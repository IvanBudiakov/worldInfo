import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.entities.User;
import model.DataModel;
import view.MainFrame;

public class LoginFrame extends JFrame {

    JDialog dlg;

    public LoginFrame(String title) {

        super(title);
        initLoginFrame();

        User tempUser = DataModel.findUserByName("user1");

        if (tempUser.getUserName() == null)
            DataModel.addUser("user1", "a03ab19b866fc585b5cb1812a2f63ca861e7e7643ee5d43fd7106b623725fd67");
    }

    private void initLoginFrame() {
        Container contentPane;

        JButton btnShowLogin = new JButton("Login here");
        JButton btnShowRegister = new JButton("Register here");
        contentPane = this.getContentPane();

        contentPane.setLayout(new FlowLayout());
        contentPane.add(btnShowLogin);
        contentPane.add(btnShowRegister);

        btnShowLogin.addActionListener(event -> {
            initLogDialog();
            dlg.setVisible(true);
            dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.dispose();

        });

        btnShowRegister.addActionListener(event -> {
            initRegDialog();
            dlg.setVisible(true);
            dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        });
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }

    private void initRegDialog() {

        dlg = new JDialog(this, "Register", true);
        dlg.setLayout(new FlowLayout());

        Container contentPane = dlg.getContentPane();

        JLabel lblUserName = new JLabel("Enter User Name");
        contentPane.add(lblUserName);

        JTextField txtUserName;
        txtUserName = new JTextField();
        txtUserName.setPreferredSize(new Dimension(100, 20));
        contentPane.add(txtUserName);

        JLabel lblPassword = new JLabel("Enter Password");
        contentPane.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(100, 20));
        contentPane.add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("Reenter Password");
        contentPane.add(lblConfirmPassword);

        JPasswordField txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setPreferredSize(new Dimension(100, 20));
        contentPane.add(txtConfirmPassword);

        JButton btnRegister = new JButton("Register");

        btnRegister.addActionListener(
                event -> {

                    String name = txtUserName.getText();

                    User currentUser = DataModel.findUserByName(name);

                    if (currentUser.getUserName() == null) {

                        char[] password = txtPassword.getPassword();
                        char[] confirmPassword = txtConfirmPassword.getPassword();
                        if (password.length != 0) {
                            if (Arrays.equals(password, confirmPassword)) {
                                String encryptedPassword = encryptToString(password);
                                DataModel.addUser(name, encryptedPassword);

                                dlg.setVisible(false);
                                dlg.dispose();
                            } else
                                JOptionPane.showMessageDialog(this, "passwords don't match");
                        } else
                            JOptionPane.showMessageDialog(this, "please enter a password");
                    } else
                        JOptionPane.showMessageDialog(this, "User name has already been taken");
                });

        contentPane.add(btnRegister);
        dlg.setPreferredSize(new Dimension(300, 200));
        dlg.pack();
        dlg.setLocationRelativeTo(null);

    }

    private void initLogDialog() {

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

                    String name = txtUserName.getText();

                    User currentUser = DataModel.findUserByName(name);

                    if (currentUser != null) {

                        String password = currentUser.getPwd();

                        String encryptedAttempt = encryptToString(txtPassword.getPassword());

                        if (encryptedAttempt.equals(password)) {
                            dlg.setVisible(false);
                            dlg.dispose();
                            new MainFrame("Main Application Page");
                        } else
                            JOptionPane.showMessageDialog(this, "Incorrect user name / password");
                    }
                });

        contentPane.add(btnLogin);
        dlg.setPreferredSize(new Dimension(200, 200));
        dlg.pack();
        dlg.setLocationRelativeTo(null);

    }

    private String encryptToString(char[] cs) {
        try {
            byte[] bytes = new byte[cs.length];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) cs[i];
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
