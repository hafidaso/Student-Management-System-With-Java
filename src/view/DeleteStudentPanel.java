// File: view/DeleteStudentPanel.java
package view;

import javax.swing.*;
import java.awt.*;

import model.Student;
import controller.StudentController;

public class DeleteStudentPanel extends JPanel {
    private final JComboBox<Student> studentComboBox;
    private final JButton deleteButton;

    public DeleteStudentPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Delete Student"));

        // Student selection
        add(new JLabel("Select Student to Delete:"));
        studentComboBox = new JComboBox<>();
        for (Student s : StudentController.getAllStudents()) {
            studentComboBox.addItem(s);
        }
        add(studentComboBox);

        // Delete button
        deleteButton = new JButton("Delete Student");
        add(new JLabel()); // Empty cell
        add(deleteButton);

        // Event handler
        deleteButton.addActionListener(e -> {
            Student selectedStudent = (Student) studentComboBox.getSelectedItem();
            if (selectedStudent != null) {
                int confirmation = JOptionPane.showConfirmDialog(DeleteStudentPanel.this, "Are you sure you want to delete this student?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    boolean success = StudentController.deleteStudent(selectedStudent.getId());
                    if (success) {
                        JOptionPane.showMessageDialog(DeleteStudentPanel.this, "Student deleted successfully.");
                        studentComboBox.removeItem(selectedStudent);
                    } else {
                        JOptionPane.showMessageDialog(DeleteStudentPanel.this, "Error deleting student.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}