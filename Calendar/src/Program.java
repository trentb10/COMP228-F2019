/*
    Calendar Java Application

    by Trent B Minia tm-
    190928

    The following sources were consulted to help complete this assignment:

    https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
    https://www.geeksforgeeks.org/tomohiko-sakamotos-algorithm-finding-day-week/
    https://introcs.cs.princeton.edu/java/21function/Calendar.java.html
    
*/

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Boolean createCalendar = true;

        while (createCalendar) {
            Scanner input = new Scanner(System.in);

            int monthSelection = 0;
            int yearSelection = 0;

            // Input month
            while (true) {
                System.out.print("Enter month (1 - 12): ");
                monthSelection = input.nextInt();

                // Validate month
                if (monthSelection >= 1 && monthSelection <= 12) {
                    break;
                } else {
                    System.out.println("ERROR: Invalid month. Month must be between 1 to 12 inclusive.");
                }
            }

            // Input check for year
            while (true) {
                System.out.print("Enter year: ");
                yearSelection = input.nextInt();

                // Validate year
                if (yearSelection >= 1) {
                    break;
                } else {
                    System.out.println("ERROR: Invalid year. Year must be greater than 1.");
                }
            }

            System.out.println();

            displayCalendar(monthSelection, yearSelection);

            // Input check for creation repeat
            while (true) {
                System.out.print("\nCreate another calendar? 1 - Yes, 0 - No: ");
                int createAgain = input.nextInt();

                if (createAgain == 0) {
                    createCalendar = false;
                    break;
                } else if (createAgain == 1) {
                    System.out.println();
                    break;
                } else {
                    System.out.println("ERROR: Invalid input. Please try again");
                }
            }
        }
    }

    // Calendar Object

    public static void displayCalendar(int monthSelection, int yearSelection) {
        displayTitle(monthSelection, yearSelection);
        displayBody(monthSelection, yearSelection);
    }

    // Calendar Body

    public static void displayTitle(int month, int year) {
        String[] monthTitle = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };

        System.out.println(monthTitle[month - 1] + " " + year);
    }

    public static void displayBody(int month, int year) {
        // Create Body
        // -----------

        // Determine leap year
        Boolean isLeapYear = isLeapYear(year);

        // Leap year determines number of February days
        int februaryDays = 28;
        if (isLeapYear) {
            februaryDays = 29;
        }

        // Initialize days
        int numberOfDays[] = {
                31, februaryDays, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        // Determine number of days in a given month
        int days = numberOfDays[month - 1];

        // Determine start day
        int startDay = getStartDay(month, year);

        // For Troubleshooting Purposes Only
        
        /*
        System.out.println("Leap Year?");
        if (isLeapYear) { System.out.println("Yes"); } else { System.out.println("No"); }

        System.out.println("Month: " + month);
        System.out.println("Number of Days: " + days);
        System.out.println("Year: " + year);
        System.out.println("Start Day: " + startDay);
        */

        // Display Body
        // ------------

        // Day Header
        System.out.println(" S  M  T  W  T  F  S");

        // Days
        // Pre-start (no numbers)
        for (int i = 0; i < startDay; i++) { System.out.print("   "); }

        // Start
        for (int i = 1; i <= days; i++) {
            // Format string to have leading 0
            System.out.printf("%02d ", i);

            // If iteration reaches Saturday, start a new line
            if ((i + startDay) % 7 == 0) { System.out.println(); }
        }

        System.out.println();
    }

    public static Boolean isLeapYear (int year) {
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static int getStartDay (int month, int year) {
        // Formula by Tomohiko Sakamoto

        int t[] = {
                0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4
        };

        if (month < 3) {
            year -= 1;
        }

        int startDay = (year + year / 4 - year / 100 + year / 400 + t[month - 1] + 1) % 7;

        return startDay;
    }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */