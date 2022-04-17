import javax.swing.JFrame;

import java.awt.Container;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.MainFrame;

import java.awt.*;

public class LoginFrame extends JFrame {

    JDialog dlg;

    public LoginFrame(String title) {

        super(title);
        initDialog();
        initLoginFrame();

    }

    private void initLoginFrame() {
        Container contentPane;

        JButton btnShowLogin = new JButton("Login here");
        contentPane = this.getContentPane();

        contentPane.setLayout(new FlowLayout());
        contentPane.add(btnShowLogin);
        btnShowLogin.addActionListener(event -> {
            dlg.setVisible(true);
            dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.dispose();
        });
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }

    private void initDialog() {

        dlg = new JDialog(this, "Login", true);
        dlg.setLayout(new FlowLayout());

        Container contentPane = dlg.getContentPane();

        JLabel lblUserName = new JLabel("User Name");
        contentPane.add(lblUserName);

        JTextField txtUserName;
        txtUserName = new JTextField();
        txtUserName.setPreferredSize(new Dimension(100, 20));
        contentPane.add(txtUserName);

        JLabel lblPassword = new JLabel("Password");
        contentPane.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(100, 20));
        contentPane.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(
                event -> {
                    char[] password = { 'j', 'a', 'v', 'a' };
                    String encryptedAttempt = encryptToString(txtPassword.getPassword());
                    String encryptedPassword = encryptToString(password);

                    if (encryptedAttempt.equals(encryptedPassword)) {
                        dlg.setVisible(false);
                        dlg.dispose();
                        new MainFrame("Main Application Page");
                    } else
                        JOptionPane.showMessageDialog(this, "Incorrect user name / password");
                });

        contentPane.add(btnLogin);
        dlg.setPreferredSize(new Dimension(200, 200));
        dlg.pack();
        dlg.setLocationRelativeTo(null);

    }

    private String encryptToString(char[] password) {
        try {
            byte[] bytes = new byte[password.length];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) password[i];
            }

            MessageDigest digester = MessageDigest.getInstance("SHA3-256");
            byte[] encBytes = digester.digest(bytes);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < encBytes.length; i++) {
                builder.append(String.format("%02x", encBytes[i]));
            }
            System.out.print(builder.toString());
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(dlg, "No such algorithm");
        }
        return null;
    }

}
