package app;

import model.Expense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp extends JFrame {
    private JTextField titleField, amountField;
    private JComboBox<String> categoryBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Expense> expenses = new ArrayList<>();

    public ExpenseTrackerApp() {
        setTitle("Smart Expense Tracker ");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUi();
    }

    private void initUi(){
        JPanel inputPanel = new JPanel(new GridLayout( 2, 4 , 10, 10 ));
        titleField = new JTextField();
        amountField = new JTextField();
        String[] categories = {"Food", "Travel", "Rent", "Shopping", "Subscriptions"};
        categoryBox = new JComboBox<>(categories);
        JButton addButton = new JButton("Add expense");
        addButton.addActionListener(e-> addExpense());
        inputPanel.add(new JLabel("Title"));
        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(new JLabel("Category"));
        inputPanel.add(new JLabel(""));

        inputPanel.add(titleField);
        inputPanel.add(amountField);
        inputPanel.add(categoryBox);
        inputPanel.add(addButton);

        String[] labels = {"Title", "Amount", "Category", "Date"};
        tableModel = new DefaultTableModel(labels,0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


    }
    private void addExpense(){

        String title = titleField.getText().trim();
        String  amountText =  amountField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();
        ValidateExpense(title,amountText);
        double amount = Double.parseDouble(amountText);
        Expense expense = new Expense(title,amount, category);
        expenses.add(expense);
        tableModel.addRow(new Object[]{expense.getTitle(),
        expense.getAmount(),
        expense.getCategory(),
        expense.getDate()});
        clearFields();

    }
    private void clearFields(){
        titleField.setText("");
        amountField.setText("");

    }
    private void ValidateExpense(String title, String amount){
        if(title.isEmpty()){
            System.out.println("Title cannot be empty");

        }
        if(amount.isEmpty()){
            System.out.println("Amount cannot be empty");
        }
        double value = Double.parseDouble(amount);
        if(value < 0){
            System.out.println("Amount should be positive");
        }

    }

    static void main() {
        SwingUtilities.invokeLater(()-> new ExpenseTrackerApp().setVisible(true));
    }
}

