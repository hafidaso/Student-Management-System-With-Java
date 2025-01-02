// File: controller/CourseController.java
package controller;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseController {
    private static final List<Course> courseList = new ArrayList<>();
    private static final List<Enrollment> enrollmentList = new ArrayList<>();

    static {
        // Initialize with sample courses
        courseList.add(new Course("CSE101", "Introduction to Computer Science"));
        courseList.add(new Course("MTH101", "Calculus I"));
        courseList.add(new Course("PHY101", "Physics I"));
    }

    public static List<Course> getAllCourses() {
        return courseList;
    }

    public static void enrollStudent(Enrollment enrollment) {
        enrollmentList.add(enrollment);
    }

    public static List<Enrollment> getEnrollments() {
        return enrollmentList;
    }

    // Method to get enrollments for a specific student
    public static List<Enrollment> getEnrollmentsByStudent(Student student) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollmentList) {
            if (e.getStudent().equals(student)) {
                result.add(e);
            }
        }
        return result;
    }

    public static boolean removeEnrollment(Student student, Course course) {
        return enrollmentList.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
    }

    public static List<Course> searchCourses(String query) {
        List<Course> result = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getCourseId().toLowerCase().contains(query) || c.getCourseName().toLowerCase().contains(query)) {
                result.add(c);
            }
        }
        return result;
    }
}