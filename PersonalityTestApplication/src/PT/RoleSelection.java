package PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoleSelection extends JFrame {
    public RoleSelection() {
        setTitle("Select Role");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(250, 0));
        leftPanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("C:\\Users\\HAMNA\\Desktop\\download (2).jpg"); 
        Image scaledImage = icon.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        leftPanel.add(imageLabel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

 
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1, 15, 15));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        
        JLabel headingLabel = new JLabel("WELCOMEEEEEE!!! ", JLabel.CENTER);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        headingLabel.setForeground(new Color(0, 102, 204));
        rightPanel.add(headingLabel);

        
        JLabel welcomeLabel = new JLabel("Choose Login Type", JLabel.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        rightPanel.add(welcomeLabel);

      
        JButton adminBtn = new JButton("Admin Login");
        adminBtn.setBackground(new Color(0, 102, 204)); 
        adminBtn.setForeground(Color.WHITE);
        adminBtn.setFocusPainted(false);

        JButton userBtn = new JButton("User Login");
        userBtn.setBackground(new Color(0, 153, 76)); 
        userBtn.setForeground(Color.WHITE);
        userBtn.setFocusPainted(false);

        rightPanel.add(adminBtn);
        rightPanel.add(userBtn);

        add(rightPanel, BorderLayout.CENTER);

        
        adminBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login("admin");
                dispose();
            }
        });

        userBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login("user");
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
        	 JOptionPane.showMessageDialog(null, "UCanAccess driver not found.");
             return;
         }

         new RoleSelection();
     }
 }


