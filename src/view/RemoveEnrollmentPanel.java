// File: view/RemoveEnrollmentPanel.java
package view;

import javax.swing.*;
import java.awt.*;

import model.Student;
import model.Course;
import model.Enrollment;
import controller.CourseController;

import java.util.ArrayList;
import java.util.List;

public class RemoveEnrollmentPanel extends JPanel {
    private final JComboBox<Student> studentComboBox;
    private final JComboBox<Course> courseComboBox;
    private final JButton removeButton;

    public RemoveEnrollmentPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Remove Enrollment"));

        // Student selection
        add(new JLabel("Select Student:"));
        studentComboBox = new JComboBox<>();
        for (Student s : getEnrolledStudents()) {
            studentComboBox.addItem(s);
        }
        add(studentComboBox);

        // Course selection
        add(new JLabel("Select Course:"));
        courseComboBox = new JComboBox<>();
        add(courseComboBox);

        // Remove button
        removeButton = new JButton("Remove Enrollment");
        add(new JLabel()); // Empty cell
        add(removeButton);

        // Event handlers
        studentComboBox.addActionListener(e -> updateCourseComboBox());
        removeButton.addActionListener(e -> removeEnrollment());

        // Initialize course combo box
        updateCourseComboBox();
    }

    private List<Student> getEnrolledStudents() {
        // Get unique list of students who are enrolled
        List<Student> students = new ArrayList<>();
        for (Enrollment e : CourseController.getEnrollments()) {
            if (!students.contains(e.getStudent())) {
                students.add(e.getStudent());
            }
        }
        return students;
    }

    private void updateCourseComboBox() {
        courseComboBox.removeAllItems();
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        if (selectedStudent != null) {
            List<Enrollment> enrollments = CourseController.getEnrollmentsByStudent(selectedStudent);
            for (Enrollment e : enrollments) {
                courseComboBox.addItem(e.getCourse());
            }
        }
    }

    private void removeEnrollment() {
        Student student = (Student) studentComboBox.getSelectedItem();
        Course course = (Course) courseComboBox.getSelectedItem();
        if (student == null || course == null) {
            JOptionPane.showMessageDialog(RemoveEnrollmentPanel.this, "Please select a student and a course.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(RemoveEnrollmentPanel.this, "Are you sure you want to remove this enrollment?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = CourseController.removeEnrollment(student, course);
            if (success) {
                JOptionPane.showMessageDialog(RemoveEnrollmentPanel.this, "Enrollment removed successfully.");
                updateCourseComboBox();
            } else {
                JOptionPane.showMessageDialog(RemoveEnrollmentPanel.this, "Error removing enrollment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}