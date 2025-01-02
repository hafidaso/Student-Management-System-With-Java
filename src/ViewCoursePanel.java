// File: view/ViewCoursePanel.java
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Course;
import controller.CourseController;
import java.util.List;

public class ViewCoursePanel extends JPanel {
    private JTable table;
    private final JTextField searchField;
    private final JButton searchButton;
    private final List<Course> courses;

    public ViewCoursePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Course Details"));

        // Top panel for search bar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // Initialize courses list
        courses = CourseController.getAllCourses();

        // Create table
        updateTable(courses);

        // Add action listener for search
        searchButton.addActionListener(e -> performSearch());
    }

    private void updateTable(List<Course> courseList) {
        String[] columnNames = {"Course ID", "Course Name"};

        // Convert list to array for JTable
        String[][] data = new String[courseList.size()][2];
        for(int i = 0; i < courseList.size(); i++) {
            Course c = courseList.get(i);
            data[i][0] = c.getCourseId();
            data[i][1] = c.getCourseName();
        }

        // Create table with data
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void performSearch() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            updateTable(CourseController.getAllCourses());
            return;
        }

        // Filter courses based on query
        List<Course> filteredCourses = CourseController.searchCourses(query);
        updateTable(filteredCourses);
    }
}