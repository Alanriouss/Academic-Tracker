package tracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Semester implements Serializable {
    private int academicYear;
    private int type;
    private List<Course> courses;
    private double termGPA4;
    private double termGPA100;
    public Semester(int academicYear, int type) {
        this.academicYear = academicYear;
        this.type = type;
        this.courses = new ArrayList<>();
    }
public void addCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
            calculateTermGPA();
        }
    }
    public int getAcademicYear() { return academicYear; }
    public int getType() { return type; }
    public List<Course> getCourses() { return courses; }
    public void calculateTermGPA() {
        double totalPoints4 = 0;
        double totalPoints100 = 0;
        int totalCredits = 0;
        for (Course c : courses) {
            if ("Completed".equals(c.getStatus())) {
                totalPoints4 += (c.getGrade4() * c.getCredits());
                totalPoints100 += (c.getGrade() * c.getCredits());
                totalCredits += c.getCredits();
            }
        }
        this.termGPA4 = (totalCredits > 0) ? (totalPoints4 / totalCredits) : 0.0;
        this.termGPA100 = (totalCredits > 0) ? (totalPoints100 / totalCredits) : 0.0;
    }
    public void displayTermDetails() {
        String typeLabel = (type == 1) ? "Sem 1" : (type == 2) ? "Sem 2" : "Summer";
        System.out.println("\n[ TERM: " + academicYear + " - " + typeLabel + " ]");
        System.out.println("GPA (4.0): " + String.format("%.2f", termGPA4));
        System.out.println("GPA (100.0): " + String.format("%.2f", termGPA100));
        for (int i = 0; i < courses.size(); i++) {
            System.out.print("[" + (i + 1) + "] ");
            courses.get(i).displayCourseInfo();
        }
    }
}