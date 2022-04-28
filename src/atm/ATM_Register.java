package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_Register extends JFrame {

    private JPanel registerScreen;

    private JLabel idNum;
    private JTextField firstName;
    private JTextField lastName;
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

        idNum.setText(Account.nextId);

        scene = this;

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String f = firstName.getText();
                    String l = lastName.getText();
                    String p = String.valueOf(pinCode.getPassword());
                    if (validPin(p) && validName(f, l)) {
                        ATM_Main.addAccount(f, l, p);
                        scene.dispose();
                        ATM_Welcome.open();
                    }
                    else throw new Exception("Wrong input! Please try again.");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
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

    // Check if the pin is valid input
    protected static boolean validPin(String pin) {
        return pin.length() == 4 && pin.chars().allMatch(Character::isDigit);
    }

    // Check if the names are correctly provided
    private static boolean validName(String firstName, String lastName) {
        return firstName.chars().allMatch(Character::isLetter) && lastName.chars().allMatch(Character::isLetter);
    }

    // Opening this window
    public static void open() {
        JFrame register = new ATM_Register("Java Bank ATM");
        register.setResizable(false);
        register.setVisible(true);
    }

}
