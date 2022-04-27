package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_Dashboard extends JFrame {

    private JPanel dashboardScreen;

    private JLabel name;
    private JLabel balance;
    private JLabel frozen;

    private JTextField amount;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton finishButton;

    private final Account current;
    private final JFrame scene;

    public ATM_Dashboard(String title, int id) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(dashboardScreen);
        this.pack();

        scene = this;
        current = ATM_Main.database.get(id);
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
                scene.dispose();
                ATM_Welcome.open();
            }
        });
    }

    public static void open(int id) {
        JFrame atm = new ATM_Dashboard("Java Bank ATM", id);
        atm.setResizable(false);
        atm.setVisible(true);
    }

}
