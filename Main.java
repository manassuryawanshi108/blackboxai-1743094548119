import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Simple text-based login
        System.out.println("=== Timetable System ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (username.equals("admin") && password.equals("password")) {
            System.out.println("\nLogin successful!");
            showTimetableMenu(scanner);
        } else {
            System.out.println("Invalid credentials");
        }
    }
    
    private static void showTimetableMenu(Scanner scanner) {
        String[][] timetable = new String[10][6]; // 10 time slots, 5 days + time column
        
        // Initialize timetable
        for (int i = 0; i < 10; i++) {
            timetable[i][0] = String.format("%02d:00", i + 8); // 8am to 5pm
            for (int j = 1; j < 6; j++) {
                timetable[i][j] = "Free";
            }
        }
        
        while (true) {
            System.out.println("\n=== Timetable ===");
            // Print timetable
            System.out.printf("%-8s %-10s %-10s %-10s %-10s %-10s%n", 
                "Time", "Mon", "Tue", "Wed", "Thu", "Fri");
            for (String[] row : timetable) {
                System.out.printf("%-8s %-10s %-10s %-10s %-10s %-10s%n", 
                    row[0], row[1], row[2], row[3], row[4], row[5]);
            }
            
            System.out.println("\nOptions:");
            System.out.println("1. Edit entry");
            System.out.println("2. Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            if (choice == 1) {
                System.out.print("Enter row number (1-10): ");
                int row = scanner.nextInt() - 1;
                System.out.print("Enter day (1=Mon, 2=Tue, etc.): ");
                int day = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                if (row >= 0 && row < 10 && day >= 1 && day <= 5) {
                    System.out.print("Enter new value: ");
                    timetable[row][day] = scanner.nextLine();
                } else {
                    System.out.println("Invalid input");
                }
            } else if (choice == 2) {
                break;
            }
        }
    }
}
