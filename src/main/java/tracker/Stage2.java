package tracker;
import java.util.*;

public class Stage2 {
    static Scanner sc = new Scanner(System.in);

    public static void run(Student s) {
        while (true) {
            System.out.println("===================================================");
            System.out.println("              [ ACADEMIC DASHBOARD ]               ");
            System.out.println("Student: " + s.getFullName() + " | ID: " + s.getStudentId());

            double gpa = s.calculateOverallGPA();

            System.out.printf("Academic Standing: %s | Overall GPA: %.2f\n",
                    s.getAcademicStanding(), gpa);

            System.out.println("===================================================");

            System.out.println("Select a Term to Manage Courses:");

            List<Semester> list = s.getSemesters();

            for (int i = 0; i < list.size(); i++) {
                Semester sem = list.get(i);

                String termName = getTermName(sem.getType());
                double termGPA = getTermGPA(sem);

                System.out.printf("[%d] %d - %s (Term GPA: %.2f)\n",
                        i + 1,
                        sem.getAcademicYear(),
                        termName,
                        termGPA);
            }

            System.out.println("[+] Create a New Term");
            System.out.println("[0] Save & Exit");
            System.out.println("===================================================");
            System.out.print("> ");

            String choice = sc.nextLine();

            if (choice.equals("0")) {
                Stage1.save(s);
                System.out.println("[SYSTEM] Data saved");
                break;
            } else if (choice.equals("+")) {
                createTerm(s);
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < list.size()) {
                        Stage3.run(list.get(index));
                    } else {
                        System.out.println("[ERROR] Invalid selection!");
                    }
                } catch (Exception e) {
                    System.out.println("[ERROR] Invalid input!");
                }
            }
        }
    }

    static void createTerm(Student s) {
        System.out.println("\n>> CREATE NEW TERM");

        System.out.print("Enter Academic Year: > ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Select Type [1] Sem1 [2] Sem2 [3] Summer: > ");
        int type = Integer.parseInt(sc.nextLine());

        s.addSemester(new Semester(year, type));

        System.out.println("[SUCCESS] Term created!");
    }

    static String getTermName(int type) {
        if (type == 1) return "Sem 1";
        if (type == 2) return "Sem 2";
        return "Summer Sem";
    }

    static double getTermGPA(Semester sem) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : sem.getCourses()) {
            if (c.getStatus().equals("Completed")) {
                totalPoints += c.getGrade4() * c.getCredits();
                totalCredits += c.getCredits();
            }
        }

        if (totalCredits > 0) {
            return totalPoints / totalCredits;
        }
        return 0.0;
    }
}