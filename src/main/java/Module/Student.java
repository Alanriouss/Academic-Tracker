package Module.main.java.Module;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class Student implements Serializable {
    private String fullName;
    private String studentId;
    private List<Semester> semesters;
    private String academicStanding;
    public Student(String fullName, String studentId) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.semesters = new ArrayList<>();
        this.academicStanding = "Good";
    }
    public String getFullName() {
        return fullName;
    }
    public String getStudentId() {
        return studentId;
    }
    public List<Semester> getSemesters() {
        return semesters;
    }
    public void addSemester(Semester sem) {
        this.semesters.add(sem);
    }
    public void displayProfile(double currentGPA) {
        System.out.println("\n" + "=".repeat(20));
        System.out.println("STUDENT PROFILE");
        System.out.println("Name: " + fullName);
        System.out.println("ID:   " + studentId);
        System.out.println("Standing: " + academicStanding);
        System.out.println("Overall GPA: " + String.format("%.2f", currentGPA));
        System.out.println("=".repeat(20));
    }
}