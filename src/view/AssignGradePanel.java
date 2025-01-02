// File: view/AssignGradePanel.java
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Student;
import model.Course;
import model.Enrollment;
import controller.CourseController;

import java.util.List;

public class AssignGradePanel extends JPanel {
    private final JComboBox<Student> studentComboBox;
    private final JComboBox<Course> courseComboBox;
    private final JTextField gradeField;
    private final JButton assignButton;

    public AssignGradePanel() {
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Assign Grade to Student"));

        // Student selection
        add(new JLabel("Select Student:"));
        studentComboBox = new JComboBox<>();
        // Populate with students who have enrollments
        for (Enrollment e : CourseController.getEnrollments()) {
            Student student = e.getStudent();
            if (((DefaultComboBoxModel<Student>) studentComboBox.getModel()).getIndexOf(student) == -1) {
                studentComboBox.addItem(student);
            }
        }
        add(studentComboBox);

        // Course selection based on selected student
        add(new JLabel("Select Course:"));
        courseComboBox = new JComboBox<>();
        add(courseComboBox);

        // Grade input
        add(new JLabel("Assign Grade:"));
        gradeField = new JTextField();
        add(gradeField);

        // Assign button
        assignButton = new JButton("Assign Grade");
        add(new JLabel()); // Empty cell
        add(assignButton);

        // Event handlers
        studentComboBox.addActionListener(e -> updateCourseComboBox());
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = (Student) studentComboBox.getSelectedItem();
                Course course = (Course) courseComboBox.getSelectedItem();
                String grade = gradeField.getText().trim();

                if (student == null || course == null || grade.isEmpty()) {
                    JOptionPane.showMessageDialog(AssignGradePanel.this, "Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Find the enrollment and assign grade
                for (Enrollment enrollment : CourseController.getEnrollments()) {
                    if (enrollment.getStudent().equals(student) && enrollment.getCourse().equals(course)) {
                        enrollment.setGrade(grade);
                        JOptionPane.showMessageDialog(AssignGradePanel.this, "Grade assigned successfully!");
                        gradeField.setText("");
                        break;
                    }
                }
            }
        });

        // Initialize course combo box
        updateCourseComboBox();
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
}