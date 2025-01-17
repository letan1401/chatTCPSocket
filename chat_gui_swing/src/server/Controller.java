package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import chat_gui.Chat_Body;
import chat_gui.Chat_Bottom;
import client.TCPClient;
import core.FileInfo;
import core.ListMessChat;
import core.MessInfo;
import core.Service;
import core.UserInfo;
import event.EventChat;
import event.EventExitOrLogout;
import event.EventServer;
import event.PublicEvent;
import guiCore.HomeChatServer;
import guiCore.HomeClient;
import guiCore.Item_People;

public class Controller implements ActionListener {
	private ServerGuiView view;
	private int port = 9900;
	private TCPServer tcpServer;
	private ArrayList<UserInfo> arrayListUserDatabaseToGUI;
	private SubChatClientView viewSub;
	private boolean isSendFile = false;

	public void actionPerformed(ActionEvent e) {

	}

	public Controller(ServerGuiView view) {

		this.view = view;
		tcpServer = new TCPServer(port, view.getTextAreaResult(), view);
		tcpServer.open();
		tcpServer.start();
		arrayListUserDatabaseToGUI = new ArrayList<UserInfo>();
		this.viewSub = new SubChatClientView();
		this.viewSub.setVisible(false);

		// getuser tu database va ve lai bang
		arrayListUserDatabaseToGUI = tcpServer.getService().getListUser();
		view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, tcpServer.getArrayListUser());

		PublicEvent.getInstance().addeventServer(new EventServer() {

			@Override
			public void UpdateUser(String username, int option) {
				// TODO Auto-generated method stub
				try {
					System.out.println("username "+username+" option "+option);
					tcpServer.getService().updateUser(username, option);
					
					// getuser tu database va ve lai bang
					arrayListUserDatabaseToGUI = tcpServer.getService().getListUser();
					view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, tcpServer.getArrayListUser());
					
					// kick user
					if(option == 0) {
						for (UserInfo userInfo : tcpServer.getArrayListUser()) {
							if (username.equals(userInfo.getUsername())) {
								userInfo.getSocket().close();
								tcpServer.getArrayListUser().remove(tcpServer.getArrayListUser().indexOf(userInfo));
								view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, tcpServer.getArrayListUser());
								view.getTxtUsername().setText("");
								view.getTxtComboBoxStatus().setSelectedIndex(0);
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			@Override
			public void Adduser(String username, String Password) {
				// TODO Auto-generated method stub
				if(tcpServer.getService().Register(username, Password)) {
					JOptionPane.showMessageDialog(view.getContentPane(), "Add User Success", "Alert",
							JOptionPane.WARNING_MESSAGE);
					
					// getuser tu database va ve lai bang
					arrayListUserDatabaseToGUI = tcpServer.getService().getListUser();
					view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, tcpServer.getArrayListUser());
					
				}else {
					JOptionPane.showMessageDialog(view.getContentPane(), "Add User Failed", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}
			}

			@Override
			public void SelectTableUser(String username, String status) {
				// TODO Auto-generated method stub
				for (UserInfo userInfo : arrayListUserDatabaseToGUI) {
					if (username.equals(userInfo.getUsername())) {
						view.getTxtUsername().setText(username);
						view.getTxtComboBoxStatus().setSelectedIndex(userInfo.getStatus());
						break;
					}
				}

			}

			@Override
			public void UpdateStatusUserLoginInSystem(ArrayList<UserInfo> userInfoInSystem) {
				view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, userInfoInSystem);
			}

			@Override
			public void KickUser(String username, String statusInSytem) {
				// TODO Auto-generated method stub
				try {
					for (UserInfo userInfo : tcpServer.getArrayListUser()) {
						if (username.equals(userInfo.getUsername())) {
							userInfo.getSocket().close();
							tcpServer.getArrayListUser().remove(tcpServer.getArrayListUser().indexOf(userInfo));
							view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, tcpServer.getArrayListUser());
							view.getTxtUsername().setText("");
							view.getTxtComboBoxStatus().setSelectedIndex(0);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("kick user");
				}

			}

			@Override
			public void ChatWithServer() {
				// SHOW US SOME MESSAGE
				viewSub.setVisible(true);
				viewSub.getHome().getChat().getChatBody().clearChat();
				paintMessServerWhenClick(tcpServer.getService().getMessInfoOfServer(view.getTxtUsername().getText()),
						viewSub.getHome().getChat().getChatBody());
//				JOptionPane.showMessageDialog(view.getContentPane(), view.getTxtUsername().getText() + " Clicked");
			}

			@Override
			public void UserSendServer(String userSend) {
				// TODO Auto-generated method stub
				if (view.getTxtUsername().getText().equals(userSend)) {
					paintMessServerWhenClick(
							tcpServer.getService().getMessInfoOfServer(view.getTxtUsername().getText()),
							viewSub.getHome().getChat().getChatBody());
				}
			}

		});
		PublicEvent.getInstance().addEventChat(new EventChat() {
			@Override
			public void sendMessage(String text) {
				try {
					String pathFile = null;
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					String time = dtf.format(now);
					
					MessInfo messInfo = new MessInfo("admin", view.getTxtUsername().getText(), text, time,null);
					
					// create MessInfo
					if(isSendFile) {
						FileInfo fileInfo = tcpServer.getFileInfo(text, "D:\\NAM3_HK1\\LAPTRINHMANG\\CUOIKI\\chatTcpGui-main\\Server");
						tcpServer.createFile(fileInfo);
						messInfo.setFileInfo(fileInfo);
						messInfo.getFileInfo().setSourceDirectory(messInfo.getFileInfo().getDestinationDirectory());
						pathFile = fileInfo.getDestinationDirectory() + fileInfo.getFilename();
						
					}
				 
					// find user send
					for (UserInfo userInfo : tcpServer.getArrayListUser()) {

						if (userInfo.getUsername().equals(view.getTxtUsername().getText())) {

							
							ObjectOutputStream oos = new ObjectOutputStream(userInfo.getSocket().getOutputStream());
							tcpServer.sendMess(oos, messInfo);
							oos = null;

						}

					}
					// insert database
					tcpServer.getService().insertMessInfo("admin", view.getTxtUsername().getText(), text, time, pathFile);
					// re paint
					paintMessServerWhenClick(
							tcpServer.getService().getMessInfoOfServer(view.getTxtUsername().getText()),
							viewSub.getHome().getChat().getChatBody());
					
					isSendFile=false;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			@Override
			public void sendFile() {
				String path = viewSub.chooseFile();
				isSendFile = true;
				viewSub.getHome().getChat().getChatBottom().getTxt().setText(path);
			}

			@Override
			public void newMessage(String text) {
				// TODO Auto-generated method stub

			}

			@Override
			public void downloadFile(FileInfo fileInfo) {
				// TODO Auto-generated method stub

			}
		});
		PublicEvent.getInstance().addeventExitOrLogout(new EventExitOrLogout() {

			@Override
			public void sendLogoutToServer() {
				// TODO Auto-generated method stub

			}

			@Override
			public void sendExitToServer() {
				viewSub.setVisible(false);
			}
		});
	}

	private void paintMessServerWhenClick(ArrayList<MessInfo> arrayMessInfos, Chat_Body body) {
		// TODO Auto-generated method stub
		try {
			for (MessInfo messInfo : arrayMessInfos) {

				// nếu người gửi là admin thì bên phải
				if (messInfo.getUserSource().equals("admin")) {
					body.addItemRight(messInfo.getMessContent(), messInfo.getTime(), messInfo);
				} else {
					body.addItemLeft(messInfo.getMessContent(), messInfo.getTime(), messInfo);
				}

				body.revalidate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
