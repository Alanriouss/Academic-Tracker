package Module.main.java.Module;

import java.io.Serializable;
public class Course implements Serializable{
    private String courseName;
    private int credits;
    private String status;
    private double grade;
    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = (credits > 0) ? credits : 0;
        this.status = "Active";
        this.grade = 0.0;
    }
    public void updateDetails(String newName, int newCredits) {
        if (newName != null && !newName.isEmpty()) {
            this.courseName = newName;
        }
        if (newCredits > 0) {
            this.credits = newCredits;
        }
    }
    public String getCourseName() { 
        return courseName; 
    }
    public int getCredits() { 
        return credits; 
    }
    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) {
        if (status.equals("Active") || status.equals("Completed") || status.equals("Dropped")) {
            this.status = status;
        }
    }
    public double getGrade() { 
        return grade; 
    }
    public void setGrade(double grade) { 
        this.grade = grade; 
    }
    public double getGrade4() {
        if (grade >= 90) return 4.0; // A
        if (grade >= 85) return 3.7; // A-
        if (grade >= 80) return 3.5; // B+
        if (grade >= 70) return 3.0; // B
        if (grade >= 65) return 2.5; // C+
        if (grade >= 50) return 2.0; // C
        if (grade >= 45) return 1.5; // D+
        if (grade >= 40) return 1.0; // D
        return 0.0; // F
}
    public void displayCourseInfo() {
        System.out.println(courseName + " (" + credits + " Credits) - Status: " + status);
    }
}