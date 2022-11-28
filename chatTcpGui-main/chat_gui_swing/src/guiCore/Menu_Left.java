package guiCore;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import core.ListMessChat;
import event.PublicEvent;
import net.miginfocom.swing.MigLayout;

public class Menu_Left extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	private javax.swing.JLayeredPane menu;
	private javax.swing.JLayeredPane menuList;
	private MenuButton menuMessage;
	private javax.swing.JScrollPane sp;

	public Menu_Left() {
		initComponents();
		init();
	}

	private void init() {
		sp.setVerticalScrollBar(new ScrollBar());
		menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
//        showMessage();
	}


	public void updateListChat(ArrayList<ListMessChat> listUserChat) {
    	menuList.removeAll();
    	
    	for (ListMessChat listMessChat : listUserChat) {
    		menuList.add(listMessChat.getItem_People(), "wrap");
		}

		refreshMenuList();

	}

	private void refreshMenuList() {
		menuList.repaint();
		menuList.revalidate();
	}

	private void initComponents() {

		menu = new javax.swing.JLayeredPane();
		menuMessage = new MenuButton();
		sp = new javax.swing.JScrollPane();
		menuList = new javax.swing.JLayeredPane();

		setBackground(new java.awt.Color(242, 242, 242));

		menu.setBackground(new java.awt.Color(229, 229, 229));
		menu.setOpaque(true);
		menu.setLayout(new java.awt.GridLayout(1, 3));

		menuMessage.setIconSelected(
				new javax.swing.ImageIcon(getClass().getResource("/client/icon/message_selected.png"))); // NOI18N
		menuMessage.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/client/icon/message.png"))); // NOI18N
		menuMessage.setSelected(true);
		menuMessage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuMessageActionPerformed(evt);
				Object view;
				String input = JOptionPane.showInputDialog(menu, "enter user");
				PublicEvent.getInstance().getEventChat().newMessage(input);
			}
		});
		menu.add(menuMessage);

		sp.setBackground(new java.awt.Color(242, 242, 242));
		sp.setBorder(null);
		sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		menuList.setBackground(new java.awt.Color(242, 242, 242));
		menuList.setOpaque(true);

		javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
		menuList.setLayout(menuListLayout);
		menuListLayout.setHorizontalGroup(menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		menuListLayout.setVerticalGroup(menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 576, Short.MAX_VALUE));

		sp.setViewportView(menuList);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(sp).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sp)
				.addContainerGap()));
	}

	private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuMessageActionPerformed
		if (!menuMessage.isSelected()) {
			menuMessage.setSelected(true);
		}
	}
}
