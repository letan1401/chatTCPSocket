package chat_gui;

import java.awt.Color;

import core.MessInfo;

public class Chat_Left extends javax.swing.JLayeredPane {

    public Chat_Left(MessInfo messInfo) {
        this.messInfo = messInfo;
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }

    public void setText(String text,String time) {
        txt.setText(text);
        txt.setTime(time);    //  Testing
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new Chat_Item(this.messInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Chat_Item txt;
	public MessInfo getMessInfo() {
		return messInfo;
	}

	public void setMessInfo(MessInfo messInfo) {
		this.messInfo = messInfo;
	}


	private MessInfo messInfo;
    // End of variables declaration//GEN-END:variables
}
