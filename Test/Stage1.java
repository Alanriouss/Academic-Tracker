import java.io.*;
import java.util.*;

public class Stage1 {
    static final String FILE = "student.dat";
    static Scanner sc = new Scanner(System.in);

    public static Student run() {
        System.out.println("====================================");
        System.out.println("       ACADEMIC TRACKER v1.0        ");
        System.out.println("====================================");

        File f = new File(FILE);

        if (!f.exists()) {
            System.out.println("[SYSTEM] No existing profile found.");
            System.out.println("[SYSTEM] Initializing first-time setup...\n");

            System.out.print("Enter Full Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();

            Student s = new Student(name, id);

            save(s);

            System.out.println("\n[SUCCESS] Profile created!");
            System.out.println("[SYSTEM] Booting Dashboard...\n");

            return s;
        }

        System.out.println("[SYSTEM] Save data found. Loading profile...\n");

        Student s = load();

        System.out.println("WELCOME BACK, " + s.getFullName());
        System.out.println("Student ID: " + s.getStudentId());
        System.out.println("\nPress ENTER to continue...");
        sc.nextLine();

        return s;
    }

    static void save(Student s) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(s);
        } catch (Exception e) {
            System.out.println("[ERROR] Save failed!");
        }
    }

    static Student load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (Student) ois.readObject();
        } catch (Exception e) {
            System.out.println("[ERROR] Load failed!");
        }
        return null;
    }
}
