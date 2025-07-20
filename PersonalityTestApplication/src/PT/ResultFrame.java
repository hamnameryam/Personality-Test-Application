package PT;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultFrame extends JFrame {

    private int userId;
    private Database db;

    public ResultFrame(int userId) {
        this.userId = userId;
        this.db = new Database();

        setTitle("Result");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        List<PersonalityCount> counts = db.getPersonalityCountsSimple(userId);
        if (counts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No personality data found for this user.");
            dispose();
            return;
        }

        int totalCount = 0;
        for (PersonalityCount pc : counts) {
            totalCount += pc.count;
        }
        String dominantPersonality = "";
        int maxCount = -1;
        double dominantPercentage = 0;

        for (PersonalityCount pc : counts) {
            double percentage = (totalCount != 0) ? (pc.count * 100.0) / totalCount : 0;
            if (pc.count > maxCount) {
                maxCount = pc.count;
                dominantPersonality = pc.personality;
                dominantPercentage = percentage;
            }
        }

        db.saveDominantResult(userId, dominantPersonality, dominantPercentage);

        setLayout(new BorderLayout());

        JLabel tl = new JLabel("YOUR PERSONALITY TEST RESULT", JLabel.CENTER);
        tl.setFont(new Font("Arial", Font.BOLD, 20));
        tl.setForeground(Color.BLACK);
        add(tl, BorderLayout.NORTH);

    
        JPanel cP = new JPanel(new GridLayout(1, 2));

       
        JPanel leftPanel = new JPanel(new GridLayout(4, 1));
        leftPanel.setBackground(Color.WHITE);

        JLabel domTitle = new JLabel("DOMINANT PERSONALITY", JLabel.CENTER);
        domTitle.setFont(new Font("Arial", Font.BOLD, 16));
        domTitle.setForeground(Color.RED);

        JLabel domName = new JLabel(dominantPersonality.toUpperCase(), JLabel.CENTER);
        domName.setFont(new Font("Arial", Font.BOLD, 22));
        domName.setForeground(Color.BLACK);

        JLabel domPercent = new JLabel(String.format("(%.2f%%)", dominantPercentage), JLabel.CENTER);
        domPercent.setFont(new Font("Arial", Font.PLAIN, 14));
        domPercent.setForeground(Color.DARK_GRAY);

        JTextArea descArea = new JTextArea(getMessageFor(dominantPersonality));
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setOpaque(false);
        descArea.setFont(new Font("Serif", Font.PLAIN, 14));

        leftPanel.add(domTitle);
        leftPanel.add(domName);
        leftPanel.add(domPercent);
        leftPanel.add(descArea);

       
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1, 10, 10));
        rightPanel.setBackground(Color.WHITE);

        for (PersonalityCount pc : counts) {
            double percentage = totalCount == 0 ? 0 : (pc.count * 100.0) / totalCount;

            JPanel barPanel = new JPanel(new BorderLayout());
            barPanel.setBackground(Color.WHITE);

            JLabel label = new JLabel(pc.personality + ": " + String.format("%.2f", percentage) + "%");
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            barPanel.add(label, BorderLayout.NORTH);

            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue((int) percentage);
            progressBar.setStringPainted(true);
            progressBar.setForeground(Color.RED);

            progressBar.setPreferredSize(new Dimension(200, 15));
            barPanel.add(progressBar, BorderLayout.CENTER);

            rightPanel.add(barPanel);
        }


        cP.add(leftPanel);
        cP.add(rightPanel);
        add(cP, BorderLayout.CENTER);

        JLabel thankYou = new JLabel("THANK YOU FOR YOUR TEST", JLabel.CENTER);
        thankYou.setFont(new Font("Arial", Font.BOLD, 18));
        thankYou.setForeground(new Color(0, 128, 0));
        add(thankYou, BorderLayout.SOUTH);
    }

    private String getMessageFor(String personality) {
        if ("Introvert".equals(personality)) {
            return "• You find peace and strength in solitude.\n" +
                    "• Prefer deep, meaningful conversations over small talk.\n" +
                    "• Recharge by spending time alone or in calm environments.\n" +
                    "• Thoughtful, reflective, and thrive in introspective moments.";
        } else if ("Extrovert".equals(personality)) {
            return "• Energized by the presence of others and social interactions.\n" +
                    "• Enjoy lively discussions, group activities, and teamwork.\n" +
                    "• Expressive, enthusiastic, and often the life of the party.\n" +
                    "• Thrive in dynamic, social environments.";
        } else if ("Ambivert".equals(personality)) {
            return "• Comfortable with both social interaction and solitude.\n" +
                    "• Adapt well to different social situations and moods.\n" +
                    "• A balanced communicator and a good listener.\n" +
                    "• Flexible and emotionally intelligent in relationships.";
        } else if ("Analyst".equals(personality)) {
            return "• Possess a sharp, logical, and inquisitive mind.\n" +
                    "• Approach life with curiosity and a desire to understand.\n" +
                    "• Value accuracy, analysis, and well-thought-out decisions.\n" +
                    "• Often prefer logic and structure over spontaneity.";
        } else {
            return "• You have a unique and dynamic personality.\n" +
                    "• Not confined to one category—you're adaptable and original.\n" +
                    "• You bring a blend of qualities that make you truly one of a kind.";
        }
    }
}

