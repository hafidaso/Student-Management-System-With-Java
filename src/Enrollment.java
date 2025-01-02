// File: model/Enrollment.java
package model;

public class Enrollment {
    private Student student;
    private Course course;
    private String grade; // Grade can be null if not assigned yet

    // Default constructor
    public Enrollment() {
    }

    // Parameterized constructor
    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = null; // Grade not assigned yet
    }

    // Getters and setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}