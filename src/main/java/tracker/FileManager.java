package tracker;

import java.io.*;

public class FileManager {
    private static final String FILE_NAME = "student_data.ser";
    public static void saveProfile(Student student) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(student);
            System.out.println("[SYSTEM] Progress saved successfully.");
        } catch (IOException e) {
            System.out.println("[ERROR] Could not save progress: " + e.getMessage());
        }
    }
    public static Student loadProfile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Student) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[SYSTEM] No save file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] Load failed: " + e.getMessage());
        }
        return null;
    }
    public static boolean deleteSaveFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}