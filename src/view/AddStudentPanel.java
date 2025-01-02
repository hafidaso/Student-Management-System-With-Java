// File: view/AddStudentPanel.java
package view;

import controller.StudentController;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddStudentPanel extends JPanel {
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField emailField;
    private final JTextField majorField;
    private final JButton addButton;

    public AddStudentPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Add New Student"));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels and text fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        idField = new JTextField(20);
        add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        emailField = new JTextField(20);
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Major:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        majorField = new JTextField(20);
        add(majorField, gbc);

        // Add button
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        addButton = new JButton("Add Student");
        add(addButton, gbc);

        // Add event handler
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect student data
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String major = majorField.getText().trim();

                // Input validation
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Student ID is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Email is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (major.isEmpty()) {
                    JOptionPane.showMessageDialog(AddStudentPanel.this, "Major is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new student object
                Student student = new Student(id, name, email, major);

                // Add student using controller
                StudentController.addStudent(student);

                // Show confirmation
                JOptionPane.showMessageDialog(AddStudentPanel.this, "Student added successfully!");

                // Clear fields
                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                majorField.setText("");
            }
        });
    }

    private boolean isValidEmail(String email) {
        // Simple regex for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}