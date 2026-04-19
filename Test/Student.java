import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private String fullName;
    private String studentId;
    private List<Semester> semesters;

    public Student(String fullName, String studentId) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.semesters = new ArrayList<>();
    }

    // GETTERS
    public String getFullName() {
        return fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    // ADD SEMESTER
    public void addSemester(Semester sem) {
        if (sem != null) {
            semesters.add(sem);
        }
    }

    // 🔥 TÍNH GPA TOÀN BỘ
    public double calculateOverallGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Semester sem : semesters) {
            for (Course c : sem.getCourses()) {
                if (c.getStatus().equals("Completed")) {
                    totalPoints += c.getGrade4() * c.getCredits();
                    totalCredits += c.getCredits();
                }
            }
        }

        if (totalCredits > 0) {
            return totalPoints / totalCredits;
        } else {
            return 0.0;
        }
    }

    // 🔥 XẾP LOẠI HỌC LỰC
    public String getAcademicStanding() {
        double gpa = calculateOverallGPA();

        if (gpa >= 3.5) {
            return "Excellent";
        } else if (gpa >= 3.0) {
            return "Good";
        } else if (gpa >= 2.0) {
            return "Average";
        } else {
            return "Weak";
        }
    }
}