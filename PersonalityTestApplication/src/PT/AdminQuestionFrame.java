package PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminQuestionFrame extends JFrame {
    private JTextField questionField, optionAField, optionBField, optionCField, optionDField;
    private JButton saveBtn;

    private Database db;

    public AdminQuestionFrame() {
        db = new Database(); 

        setTitle("Admin - Add Question");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JLabel qLabel = new JLabel("Question:");
        qLabel.setBounds(30, 20, 100, 25);
        add(qLabel);

        questionField = new JTextField();
        questionField.setBounds(130, 20, 300, 25);
        add(questionField);

       
        JLabel aLabel = new JLabel("Option A:");
        aLabel.setBounds(30, 60, 100, 25);
        add(aLabel);

        optionAField = new JTextField();
        optionAField.setBounds(130, 60, 300, 25);
        add(optionAField);
        JLabel bLabel = new JLabel("Option B:");
        bLabel.setBounds(30, 100, 100, 25);
        add(bLabel);

        optionBField = new JTextField();
        optionBField.setBounds(130, 100, 300, 25);
        add(optionBField);

        
        JLabel cLabel = new JLabel("Option C:");
        cLabel.setBounds(30, 140, 100, 25);
        add(cLabel);

        optionCField = new JTextField();
        optionCField.setBounds(130, 140, 300, 25);
        add(optionCField);

      
        JLabel dLabel = new JLabel("Option D:");
        dLabel.setBounds(30, 180, 100, 25);
        add(dLabel);

        optionDField = new JTextField();
        optionDField.setBounds(130, 180, 300, 25);
        add(optionDField);

        saveBtn = new JButton("Save Question");
        saveBtn.setBounds(170, 230, 150, 35);
        saveBtn.setBackground(new Color(30, 144, 255)); 

        
        add(saveBtn);

      
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveQuestion();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveQuestion() {
        String question = questionField.getText().trim();
        String a = optionAField.getText().trim();
        String b = optionBField.getText().trim();
        String c = optionCField.getText().trim();
        String d = optionDField.getText().trim();

        if (question.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        boolean success = db.insertQuestion(question, a, b, c, d);
        if (success) {
            JOptionPane.showMessageDialog(this, "Question saved successfully!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error saving question.");
        }
    }

    private void clearFields() {
        questionField.setText("");
        optionAField.setText("");
        optionBField.setText("");
        optionCField.setText("");
        optionDField.setText("");
    }

    public static void main(String[] args) {
        new AdminQuestionFrame();
    }
}


