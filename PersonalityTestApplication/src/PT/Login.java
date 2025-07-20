package PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private JLabel titleLabel;

    private Database db;
    private String role;

    public Login(String role) {
        this.role = role;
        db = new Database();

        setTitle(role.toUpperCase() + " Login");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel(role.toUpperCase() + " LOGIN", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setBounds(80, 10, 300, 30);
        titleLabel.setForeground(new Color(0, 102, 204));
        add(titleLabel);

        JLabel emailLbl = new JLabel("Email:");
        emailLbl.setBounds(50, 60, 100, 25);
        add(emailLbl);

        emailField = new JTextField();
        emailField.setBounds(150, 60, 200, 25);
        add(emailField);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(50, 100, 100, 25);
        add(passLbl);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 25);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(80, 160, 120, 30);
        loginBtn.setBackground(new Color(0, 153, 76));
        loginBtn.setForeground(Color.WHITE);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(220, 160, 120, 30);
        registerBtn.setBackground(new Color(51, 102, 255));
        registerBtn.setForeground(Color.WHITE);
        add(registerBtn);

        
        if (role.equals("admin")) {
            registerBtn.setVisible(false);
        }

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser(emailField.getText(), new String(passwordField.getPassword()));
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser(emailField.getText(), new String(passwordField.getPassword()));
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loginUser(String email, String password) {
        if (db.validateLogin(email, password)) {
            if (role.equals("admin") && email.equalsIgnoreCase("admin@gmail.com")) {
                JOptionPane.showMessageDialog(this, "Admin logged in.");
                new AdminQuestionFrame();
                dispose();
            } else if (role.equals("user") && !email.equalsIgnoreCase("admin@gmail.com")) {
                int userId = db.getUserIdByEmail(email);
                if (userId != -1) {
                    JOptionPane.showMessageDialog(this, "User login successful!");
                    new PersonalityTest(userId);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "User ID not found.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Role mismatch.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.");
        }
    }

    private void registerUser(String email, String password) {
        if (db.emailExists(email)) {
            JOptionPane.showMessageDialog(this, "Email already registered.");
        } else if (db.insertUser(email, password)) {
            JOptionPane.showMessageDialog(this, "Registered successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed.");
        }
    }
}



