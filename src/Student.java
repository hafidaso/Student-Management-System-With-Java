// File: model/Student.java
package model;

public class Student {
    private String id;
    private String name;
    private String email;
    private String major;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(String id, String name, String email, String major) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    // Override toString() for display purposes
    @Override
    public String toString() {
        return id + " - " + name;
    }
}