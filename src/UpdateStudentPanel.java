// File: view/UpdateStudentPanel.java
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Student;
import controller.StudentController;

public class UpdateStudentPanel extends JPanel {
    private final JComboBox<Student> studentComboBox;
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField emailField;
    private final JTextField majorField;
    private final JButton updateButton;

    public UpdateStudentPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Update Student Information"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Student selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Select Student:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        studentComboBox = new JComboBox<>();
        for (Student s : StudentController.getAllStudents()) {
            studentComboBox.addItem(s);
        }
        add(studentComboBox, gbc);

        // Labels and text fields
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        idField = new JTextField(20);
        idField.setEditable(false);
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

        // Update button
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = new JButton("Update Student");
        add(updateButton, gbc);

        // Event handlers
        studentComboBox.addActionListener(e -> populateFields());

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect updated data
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String major = majorField.getText().trim();

                // Input validation
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(UpdateStudentPanel.this, "Name is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(UpdateStudentPanel.this, "Email is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(UpdateStudentPanel.this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (major.isEmpty()) {
                    JOptionPane.showMessageDialog(UpdateStudentPanel.this, "Major is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new student object
                Student student = new Student(id, name, email, major);

                // Update student using controller
                StudentController.updateStudent(student);

                // Show confirmation
                JOptionPane.showMessageDialog(UpdateStudentPanel.this, "Student updated successfully!");
            }
        });

        // Initialize fields
        populateFields();
    }

    private void populateFields() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        if (selectedStudent != null) {
            idField.setText(selectedStudent.getId());
            nameField.setText(selectedStudent.getName());
            emailField.setText(selectedStudent.getEmail());
            majorField.setText(selectedStudent.getMajor());
        }
    }

    private boolean isValidEmail(String email) {
        // Simple regex for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}