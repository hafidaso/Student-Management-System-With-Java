// File: view/MainFrame.java
package view;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class MainFrame extends JFrame {

    private JPanel mainPanel;

    // Declare menu items at class level
    private JMenuItem addStudentItem;
    private JMenuItem updateStudentItem;
    private JMenuItem viewStudentItem;
    private JMenuItem deleteStudentItem;
    private JMenuItem enrollStudentItem;
    private JMenuItem removeEnrollmentItem;
    private JMenuItem assignGradeItem;
    private JMenuItem viewEnrollmentsItem;
    private JMenuItem viewCourseItem;
    private JMenuItem performanceReportItem;

    public MainFrame() {
        setTitle("Student Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the GUI components
        initMenuBar();
        initMainPanel();

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Student Menu
        JMenu studentMenu = new JMenu("Student");
        addStudentItem = new JMenuItem("Add Student");
        updateStudentItem = new JMenuItem("Update Student");
        viewStudentItem = new JMenuItem("View Student Details");
        deleteStudentItem = new JMenuItem("Delete Student");
        viewEnrollmentsItem = new JMenuItem("View Enrollments");

        studentMenu.add(addStudentItem);
        studentMenu.add(updateStudentItem);
        studentMenu.add(viewStudentItem);
        studentMenu.add(deleteStudentItem);
        studentMenu.add(viewEnrollmentsItem);

        // Course Menu
        JMenu courseMenu = new JMenu("Course");
        enrollStudentItem = new JMenuItem("Enroll Student");
        removeEnrollmentItem = new JMenuItem("Remove Enrollment");
        viewCourseItem = new JMenuItem("View Courses");

        courseMenu.add(enrollStudentItem);
        courseMenu.add(removeEnrollmentItem);
        courseMenu.add(viewCourseItem);

        // Grade Menu
        JMenu gradeMenu = new JMenu("Grade");
        assignGradeItem = new JMenuItem("Assign Grade");

        gradeMenu.add(assignGradeItem);

        // Reports Menu
        JMenu reportsMenu = new JMenu("Reports");
        performanceReportItem = new JMenuItem("Student Performance");

        reportsMenu.add(performanceReportItem);

        // Add menus to the menu bar
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(gradeMenu);
        menuBar.add(reportsMenu);

        setJMenuBar(menuBar);

        // Add action listeners for menu items
        addStudentItem.addActionListener(e -> showAddStudentPanel());
        updateStudentItem.addActionListener(e -> showUpdateStudentPanel());
        viewStudentItem.addActionListener(e -> showViewStudentPanel());
        deleteStudentItem.addActionListener(e -> showDeleteStudentPanel());
        viewEnrollmentsItem.addActionListener(e -> showViewEnrollmentsPanel());
        enrollStudentItem.addActionListener(e -> showEnrollStudentPanel());
        removeEnrollmentItem.addActionListener(e -> showRemoveEnrollmentPanel());
        viewCourseItem.addActionListener(e -> showViewCoursePanel());
        assignGradeItem.addActionListener(e -> showAssignGradePanel());
        performanceReportItem.addActionListener(e -> showReportsPanel());
    }

    private void initMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }

    private void showAddStudentPanel() {
        mainPanel.removeAll();
        mainPanel.add(new AddStudentPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showUpdateStudentPanel() {
        mainPanel.removeAll();
        mainPanel.add(new UpdateStudentPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showViewStudentPanel() {
        mainPanel.removeAll();
        mainPanel.add(new ViewStudentPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showDeleteStudentPanel() {
        mainPanel.removeAll();
        mainPanel.add(new DeleteStudentPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showEnrollStudentPanel() {
        mainPanel.removeAll();
        mainPanel.add(new EnrollStudentPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showRemoveEnrollmentPanel() {
        mainPanel.removeAll();
        mainPanel.add(new RemoveEnrollmentPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showViewEnrollmentsPanel() {
        mainPanel.removeAll();
        mainPanel.add(new ViewEnrollmentsPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showAssignGradePanel() {
        mainPanel.removeAll();
        mainPanel.add(new AssignGradePanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showReportsPanel() {
        mainPanel.removeAll();
        mainPanel.add(new ReportsPanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showViewCoursePanel() {
        mainPanel.removeAll();
        mainPanel.add(new ViewCoursePanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}