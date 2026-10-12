import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Random;

public class QuoteGenerator extends JFrame {

    private final String[] quotes = {
        "Believe you can and you're halfway there.",
        "The secret of getting ahead is getting started.",
        "Never give up on your dreams.",
        "Success is the sum of small efforts repeated daily.",
        "The future depends on what you do today.",
        "Dream big and dare to fail.",
        "Stay hungry, stay foolish.",
        "Your only limit is your mind.",
        "Great things take time. Keep going.",
        "Do something today that your future self will thank you for."
    };

    private final String[] authors = {
        "Theodore Roosevelt",
        "Mark Twain",
        "Anonymous",
        "Robert Collier",
        "Mahatma Gandhi",
        "Norman Vaughan",
        "Steve Jobs",
        "Anonymous",
        "Anonymous",
        "Anonymous"
    };

    private final Random random = new Random();

    private final JLabel titleLabel = new JLabel(
        "QUOTE GENERATOR", SwingConstants.CENTER
    );

    private final JLabel quoteLabel = new JLabel(
        "<html><div style='text-align:center;'>"
        + "Click the button to generate a quote!"
        + "</div></html>",
        SwingConstants.CENTER
    );

    private final JLabel authorLabel = new JLabel(
        "— Your daily inspiration",
        SwingConstants.CENTER
    );

    private final JButton generateButton =
        new JButton("Generate Quote");

    private final JButton copyButton =
        new JButton("Copy Quote");

    private final JButton exitButton =
        new JButton("Exit");

    private String currentQuote = "";

    public QuoteGenerator() {

        setTitle("Java Quote Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );
        mainPanel.setBackground(new Color(240, 235, 255));

        // Title
        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 28)
        );
        titleLabel.setForeground(new Color(90, 50, 160));

        // Quote text
        quoteLabel.setFont(
            new Font("Serif", Font.ITALIC, 21)
        );
        quoteLabel.setForeground(new Color(40, 40, 60));

        // Author
        authorLabel.setFont(
            new Font("Arial", Font.BOLD, 15)
        );
        authorLabel.setForeground(Color.GRAY);

        // Quote display panel
        JPanel quotePanel = new JPanel(new BorderLayout(15, 15));
        quotePanel.setBackground(Color.WHITE);
        quotePanel.setBorder(
            BorderFactory.createEmptyBorder(25, 20, 25, 20)
        );

        quotePanel.add(quoteLabel, BorderLayout.CENTER);
        quotePanel.add(authorLabel, BorderLayout.SOUTH);

        // Buttons
        generateButton.setBackground(
            new Color(100, 65, 180)
        );
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);

        copyButton.setBackground(
            new Color(70, 140, 200)
        );
        copyButton.setForeground(Color.WHITE);
        copyButton.setFocusPainted(false);

        exitButton.setBackground(
            new Color(200, 70, 70)
        );
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 12, 5)
        );
        buttonPanel.setBackground(
            new Color(240, 235, 255)
        );

        buttonPanel.add(generateButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(exitButton);

        // Layout
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(quotePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Generate a new quote
        generateButton.addActionListener(e -> generateQuote());

        // Copy the current quote
        copyButton.addActionListener(e -> copyQuote());

        // Exit the application
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to exit?",
                "Exit",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void generateQuote() {

        int index = random.nextInt(quotes.length);

        currentQuote = quotes[index] + " — " + authors[index];

        quoteLabel.setText(
            "<html><div style='text-align:center;width:400px;'>"
            + "&ldquo;" + quotes[index] + "&rdquo;"
            + "</div></html>"
        );

        authorLabel.setText("— " + authors[index]);
    }

    private void copyQuote() {

        if (currentQuote.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please generate a quote first!",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        StringSelection selection =
            new StringSelection(currentQuote);

        Toolkit.getDefaultToolkit()
            .getSystemClipboard()
            .setContents(selection, null);

        JOptionPane.showMessageDialog(
            this,
            "Quote copied to clipboard!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new QuoteGenerator();
        });
    }
}