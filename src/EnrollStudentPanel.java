// File: view/EnrollStudentPanel.java
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Student;
import model.Course;
import model.Enrollment;
import controller.StudentController;
import controller.CourseController;

public class EnrollStudentPanel extends JPanel {
    private final JComboBox<Student> studentComboBox;
    private final JComboBox<Course> courseComboBox;
    private final JButton enrollButton;

    public EnrollStudentPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Enroll Student in Course"));

        // Student selection
        add(new JLabel("Select Student:"));
        studentComboBox = new JComboBox<>();
        for (Student s : StudentController.getAllStudents()) {
            studentComboBox.addItem(s);
        }
        add(studentComboBox);

        // Course selection
        add(new JLabel("Select Course:"));
        courseComboBox = new JComboBox<>();
        for (Course c : CourseController.getAllCourses()) {
            courseComboBox.addItem(c);
        }
        add(courseComboBox);

        // Enroll button
        enrollButton = new JButton("Enroll Student");
        add(new JLabel()); // Empty cell
        add(enrollButton);

        // Event handler
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student selectedStudent = (Student) studentComboBox.getSelectedItem();
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();

                if (selectedStudent == null || selectedCourse == null) {
                    JOptionPane.showMessageDialog(EnrollStudentPanel.this, "Please select a student and a course.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if student is already enrolled in the course
                for (Enrollment enrollment : CourseController.getEnrollments()) {
                    if (enrollment.getStudent().equals(selectedStudent) && enrollment.getCourse().equals(selectedCourse)) {
                        JOptionPane.showMessageDialog(EnrollStudentPanel.this, "Student is already enrolled in this course.", "Enrollment Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Enrollment enrollment = new Enrollment(selectedStudent, selectedCourse);
                CourseController.enrollStudent(enrollment);

                JOptionPane.showMessageDialog(EnrollStudentPanel.this, "Student enrolled successfully!");

            }
        });
    }
}