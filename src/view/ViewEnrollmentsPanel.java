// File: view/ViewEnrollmentsPanel.java
package view;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import model.Student;
import model.Enrollment;
import model.Course;
import controller.CourseController;

public class ViewEnrollmentsPanel extends JPanel {
    private final JComboBox<Student> studentComboBox;
    private JTable table;

    public ViewEnrollmentsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("View Enrollments"));

        // Top panel for student selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Student:"));

        studentComboBox = new JComboBox<>();
        for (Student s : getEnrolledStudents()) {
            studentComboBox.addItem(s);
        }
        topPanel.add(studentComboBox);

        add(topPanel, BorderLayout.NORTH);

        // Initialize table
        updateTable();

        // Event handler
        studentComboBox.addActionListener(e -> updateTable());
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

    private void updateTable() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        if (selectedStudent != null) {
            List<Enrollment> enrollments = CourseController.getEnrollmentsByStudent(selectedStudent);
            String[] columnNames = {"Course ID", "Course Name", "Grade"};

            String[][] data = new String[enrollments.size()][3];
            for (int i = 0; i < enrollments.size(); i++) {
                Enrollment e = enrollments.get(i);
                Course c = e.getCourse();
                data[i][0] = c.getCourseId();
                data[i][1] = c.getCourseName();
                data[i][2] = e.getGrade() != null ? e.getGrade() : "Not Assigned";
            }

            table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            revalidate();
            repaint();
        }
    }
}