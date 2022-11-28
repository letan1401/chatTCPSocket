package chat_gui;

import java.awt.Color;

import core.MessInfo;

public class Chat_Right extends javax.swing.JLayeredPane {

	private static final long serialVersionUID = 1L;
	private Chat_Item txt;
	private MessInfo messInfo;
	
	public MessInfo getMessInfo() {
		return messInfo;
	}

	public void setMessInfo(MessInfo messInfo) {
		this.messInfo = messInfo;
	}

	public Chat_Right(MessInfo messInfo) {
		this.messInfo = messInfo;
        initComponents();
        txt.setBackground(new Color(179, 233, 255));        
    }

    public void setText(String text,String time) {
        txt.setText(text);
        txt.setTime(time);
        txt.seen();
    }

    private void initComponents() {

        txt = new Chat_Item(this.messInfo);

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }
}

