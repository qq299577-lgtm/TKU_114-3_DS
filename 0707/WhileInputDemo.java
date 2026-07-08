import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number (0 to stop): ");
        int number = scanner.nextInt();

        while (number != 0) {
            System.out.println("You entered: " + number);
            System.out.print("Enter a number (0 to stop): ");
            number = scanner.nextInt();
        }

        scanner.close();
    }
}