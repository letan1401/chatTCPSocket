package chat_gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import event.EventChat;
import event.PublicEvent;
import net.miginfocom.swing.MigLayout;

public class Chat extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private Chat_Title chatTitle;

	public Chat_Title getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(Chat_Title chatTitle) {
		this.chatTitle = chatTitle;
	}

	private Chat_Body chatBody;

	public Chat_Body getChatBody() {
		return chatBody;
	}

	public void setChatBody(Chat_Body chatBody) {
		this.chatBody = chatBody;
	}

	private Chat_Bottom chatBottom;

	public Chat_Bottom getChatBottom() {
		return chatBottom;
	}

	public void setChatBottom(Chat_Bottom chatBottom) {
		this.chatBottom = chatBottom;
	}

	public Chat() {
		initComponents();
		init();
	}

	private void init() {
		setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, bottom]0[shrink 0]0"));
		chatTitle = new Chat_Title();
		chatBody = new Chat_Body();
		chatBottom = new Chat_Bottom();
		add(chatTitle, "wrap");
		add(chatBody, "wrap");
		add(chatBottom, "h ::50%");
	}

	private void initComponents() {

		setBackground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 727, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 681, Short.MAX_VALUE));
	}
}
