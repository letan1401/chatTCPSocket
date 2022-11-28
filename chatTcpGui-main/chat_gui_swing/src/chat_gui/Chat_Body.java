package chat_gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import core.MessInfo;
import guiCore.*;
import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;

	public Chat_Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public void addItemLeft(String text,String time,MessInfo messInfo) {
        Chat_Left item = new Chat_Left(messInfo);
        item.setText(text,time);
        body.add(item, "wrap, w ::80%");
        //  ::80% set max with 80%
        
        rePaint();
    }
    
    public void rePaint() {
    	
        body.repaint();
        body.revalidate();
        
        int height = (int)body.getPreferredSize().getHeight();
        Rectangle rect = new Rectangle(0,height,10,10);
        body.scrollRectToVisible(rect);
    }

    public void addItemRight(String text,String time,MessInfo messInfo) {
    	
        Chat_Right item = new Chat_Right(messInfo);
        
        item.setText(text,time);
        body.add(item, "wrap, al right, w ::80%");
        //  ::80% set max with 80%
        
        rePaint();
    }
    
    public void clearChat() {

    	body.removeAll();
        body.repaint();
        body.revalidate();
        
    }

    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }
}
