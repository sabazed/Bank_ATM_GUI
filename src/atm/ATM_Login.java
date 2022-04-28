package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                String l = login.getText();
                String p = String.valueOf(pinCode.getPassword());
                Account current = checkCredentials(l, p);
                if (current != null) {
                    scene.dispose();
                    ATM_Dashboard.open(current);
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

    private static Account checkCredentials(String login, String pin) {
        try {
            PreparedStatement check = ATM_Main.db.prepareStatement("SELECT * FROM accounts " +
                                                                                    "WHERE id = ?" +
                                                                                    "AND pin = ?");
            check.setString(1, login);
            check.setString(2, pin);
            ResultSet rs = check.executeQuery();
            if (!rs.next()) return null;
            return new Account(rs);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Opening this window
    public static void open() {
        JFrame login = new ATM_Login("Java Bank ATM");
        login.setResizable(false);
        login.setVisible(true);
    }

}
