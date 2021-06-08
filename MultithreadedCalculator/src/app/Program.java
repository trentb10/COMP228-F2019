package app;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws Exception {
        // Program Running Loop
        Boolean program = true;
        
        // Instantiate scanner object
        Scanner intInput = new Scanner(System.in);
        
        System.out.println("Multithreaded Calculator");
        System.out.println("------------------------");

        while (program == true) {
            System.out.print("\nEnter first integer: ");
            int firstInteger = intInput.nextInt();
            System.out.print("Enter second integer: ");
            int secondInteger = intInput.nextInt();

            System.out.println();

            // Instantiate Calculate objects
            Calculate c1 = new Calculate("add", firstInteger, secondInteger);
            Calculate c2 = new Calculate("subt", firstInteger, secondInteger);
            Calculate c3 = new Calculate("mulx", firstInteger, secondInteger);
            Calculate c4 = new Calculate("div", firstInteger, secondInteger);

            // Start Calculate objects
            c1.start();
            c2.start();
            c3.start();
            c4.start();

            Thread.sleep(1000); // (sleep for a second to ensure all calculations perform first before prompting for
                                // re-entry)

            // Go Again Prompt
            while (true) {
                System.out.print("\nGo again? 1 - Yes, 0 - No: ");
                int goAgain = intInput.nextInt();

                if (goAgain == 1) {
                    break;
                } else if (goAgain == 0) {
                    program = false;
                    intInput.close();
                    break;
                } else {
                    System.out.println("ERROR: Invalid input. Please try again.");
                }
            }
        }
    }
}