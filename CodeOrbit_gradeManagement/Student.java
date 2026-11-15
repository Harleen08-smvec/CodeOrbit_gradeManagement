/**
 * Student.java
 * Represents an individual student with ID, Name, and Marks.
 * Calculates letter grade and pass/fail status based on marks.
 */
public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    /**
     * Determines letter grade based on marks.
     * 90 - 100 : A+
     * 80 - 89.9: A
     * 70 - 79.9: B
     * 60 - 69.9: C
     * 50 - 59.9: D
     * Below 50 : F
     */
    public String getGrade() {
        if (marks >= 90.0) return "A+";
        if (marks >= 80.0) return "A";
        if (marks >= 70.0) return "B";
        if (marks >= 60.0) return "C";
        if (marks >= 50.0) return "D";
        return "F";
    }

    /**
     * Checks if the student passed (marks >= 50.0).
     */
    public boolean isPassed() {
        return marks >= 50.0;
    }

    public String getStatus() {
        return isPassed() ? "PASS" : "FAIL";
    }

    public String getRemark() {
        if (marks >= 90.0) return "Outstanding";
        if (marks >= 80.0) return "Excellent";
        if (marks >= 70.0) return "Very Good";
        if (marks >= 60.0) return "Good";
        if (marks >= 50.0) return "Satisfactory";
        return "Needs Improvement";
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d | Name: %-20s | Marks: %6.2f | Grade: %-2s | Status: %-4s",
                id, name, marks, getGrade(), getStatus());
    }
}
