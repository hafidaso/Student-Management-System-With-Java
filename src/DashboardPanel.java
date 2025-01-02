// File: view/DashboardPanel.java
package view;

import javax.swing.*;
import java.awt.*;
import controller.StudentController;
import controller.CourseController;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Example summary cards
        JPanel studentsCard = createSummaryCard("Total Students", "icons/students.png", StudentController.getAllStudents().size());
        JPanel coursesCard = createSummaryCard("Total Courses", "icons/courses.png", CourseController.getAllCourses().size());
        JPanel enrollmentsCard = createSummaryCard("Total Enrollments", "icons/enrollments.png", CourseController.getEnrollments().size());
        JPanel gradesCard = createSummaryCard("Average Grade", "icons/grades.png", calculateAverageGrade());

        add(studentsCard);
        add(coursesCard);
        add(enrollmentsCard);
        add(gradesCard);
    }

    private JPanel createSummaryCard(String title, String iconPath, Object value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // Set icon if available
        ImageIcon icon = new ImageIcon(iconPath);
        if (icon.getIconWidth() > 0) {
            titleLabel.setIcon(icon);
            titleLabel.setHorizontalTextPosition(JLabel.CENTER);
            titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        }

        JLabel valueLabel = new JLabel(value.toString(), JLabel.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        card.add(titleLabel, BorderLayout.CENTER);
        card.add(valueLabel, BorderLayout.SOUTH);

        return card;
    }

    private String calculateAverageGrade() {
        double totalGrades = 0;
        int gradeCount = 0;
        for (var enrollment : CourseController.getEnrollments()) {
            if (enrollment.getGrade() != null) {
                try {
                    double grade = Double.parseDouble(enrollment.getGrade());
                    totalGrades += grade;
                    gradeCount++;
                } catch (NumberFormatException ex) {
                    // Ignore non-numeric grades
                }
            }
        }
        double average = gradeCount > 0 ? totalGrades / gradeCount : 0.0;
        return gradeCount > 0 ? String.format("%.2f", average) : "N/A";
    }
}