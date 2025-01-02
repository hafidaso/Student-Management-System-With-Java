package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import model.Student;
import controller.StudentController;

public class AddStudentController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField majorField;

    @FXML
    private void handleAddStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String major = majorField.getText().trim();

        // Input validation
        if (id.isEmpty()) {
            showAlert("Student ID is required.");
            return;
        }

        if (name.isEmpty()) {
            showAlert("Name is required.");
            return;
        }

        if (email.isEmpty()) {
            showAlert("Email is required.");
            return;
        }

        if (!isValidEmail(email)) {
            showAlert("Please enter a valid email address.");
            return;
        }

        if (major.isEmpty()) {
            showAlert("Major is required.");
            return;
        }

        // Add student
        Student student = new Student(id, name, email, major);
        StudentController.addStudent(student);

        // Show confirmation
        showAlert("Student added successfully.", Alert.AlertType.INFORMATION);

        // Clear fields
        idField.clear();
        nameField.clear();
        emailField.clear();
        majorField.clear();
    }

    private void showAlert(String message) {
        showAlert(message, Alert.AlertType.ERROR);
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        // Simple regex for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}