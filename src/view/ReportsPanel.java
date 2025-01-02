// File: view/ReportsPanel.java
package view;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import model.Student;
import model.Enrollment;
import controller.StudentController;
import controller.CourseController;

public class ReportsPanel extends JPanel {
    public ReportsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Student Performance Report"));

        // Column names for the table
        String[] columnNames = {"Student ID", "Name", "Average Grade"};

        // Calculate average grades
        List<Student> students = StudentController.getAllStudents();
        String[][] data = new String[students.size()][3];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            List<Enrollment> enrollments = CourseController.getEnrollmentsByStudent(s);
            double totalGrades = 0;
            int gradeCount = 0;
            for (Enrollment e : enrollments) {
                if (e.getGrade() != null) {
                    try {
                        double grade = Double.parseDouble(e.getGrade());
                        totalGrades += grade;
                        gradeCount++;
                    } catch (NumberFormatException ex) {
                        // Ignore non-numeric grades
                    }
                }
            }
            double average = gradeCount > 0 ? totalGrades / gradeCount : 0;
            data[i][0] = s.getId();
            data[i][1] = s.getName();
            data[i][2] = gradeCount > 0 ? String.format("%.2f", average) : "N/A";
        }

        // Create table with data
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}