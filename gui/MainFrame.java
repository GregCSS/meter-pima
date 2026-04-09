package gui;

import model.Staff;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Staff currentUser;

    public MainFrame(Staff currentUser) {
        this.currentUser = currentUser;

        setTitle("Dashboard - " + currentUser.getName());
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setBackground(new Color(30, 40, 60));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(sidebar, BorderLayout.WEST);

        // Top section
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(30, 40, 60));

        JLabel userLabel = new JLabel("Logged in as: " + currentUser.getName());
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(userLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        sidebar.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(30, 40, 60));
        sidebar.add(centerPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        if (currentUser.isAdmin() == 1) {
            // Admin sees Inventory & Staff panels
            CardLayout cardLayout = new CardLayout();
            mainPanel.setLayout(cardLayout);

            InventoryPanel inventoryPanel = new InventoryPanel(currentUser);
            StaffPanel staffPanel = new StaffPanel(currentUser);

            mainPanel.add(inventoryPanel, "Inventory");
            mainPanel.add(staffPanel, "Staff");

            // Sidebar buttons for admin
            JButton inventoryBtn = createSidebarButton("Inventory");
            inventoryBtn.addActionListener(e -> cardLayout.show(mainPanel, "Inventory"));
            centerPanel.add(inventoryBtn);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            JButton staffBtn = createSidebarButton("Staff");
            staffBtn.addActionListener(e -> cardLayout.show(mainPanel, "Staff"));
            centerPanel.add(staffBtn);

        } else {
            // Cashier sees only OrderPanel
            OrderPanel orderPanel = new OrderPanel(currentUser);
            mainPanel.add(orderPanel, BorderLayout.CENTER);
        }

        // Logout
        JButton logoutBtn = createSidebarButton("Logout");
        logoutBtn.addActionListener(e -> {
            dispose(); // close dashboard
            new AuthFrame().setVisible(true); // go back to login
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(30, 40, 60));
        bottomPanel.add(Box.createVerticalGlue());
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(logoutBtn);

        sidebar.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 70, 100));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 90, 130));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 70, 100));
            }
        });

        return button;
    }
}