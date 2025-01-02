package view;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import controller.StudentController;
import controller.CourseController;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Students", StudentController.getAllStudents().size());
        dataset.setValue("Courses", CourseController.getAllCourses().size());
        dataset.setValue("Enrollments", CourseController.getEnrollments().size());

        // Create chart
        JFreeChart pieChart = ChartFactory.createPieChart("System Overview", dataset, true, true, false);

        // Create ChartPanel and add to DashboardPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        add(chartPanel, BorderLayout.CENTER);
    }
}