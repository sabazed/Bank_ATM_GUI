package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_Login extends JFrame {

    private JPanel loginScreen;

    private JTextField login;
    private JPasswordField pinCode;
    private JLabel wrongCred;
    private JButton loginButton;
    private JButton backButton;

    private final JFrame scene;

    public ATM_Login(String title) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginScreen);
        this.pack();

        scene = this;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = Integer.parseInt(login.getText());
                char[] p = pinCode.getPassword();
                if (ATM_Main.database.size() > temp && ATM_Register.validPin(p) && ATM_Main.database.get(temp).matchPin(p)) {
                    scene.dispose();
                    ATM_Dashboard.open(temp);
                }
                else wrongCred.setText("Wrong Credentials! Please try again.");
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

    public static void open() {
        JFrame login = new ATM_Login("Java Bank ATM");
        login.setResizable(false);
        login.setVisible(true);
    }

}
