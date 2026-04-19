import java.util.*;

public class Stage3 {
    static Scanner sc = new Scanner(System.in);

    public static void run(Semester sem) {
        while (true) {
            System.out.println("===================================================");
            System.out.println("[ TERM MANAGEMENT: " + sem.getAcademicYear() + " - " + getTermName(sem.getType()) + " ]");

            double gpa = getTermGPA(sem);
            System.out.printf("Term GPA: %.2f\n", gpa);

            System.out.println("===================================================");

            List<Course> list = sem.getCourses();

            System.out.println("CURRENT COURSES:");

            if (list.isEmpty()) {
                System.out.println("(No courses yet)");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Course c = list.get(i);

                    System.out.printf("[%d] %s (%d Credits) - Status: %s\n",
                            i + 1,
                            c.getCourseName(),
                            c.getCredits(),
                            c.getStatus());
                }
            }

            System.out.println("--------------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("[M] Manage a Course's Grades (Select by number)");
            System.out.println("[1] Add a New Course");
            System.out.println("[2] Edit Course Details (Name/Credits)");
            System.out.println("[3] Update Course Status (Active/Completed/Dropped)");
            System.out.println("[4] Delete a Course");
            System.out.println("[0] <-- Back to Dashboard");
            System.out.println("===================================================");
            System.out.print("> ");

            String ch = sc.nextLine();

            if (ch.equals("1")) addCourse(sem);
            else if (ch.equals("2")) editCourse(sem);
            else if (ch.equals("3")) updateStatus(sem);
            else if (ch.equals("4")) deleteCourse(sem);
            else if (ch.equals("M")) manageGrades(sem);
            else if (ch.equals("0")) return;
        }
    }

    static void addCourse(Semester sem) {
        System.out.print("Course name: ");
        String name = sc.nextLine();

        System.out.print("Credits: ");
        int cr = Integer.parseInt(sc.nextLine());

        sem.addCourse(new Course(name, cr));
    }

    static void editCourse(Semester sem) {
        List<Course> list = sem.getCourses();
        if (list.isEmpty()) return;

        System.out.print("Course number: ");
        int i = Integer.parseInt(sc.nextLine()) - 1;

        if (i < 0 || i >= list.size()) return;

        Course c = list.get(i);

        System.out.print("New name (ENTER skip): ");
        String name = sc.nextLine();

        System.out.print("New credits (0 skip): ");
        int cr = Integer.parseInt(sc.nextLine());

        c.updateDetails(name, cr);
    }

    static void updateStatus(Semester sem) {
        List<Course> list = sem.getCourses();
        if (list.isEmpty()) return;

        System.out.print("Course number: ");
        int i = Integer.parseInt(sc.nextLine()) - 1;

        if (i < 0 || i >= list.size()) return;

        System.out.print("[1] Active [2] Completed [3] Dropped: ");
        String ch = sc.nextLine();

        if (ch.equals("1")) list.get(i).setStatus("Active");
        else if (ch.equals("2")) list.get(i).setStatus("Completed");
        else if (ch.equals("3")) list.get(i).setStatus("Dropped");
    }

    static void deleteCourse(Semester sem) {
        List<Course> list = sem.getCourses();
        if (list.isEmpty()) return;

        System.out.print("Course number: ");
        int i = Integer.parseInt(sc.nextLine()) - 1;

        if (i < 0 || i >= list.size()) return;

        list.remove(i);
    }

    static void manageGrades(Semester sem) {
        List<Course> list = sem.getCourses();
        if (list.isEmpty()) return;

        System.out.print("Course number: ");
        int i = Integer.parseInt(sc.nextLine()) - 1;

        if (i < 0 || i >= list.size()) return;

        System.out.print("Enter grade (0-100): ");
        double g = Double.parseDouble(sc.nextLine());

        Course c = list.get(i);
        c.setGrade(g);
        c.setStatus("Completed");
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

        if (totalCredits > 0) return totalPoints / totalCredits;
        return 0.0;
    }
}
