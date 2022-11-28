package guiCore;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Item_People extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	
	private String name;
    private ImageAvatar imageAvatar1;
    private javax.swing.JLabel lb;

	public javax.swing.JLabel getLb() {
		return lb;
	}

	public void setLb(javax.swing.JLabel lb) {
		this.lb = lb;
	}

	public Item_People(String name) {
		this.name = name;
        initComponents();
        lb.setText(name);
        init();
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(242, 242, 242));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            	// TODO Auto-generated method stub
            	super.mouseClicked(e);
//            	System.out.println("hello");
            }
        });
    }


    private void initComponents() {

        imageAvatar1 = new ImageAvatar();
        lb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/client/icon/profile.png"))); // NOI18N

        lb.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lb.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );
    }
}
