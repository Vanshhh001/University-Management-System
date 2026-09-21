package University.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Allows an administrator or user to create a separate login account. */
public class Signup extends JFrame implements ActionListener {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;
    private final JButton createButton;
    private final JButton cancelButton;

    Signup() {
        setTitle("Create Account");
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Create Login Account");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(90, 20, 250, 30);
        add(heading);

        addLabel("Username", 70);
        usernameField = new JTextField();
        usernameField.setBounds(160, 70, 180, 25);
        add(usernameField);

        addLabel("Password", 115);
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 115, 180, 25);
        add(passwordField);

        addLabel("Confirm Password", 160);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(160, 160, 180, 25);
        add(confirmPasswordField);

        createButton = new JButton("Create Account");
        createButton.setBounds(65, 220, 140, 30);
        createButton.addActionListener(this);
        add(createButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 220, 120, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setSize(420, 320);
        setLocation(550, 280);
        setLayout(null);
        setVisible(true);
    }

    private void addLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(45, y, 120, 25);
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == cancelButton) {
            setVisible(false);
            return;
        }

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmation = new String(confirmPasswordField.getPassword());

        if (!username.matches("[A-Za-z0-9_.-]{3,30}")) {
            JOptionPane.showMessageDialog(this,
                    "Username must contain 3-30 letters, numbers, dots, hyphens, or underscores.");
            return;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must contain at least 6 characters.");
            return;
        }
        if (!password.equals(confirmation)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        try {
            Conn c = new Conn();
            PreparedStatement checkUser = c.connection.prepareStatement(
                    "select username from login where username = ?");
            checkUser.setString(1, username);
            ResultSet resultSet = checkUser.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "This username is already in use.");
                return;
            }

            PreparedStatement createUser = c.connection.prepareStatement(
                    "insert into login (username, password) values (?, ?)");
            createUser.setString(1, username);
            createUser.setString(2, password);
            createUser.executeUpdate();
            JOptionPane.showMessageDialog(this, "Account created. You can now log in.");
            setVisible(false);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this,
                    "Could not create the account. Check that the login table exists in the database.");
            exception.printStackTrace();
        }
    }
}
