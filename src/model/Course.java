// File: model/Course.java
package model;

public class Course {
    private String courseId;
    private String courseName;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    // Getter and Setter for courseId
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    // Getter and Setter for courseName
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Override toString() for display purposes
    @Override
    public String toString() {
        return courseId + " - " + courseName;
    }
}