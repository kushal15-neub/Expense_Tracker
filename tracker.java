import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Expenses_tracker {
    public static ArrayList<Expense> expensesList = new ArrayList<>();

    public static void main(String[] args) {
        createMainFrame();
    }

    private static void createMainFrame() {
        JFrame mainFrame = new JFrame("Personal Expense Tracker");
        mainFrame.setSize(400, 200);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setBounds(125, 50, 150, 25);

        JButton viewExpensesButton = new JButton("View Expenses");
        viewExpensesButton.setBounds(125, 110, 150, 25);

        mainFrame.add(addExpenseButton);
        mainFrame.add(viewExpensesButton);

        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAddExpenseFrame();
            }
        });

        viewExpensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createViewExpensesFrame();
            }
        });

        mainFrame.setVisible(true);
    }

    private static void createAddExpenseFrame() {
        JFrame addExpenseFrame = new JFrame("Add Expense");
        addExpenseFrame.setSize(400, 300);
        addExpenseFrame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel dateLabel = new JLabel("Date (yy-mm-dd):");
        JLabel categoryLabel = new JLabel("Category:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel descriptionLabel = new JLabel("Description:");

        JTextField dateField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField descriptionField = new JTextField();

        JButton addButton = new JButton("Add To List");
        JButton backButton = new JButton("Back");

        addExpenseFrame.add(dateLabel);
        addExpenseFrame.add(dateField);
        addExpenseFrame.add(categoryLabel);
        addExpenseFrame.add(categoryField);
        addExpenseFrame.add(amountLabel);
        addExpenseFrame.add(amountField);
        addExpenseFrame.add(descriptionLabel);
        addExpenseFrame.add(descriptionField);
        addExpenseFrame.add(addButton);
        addExpenseFrame.add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String category = categoryField.getText();
                String amount = amountField.getText();
                String description = descriptionField.getText();

                if (!date.isEmpty() && !category.isEmpty() && !amount.isEmpty() && !description.isEmpty()) {
                    try {
                        double amountValue = Double.parseDouble(amount);
                        Expense expense = new Expense(date, category, amountValue, description);
                        expensesList.add(expense);
                        dateField.setText("");
                        categoryField.setText("");
                        amountField.setText("");
                        descriptionField.setText("");
                        JOptionPane.showMessageDialog(addExpenseFrame, "Expense Added Successfully");

                        // Show all collected data in a new JFrame
                        createTotalDataFrame();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addExpenseFrame, "Invalid amount. Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(addExpenseFrame, "Please fill in all fields.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpenseFrame.dispose(); // Close the frame
            }
        });
        addExpenseFrame.setVisible(true);
    }

    private static void createTotalDataFrame() {
        JFrame totalFrame = new JFrame("All Collected Data");
        totalFrame.setSize(400, 300);
        totalFrame.setLayout(new BorderLayout());

        JTextArea expensesArea = new JTextArea();
        expensesArea.setEditable(false);

        StringBuilder expensesText = new StringBuilder();
        for (Expense expense : expensesList) {
            expensesText.append(expense).append("\n");
        }

        expensesArea.setText(expensesText.toString());

        totalFrame.add(new JScrollPane(expensesArea), BorderLayout.CENTER);
        totalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        totalFrame.setVisible(true);
    }

    private static void createViewExpensesFrame() {
        JFrame viewExpensesFrame = new JFrame("View Expenses");
        viewExpensesFrame.setSize(400, 300);
        viewExpensesFrame.setLayout(new BorderLayout());

        JTextArea expensesArea = new JTextArea();
        expensesArea.setEditable(false);

        StringBuilder expensesText = new StringBuilder();
        double totalExpenses = 0;
        for (Expense expense : expensesList) {
            expensesText.append(expense).append("\n");
            totalExpenses += expense.getAmount();
        }

        expensesText.append("\nTotal Expenses: ").append(totalExpenses);
        expensesArea.setText(expensesText.toString());

        JPanel panel = new JPanel();
        JButton saveButton = new JButton("Save to File");
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveExpensesToFile(expensesText.toString());
            }
        });

        viewExpensesFrame.add(new JScrollPane(expensesArea), BorderLayout.CENTER);
        viewExpensesFrame.add(panel, BorderLayout.SOUTH);
        viewExpensesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewExpensesFrame.setVisible(true);
    }

    private static void saveExpensesToFile(String expensesText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt"))) {
            writer.write(expensesText);
            JOptionPane.showMessageDialog(null, "Expenses saved to expenses.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + e.getMessage());
        }
    }

    static class Expense {
        private String date;
        private String category;
        private double amount;
        private String description;

        public Expense(String date, String category, double amount, String description) {
            this.date = date;
            this.category = category;
            this.amount = amount;
            this.description = description;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Date: " + date + ", Category: " + category + ", Amount: " + amount + ", Description: "
                    + description;
        }
    }
}
