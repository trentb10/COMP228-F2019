/*
    Employee

    by Trent B Minia tm-
    191005

*/

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Boolean create = true;

        while (create) {
            // Instantiate scanner objects
            Scanner input = new Scanner(System.in);

            // User input
            System.out.print("First Name: ");
            String firstName = input.nextLine();


            System.out.print("Last Name: ");
            String lastName = input.nextLine();

            Date birthDate = new Date();

            while (true) { // Date validation
                System.out.print("Birth Date (yyyy/mm/dd): ");
                try {
                    birthDate = inputDate(input);

                    // Validation passed
                    System.out.println(birthDate);
                    break;
                } catch (Exception e) {
                    displayErrorMessage();
                    // Re-do input prompt
                }
            }

            System.out.print("Address: ");
            String address = input.nextLine();

            System.out.print("Position: ");
            String position = input.nextLine();

            Date hireDate = new Date();

            while (true) { // Date validation
                System.out.print("Date Hired (yyyy/mm/dd): ");
                try {
                    hireDate = inputDate(input);

                    // Validation passed
                    System.out.println(hireDate);
                    break;
                } catch (Exception e) {
                    displayErrorMessage();
                    // Re-do input prompt
                }
            }

            double annualSalary = inputSalary(input);


            // Display employee information
            System.out.println("\nAll input successful. Displaying employee information...\n");
            Employee employee = new Employee(firstName, lastName, birthDate, address, position, hireDate, annualSalary);
            System.out.println(employee);
            System.out.println();

            // Ask to go again
            System.out.print("Enter another employee?    1 - Yes    0 - No: ");

            while (true) {
                String createAgain = input.nextLine();

                if (createAgain.equals("1")) {
                    break;
                } else if (createAgain.equals("0")) {
                    create = false;
                    break;
                } else {
                    System.out.print("ERROR: Invalid input. Please try again: ");
                }
            }

            System.out.println();
        }
    }

    // Methods for handling date input
    // -------------------------------

    public static Date inputDate(Scanner input) {
        String dateHired = input.nextLine();
        // Convert to date
        Date date = convertToDate(dateHired);

        return date;
    }

    public static Date convertToDate(String dateString) {
        String[] dateArray = dateString.split("/", 3);

        // Parse String to int
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        Date date = new Date(year, month, day);

        return date;
    }

    // Method for salary input
    // ----------------------

    public static Double inputSalary(Scanner input) {
        double salary = 0.00;

        while (true) { // Salary validation
            System.out.print("Annual Salary: $");
            String strSalary = input.nextLine();

            try {
                // Attempt to parse to double
                salary = Double.parseDouble(strSalary);
                break; // Validation passed
            } catch (Exception e) {
                displayErrorMessage();
                // Go back to annual salary prompt
            }
        }

        return salary;
    }

    // Error Message
    // -------------

    public static void displayErrorMessage() {
        System.out.println("ERROR: Invalid input. Please try again.");
    }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */