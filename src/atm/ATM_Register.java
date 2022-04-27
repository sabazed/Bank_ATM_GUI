package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_Register extends JFrame {

    private JPanel registerScreen;

    private JLabel idNum;
    private JTextField lastName;
    private JTextField firstName;
    private JPasswordField pinCode;

    private JLabel wrongCred;

    private JButton regButton;
    private JButton backButton;

    private final JFrame scene;

    public ATM_Register(String title) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerScreen);
        this.pack();

        idNum.setText(String.valueOf(Account.count + 1));

        scene = this;

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String f = firstName.getText();
                    String l = lastName.getText();
                    char[] p = pinCode.getPassword();
                    if (validPin(p) && validName(f, l)) {
                        ATM_Main.database.add(new Account(f, l, p));
                        scene.dispose();
                        ATM_Welcome.open();
                    }
                    else throw new Exception("Wrong input! Please try again.");
                }
                catch (Exception ex) {
                    wrongCred.setText(ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene.dispose();
                ATM_Welcome.open();
            }
        });

    }

    protected static boolean validPin(char[] pin) {
        for (char c : pin) {
            if (c < '0' || c > '9') return false;
        }
        return pin.length == 4;
    }

    private static boolean validName(String firstName, String lastName) {
        return firstName.chars().allMatch(Character::isLetter) && lastName.chars().allMatch(Character::isLetter);
    }

    public static void open() {
        JFrame register = new ATM_Register("Java Bank ATM");
        register.setResizable(false);
        register.setVisible(true);
    }

}
