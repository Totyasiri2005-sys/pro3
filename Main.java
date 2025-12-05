package project3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RegistrationSystem rs = new RegistrationSystem();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Enter Marks");
            System.out.println("3. Display Marks Info");
            System.out.println("4. Display Student Details");
            System.out.println("5. Modify Marks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1: rs.addStudent(input); break;
                case 2: rs.enterMarks(input); break;
                case 3: rs.displayMarksInfo(input); break;
                case 4: rs.displayAllStudents(); break;
                case 5: rs.modifyMarks(input); break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}