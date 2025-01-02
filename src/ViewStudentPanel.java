// File: view/ViewStudentPanel.java
package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import model.Student;
import controller.StudentController;
import java.util.List;

public class ViewStudentPanel extends JPanel {
    private JTable table; // Move table to class level
    private final JTextField searchField;
    private final JButton searchButton;
    private final List<Student> students; // List to hold students

    public ViewStudentPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Student Details"));

        // Top panel for search bar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // Initialize students list
        students = StudentController.getAllStudents();

        // Create table
        updateTable(students);

        // Add action listener for search
        searchButton.addActionListener(e -> performSearch());
    }

    private void updateTable(List<Student> studentList) {
        String[] columnNames = {"Student ID", "Name", "Email", "Major"};

        // Convert list to array for JTable
        String[][] data = new String[studentList.size()][4];
        for(int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getName();
            data[i][2] = s.getEmail();
            data[i][3] = s.getMajor();
        }

        // Create table with data
        table = new JTable(data, columnNames);

        // Set custom renderer for better visual appeal
        table.setDefaultRenderer(Object.class, new CustomCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void performSearch() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            updateTable(StudentController.getAllStudents());
            return;
        }

        // Filter students based on query
        List<Student> filteredStudents = StudentController.searchStudents(query);
        updateTable(filteredStudents);
    }

    // Custom cell renderer for enhanced visual appeal
    private class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Alternate row colors
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 240));
                } else {
                    c.setBackground(Color.WHITE);
                }
            } else {
                c.setBackground(new Color(184, 207, 229));
            }

            // Center align text
            setHorizontalAlignment(JLabel.CENTER);

            return c;
        }
    }
}