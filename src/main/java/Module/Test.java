package Module;

import java.util.Scanner;
import java.util.List;
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ACADEMIC TRACKER v1.0");
        Student userStudent = FileManager.loadProfile();
        if (userStudent == null) {
            System.out.println("[SYSTEM] Initializing first-time setup...");
            System.out.print("Enter Full Name: > ");
            String name = scanner.nextLine();
            System.out.print("Enter Student ID: > ");
            String id = scanner.nextLine();
            userStudent = new Student(name, id);
            System.out.println("[SUCCESS] Profile for " + name + " (" + id + ") created!\n");
        } else {
            System.out.println("Welcome back, " + userStudent.getFullName() + "!\n");
        }
        boolean globalExit = false;
        while (!globalExit) {
            System.out.println(">> ACADEMIC DASHBOARD");
            List<Semester> sems = userStudent.getSemesters();
            if (sems.isEmpty()) {
                System.out.println("[INFO] No terms found. Please create one.");
            } else {
                for (int i = 0; i < sems.size(); i++) {
                    Semester s = sems.get(i);
                    String typeLabel = (s.getType() == 1) ? "Sem 1" : (s.getType() == 2) ? "Sem 2" : "Summer";
                    System.out.println("[" + (i + 1) + "] " + s.getAcademicYear() + " - " + typeLabel);
                }
            }
            System.out.println("\n[N] New Term  [W] Save  [R] Reset  [0] Exit");
            System.out.print("Select a number to manage or an option: > ");
            String choice = scanner.next().toUpperCase();

            if (choice.equals("0")) {
                System.out.println("[SYSTEM] Saving data and closing...");
                FileManager.saveProfile(userStudent);
                globalExit = true;
            } 
            else if (choice.equals("N")) {
                System.out.println("\n>> CREATE NEW TERM");
                System.out.print("Enter Academic Year (e.g., 2026): > ");
                int year = scanner.nextInt();
                System.out.print("Select Type: [1] Sem 1, [2] Sem 2, [3] Summer Sem > ");
                int type = scanner.nextInt();
                Semester newSemester = new Semester(year, type);
                userStudent.addSemester(newSemester);
                System.out.println("[SUCCESS] Term created!\n");
            } 
            else if (choice.equals("W")) {
                FileManager.saveProfile(userStudent);
            } 
            else if (choice.equals("R")) {
                System.out.print("Confirm Reset (Y/N): > ");
                if (scanner.next().equalsIgnoreCase("Y")) {
                    FileManager.deleteSaveFile();
                    return;
                }
            } 
            else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < sems.size()) {
                        manageSemester(sems.get(index), scanner);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Invalid selection.");
                }
            }
        }
        scanner.close();
    }
    public static void manageSemester(Semester currentSem, Scanner scanner) {
        boolean backToDash = false;
        while (!backToDash) {
            System.out.println("\n--- TERM MANAGEMENT ---");
            currentSem.displayTermDetails();
            System.out.println("\n[A] Add Course [E] Edit [S] Status [D] Delete [B] Back");
            System.out.print("Selection: > ");
            String action = scanner.next().toUpperCase();
            List<Course> courses = currentSem.getCourses();
            switch (action) {
                case "A":
                    System.out.println(">> ADD NEW COURSE");
                    scanner.nextLine();
                    System.out.print("Course Name: > ");
                    String cName = scanner.nextLine();
                    System.out.print("Credits: > ");
                    int cCredits = scanner.nextInt();
                    currentSem.addCourse(new Course(cName, cCredits));
                    System.out.println("[SUCCESS] Course added.");
                    break;
                case "E":
                    System.out.print("Enter Course Number to Edit: > ");
                    int eIdx = scanner.nextInt() - 1;
                    if (eIdx >= 0 && eIdx < courses.size()) {
                        scanner.nextLine();
                        System.out.print("New Name: > ");
                        String nName = scanner.nextLine();
                        System.out.print("New Credits: > ");
                        int nCreds = scanner.nextInt();
                        courses.get(eIdx).updateDetails(nName, nCreds);
                        currentSem.calculateTermGPA();
                    }
                    break;
                case "S":
                    System.out.print("Enter Course Number: > ");
                    int sIdx = scanner.nextInt() - 1;
                    if (sIdx >= 0 && sIdx < courses.size()) {
                        System.out.print("Status ([1]Active [2]Complete [3]Dropped): > ");
                        int sChoice = scanner.nextInt();
                        String status = (sChoice == 2) ? "Completed" : (sChoice == 3) ? "Dropped" : "Active";
                        courses.get(sIdx).setStatus(status);
                        if (status.equals("Completed")) {
                            System.out.print("Enter Grade (0-100): > ");
                            courses.get(sIdx).setGrade(scanner.nextDouble());
                        }
                        currentSem.calculateTermGPA();
                    }
                    break;
                case "D":
                    System.out.print("Delete Course Number: > ");
                    int dIdx = scanner.nextInt() - 1;
                    if (dIdx >= 0 && dIdx < courses.size()) {
                        courses.remove(dIdx);
                        currentSem.calculateTermGPA();
                        System.out.println("[SUCCESS] Removed.");
                    }
                    break;
                case "B":
                    backToDash = true;
                    break;
            }
        }
    }
}