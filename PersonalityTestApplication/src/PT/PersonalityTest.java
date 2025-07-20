package PT;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PersonalityTest extends JFrame {
    private List<Question> questions;
    private int currentQuestionIndex = 0;

    private JRadioButton optA, optB, optC, optD;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JLabel questionLabel, titleLabel;

    private Database db;
    private int userId;
    
    public PersonalityTest(int userId) {
        this.userId = userId;
        db = new Database();
        db.clearPersonalityCounts(userId);
        questions = db.getAllQuestions();
        Collections.shuffle(questions);

        setTitle("Personality Test");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel topPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Personality Test", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        topPanel.add(titleLabel, BorderLayout.NORTH);

       
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Bold", Font.PLAIN, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(questionLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

       
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        optA = new JRadioButton();
        optB = new JRadioButton();
        optC = new JRadioButton();
        optD = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(optA);
        optionsGroup.add(optB);
        optionsGroup.add(optC);
        optionsGroup.add(optD);

        optionsPanel.add(optA);
        optionsPanel.add(optB);
        optionsPanel.add(optC);
        optionsPanel.add(optD);
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextButton = new JButton("Next");
        nextButton.setBackground(new Color(34, 139, 34)); // Dark green
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setPreferredSize(new Dimension(100, 35));
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!optA.isSelected() && !optB.isSelected() && !optC.isSelected() && !optD.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select an option.");
                    return;
                }

                processAnswer();

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion(currentQuestionIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Test complete!");
                    dispose();
                    new ResultFrame(userId);
                }
            }
        });

        if (!questions.isEmpty()) {
            showQuestion(currentQuestionIndex);
        } else {
            JOptionPane.showMessageDialog(this, "No questions found.");
            dispose();
        }

        setVisible(true);
    }

    private void showQuestion(int index) {
        Question q = questions.get(index);
        questionLabel.setText("Q" + (index + 1) + ": " + q.getText());
        optA.setText("A. " + q.getOptionA());
        optB.setText("B. " + q.getOptionB());
        optC.setText("C. " + q.getOptionC());
        optD.setText("D. " + q.getOptionD());
        optionsGroup.clearSelection();
    }

    private void processAnswer() {
        if (optA.isSelected()) {
            db.incrementPersonalityCount(userId, "Introvert");
        } else if (optB.isSelected()) {
            db.incrementPersonalityCount(userId, "Extrovert");
        } else if (optC.isSelected()) {
            db.incrementPersonalityCount(userId, "Ambivert");
        } else if (optD.isSelected()) {
            db.incrementPersonalityCount(userId, "Analyst");
        }
    }
}

