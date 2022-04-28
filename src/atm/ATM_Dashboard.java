package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATM_Dashboard extends JFrame {

    private JPanel dashboardScreen;

    private JLabel name;
    private JLabel balance;
    private JLabel frozen;

    private JTextField amount;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton finishButton;

    private JTextField idTarget;
    private JButton transferButton;

    private final JFrame scene;

    public ATM_Dashboard(String title, Account current) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(dashboardScreen);
        this.pack();

        scene = this;
        name.setText(current.getFirstName() + " " + current.getLastName());
        balance.setText(String.valueOf(current.getBalance()));
        frozen.setText(current.isFrozen() ? "Yes" : "No");


        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    current.withdraw(Integer.parseInt(amount.getText()));
                }
                catch (Exception ex) {
                    System.out.println("Value Error");
                }
                amount.setText("0");
                balance.setText(String.valueOf(current.getBalance()));
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    current.deposit(Integer.parseInt(amount.getText()));
                }
                catch (Exception ex) {
                    System.out.println("Value Error");
                }
                amount.setText("0");
                balance.setText(String.valueOf(current.getBalance()));
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATM_Main.updateAccount(current);
                scene.dispose();
                ATM_Welcome.open();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int val = Integer.parseInt(amount.getText());
                String id = idTarget.getText();
                try {
                    ResultSet rs = checkCredentials(id);
                    if (rs != null && rs.next() && current.withdraw(val)) {
                        Account tmp = new Account(rs);
                        tmp.deposit(val);
                        ATM_Main.updateAccount(tmp);
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                amount.setText("0");
                idTarget.setText("");
                balance.setText(String.valueOf(current.getBalance()));

            }
        });

    }

    private static ResultSet checkCredentials(String login) {
        try {
            PreparedStatement check = ATM_Main.db.prepareStatement("SELECT * FROM accounts " +
                    "WHERE id = ?" +
                    "AND frozen = FALSE");
            check.setString(1, login);
            return check.executeQuery();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Opening this window
    public static void open(Account current) {
        JFrame atm = new ATM_Dashboard("Java Bank ATM", current);
        atm.setResizable(false);
        atm.setVisible(true);
    }

}
