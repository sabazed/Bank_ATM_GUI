package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_Welcome extends JFrame {

    private JPanel welcomeScreen;

    private JButton loginButton;
    private JButton registerButton;

    private final JFrame scene;

    public ATM_Welcome(String title) throws HeadlessException {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(welcomeScreen);
        this.pack();

        scene = this;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene.dispose();
                ATM_Login.open();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scene.dispose();
                ATM_Register.open();
            }
        });

    }

    public static void open() {
        JFrame welcome = new ATM_Welcome("Java Bank ATM");
        welcome.setResizable(false);
        welcome.setVisible(true);
    }

}
