import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A GUI application to manage a simple bank account.
 * Allows users to deposit, withdraw, view balance, and exit.
 */
public class BankAppGUI {

    private JFrame frame;
    private JTextField inputField;
    private JTextArea historyArea;
    private JLabel balanceLabel;
    private BankAccount account;

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton exitButton;

    /**
     * Constructs the GUI application and initializes the bank account.
     */
    public BankAppGUI() {
        account = new BankAccount();
        initializeGUI();
    }

    /**
     * Entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new BankAppGUI();
    }

    /**
     * Initializes the GUI and all its components.
     */
    private void initializeGUI() {
        frame = new JFrame("Bank Balance App");
        JPanel panel = new JPanel(new BorderLayout());

        inputField = new JTextField(10);
        historyArea = new JTextArea(10, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        balanceLabel = new JLabel();
        updateBalance();

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exitButton = new JButton("Exit");

        depositButton.addActionListener(new ActionListener() {
            /**
             * Handles deposit button click: parses input and updates account.
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    account.deposit(amount);
                    updateBalance();
                    appendHistory(String.format("Deposited $%.2f | New Balance: $%.2f",
                            amount, account.getBalance()));
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    showError("Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            /**
             * Handles withdraw button click: validates amount and updates account.
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    if (amount > account.getBalance()) {
                        showError("Insufficient funds.");
                        return;
                    }
                    account.withdraw(amount);
                    updateBalance();
                    appendHistory(String.format("Withdrew $%.2f | New Balance: $%.2f",
                            amount, account.getBalance()));
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    showError("Please enter a valid number.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            /**
             * Handles exit button: shows final balance and exits.
             */
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Final Balance: $" + String.format("%.2f", account.getBalance()),
                        "Final Balance", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(balanceLabel);
        topPanel.add(inputField);
        topPanel.add(depositButton);
        topPanel.add(withdrawButton);
        topPanel.add(exitButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Updates the balance label with the current account balance.
     */
    private void updateBalance() {
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));
    }

    /**
     * Appends a message to the transaction history area.
     *
     * @param action the transaction message to append
     */
    private void appendHistory(String action) {
        historyArea.append(action + "\n");
    }

    /**
     * Shows an error dialog with the specified message.
     *
     * @param message the error message
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
