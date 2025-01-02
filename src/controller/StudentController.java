// File: controller/StudentController.java
package controller;

import model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private static final List<Student> studentList = new ArrayList<>();

    public static void addStudent(Student student) {
        studentList.add(student);
    }

    public static List<Student> getAllStudents() {
        return studentList;
    }

    public static void updateStudent(Student student) {
        // Find the student by ID and update details
        for (Student s : studentList) {
            if (s.getId().equals(student.getId())) {
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setMajor(student.getMajor());
                break;
            }
        }
    }

    public static boolean deleteStudent(String studentId) {
        // Remove student from list
        return studentList.removeIf(s -> s.getId().equals(studentId));
    }

    public static List<Student> searchStudents(String query) {
        List<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getId().toLowerCase().contains(query) || s.getName().toLowerCase().contains(query)) {
                result.add(s);
            }
        }
        return result;
    }
}