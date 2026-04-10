package gui;

import dao.StaffDAO;
import javax.swing.*;
import model.Staff;

public class AuthFrame extends JFrame {

    private final StaffDAO dao = new StaffDAO();

    public AuthFrame() {
        setTitle("Staff Login");
        setSize(350, 200);
        setLayout(new java.awt.GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // UI elements
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");

        add(nameLabel); add(nameField);
        add(passwordLabel); add(passwordField);
        add(new JLabel()); add(new JLabel());
        add(loginBtn); add(exitBtn);

        // Event listeners
        loginBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter both name & password");
                return;
            }

            Staff staff = dao.getByName(name);
            if (staff == null || !staff.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
                return;
            }

            new MainFrame(staff).setVisible(true);
            dispose();
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }
}