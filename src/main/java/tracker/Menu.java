package tracker;
import java.util.Scanner;
public abstract class Menu {
    protected Scanner scanner = new Scanner(System.in);
    public abstract void display();
    public abstract void handleInput();
}