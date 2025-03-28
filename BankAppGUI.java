import javax.swing.*;
import java.awt.event.*;

public class BankAppGUI {
    private JFrame frame;
    private JTextField inputField;
    private JLabel balanceLabel;
    private JTextArea historyArea;
    private BankAccount account;

    public BankAppGUI() {
        account = new BankAccount(0.0);

        frame = new JFrame("Bank Balance App");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        balanceLabel = new JLabel("Current Balance: $0.00");
        inputField = new JTextField(10);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        historyArea = new JTextArea(8, 30);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    account.deposit(amount);
                    updateBalance();
                    appendHistory("Deposited $" + String.format("%.2f", amount) +
                                  " | New Balance: $" + String.format("%.2f", account.getBalance()));
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    showError("Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    if (amount > account.getBalance()) {
                        showError("Insufficient funds.");
                        return;
                    }
                    account.withdraw(amount);
                    updateBalance();
                    appendHistory("Withdrew $" + String.format("%.2f", amount) +
                                  " | New Balance: $" + String.format("%.2f", account.getBalance()));
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    showError("Please enter a valid number.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Final Balance: $" +
                        String.format("%.2f", account.getBalance()));
                System.exit(0);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(balanceLabel);
        topPanel.add(inputField);
        topPanel.add(depositButton);
        topPanel.add(withdrawButton);
        topPanel.add(exitButton);

        panel.add(topPanel);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateBalance() {
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", account.getBalance()));
    }

    private void appendHistory(String action) {
        historyArea.append(action + "\n");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new BankAppGUI();
    }
}
