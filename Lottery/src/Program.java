/*
    LuckyMatch - A Lottery Game

    by Trent B Minia tm-
    190928

*/

import java.util.Scanner;

public class Program {
    // Main

    public static void main(String[] args) {
        // Instantiate objects for game
        PlayerNumber playerNumber = new PlayerNumber();
        LotteryNumber lotteryNumber = new LotteryNumber();
        Winnings winnings = new Winnings();

        // Bools for menu selection
        boolean mainMenuSelection = true;
        boolean playingGame = false;
        boolean requestReset = false;

        // Start Screen
        displayTitle();

        // Main Menu - Display & Selection
        while (mainMenuSelection) {
            // Main Menu with Selection
            displayMainMenu();
            String isPlaying = menuPlayerSelection();

            switch (isPlaying) {
                case "0":
                    System.exit(0);
                case "1":
                    System.out.println("Good luck!\n");
                    mainMenuSelection = false;
                    playingGame = true;
                    break;
                case "2":
                    displayHowToPlay();
                    break;
                default:
                    displayMenuSelectionError();
            }
        }

        // Gameplay
        while (playingGame) {
            // Generate a random lottery number
            lotteryNumber = generateLotteryNumber(lotteryNumber);

            // Prompt for player number
            playerNumber = promptForPlayerNumber(playerNumber);

            // Check Winnings
            checkWinnings(lotteryNumber, playerNumber, winnings);

            // Display Winnings
            displayWinnings(winnings);

            // Request for game restart
            requestReset = true;
            while (requestReset) {
                displayRestart();
                String playAgain = menuPlayerSelection();

                switch (playAgain) {
                    case "1":
                        System.out.println("Good luck!\n");
                        requestReset = false;
                        break;
                    case "0":
                        System.out.println("Thank you for playing!");
                        requestReset = false;
                        playingGame = false;
                        break;
                    default:
                        displayMenuSelectionError();
                }
            }
        }
    }

    // Display

    public static void displayTitle() {
        System.out.println(".##.....##..##..####..##..##.##..##........##...##..####..######..####..##..##.");
        System.out.println(".##.....##..##.##..##.##.##...####.........###.###.##..##...##...##..##.##..##.");
        System.out.println(".##.....##..##.##.....####.....##..........##.#.##.######...##...##.....######.");
        System.out.println(".##.....##..##.##..##.##.##....##..........##...##.##..##...##...##..##.##..##.");
        System.out.println(".######..####...####..##..##...##..........##...##.##..##...##....####..##..##.");
        System.out.println("...............................................................................");
        System.out.println();
        System.out.println("             A       L   O   T   T   E   R   Y       G   A   M   E             ");
        System.out.println();
    }

    public static void displayMainMenu() {
        System.out.println("               1 - Start Game      2 - How To Play      0 - Exit               ");
        System.out.println();
        System.out.println();
    }

    public static void displayHowToPlay() {
        System.out.println("The objective of Lucky Match is to pick any two-digit number and have \n" +
                "it match with the drawn lottery number.\n" +
                "The more digits you can match up with, the more you can win!\n\n" +
                "WINNINGS:\n" +
                "$1,000,000: All digits match in exact order.\n" +
                "Example: Player Number: 21     Lottery Number: 21\n\n" +
                "$500,000: ALl digits match in no particular order.\n" +
                "Example: Player Number: 96     Lottery Number: 69\n\n" +
                "$100,000: At least one digit matches.\n" +
                "Example: Player Number: 11     Lottery Number: 01\n\n" +
                "Good luck!\n");
    }

    public static void displayWinnings(Winnings winnings) {
        // Get winning status and amount won
        Boolean winner = winnings.getIsAWinner();
        int amount = winnings.getAmount();

        // Set winning message
        String message;

        if (winner == true) {
            message = "Congratulations! You won! :)";
        } else {
            message = "Sorry. You did not win. :(";
        }

        System.out.println(message);

        // Display winnings only if amount > 0
        if (amount > 0) {
            System.out.println("Your winnings: $" + amount);
        }

        System.out.println();
    }

    public static void displayRestart() {
        System.out.println("Play Again?      1 - Yes      0 - No");
    }

    // Menu Player Selection

    public static String menuPlayerSelection() {
        Scanner input = new Scanner(System.in);

        System.out.print("Make a selection: ");

        String selection = input.nextLine();
        System.out.println();

        return selection;
    }

    public static void displayMenuSelectionError() {
        System.out.println("ERROR: Invalid input. Please try again.\n");
    }

    // Gameplay

    public static LotteryNumber generateLotteryNumber(LotteryNumber lotteryNumber) {
        int firstDigit, secondDigit;

        // Generate any double digit number
        int number = (int)(Math.random() * 98 + 1);

        // Check if both numbers are 0
        if (number == 0) {
            firstDigit = 0; secondDigit = 0;
        }
        // Convert to double digits if lottery number is a single digit
        else if (number < 10) {
            firstDigit = 0;
            secondDigit = number % 10;
        }
        // If first digit is not 0, store both digits
        else {
            firstDigit = number / 10;
            secondDigit = number % 10;
        }

        lotteryNumber.setFirstDigit(firstDigit);
        lotteryNumber.setSecondDigit(secondDigit);

        return lotteryNumber;
    }

    public static PlayerNumber promptForPlayerNumber(PlayerNumber playerNumber) {
        Scanner input = new Scanner(System.in);

        int number = 0;

        while (true) {
            System.out.print("Enter a number: ");
            number = input.nextInt();

            if (number >= 0 && number <= 99) {
                break;
            } else {
                System.out.println("ERROR: Invalid input. Numbers must be between 0-99.\n" +
                        "Please try again.\n\n");
            }
        }

        // Set digits for object playerNumber
        int firstDigit, secondDigit;

        // Check if both numbers are 0
        if (number == 0) {
            firstDigit = 0; secondDigit = 0;
        }
        // Convert to double digits if lottery number is a single digit
        else if (number < 10) {
            firstDigit = 0;
            secondDigit = number % 10;
        }
        // If first digit is not 0, store both digits
        else {
            firstDigit = number / 10;
            secondDigit = number % 10;
        }

        playerNumber.setFirstDigit(firstDigit);
        playerNumber.setSecondDigit(secondDigit);

        return playerNumber;
    }

    public static void checkWinnings(LotteryNumber lotteryNumber, PlayerNumber playerNumber, Winnings winnings) {
        int lotteryFirstDigit = lotteryNumber.getFirstDigit();
        int lotterySecondDigit = lotteryNumber.getSecondDigit();

        int playerFirstDigit = playerNumber.getFirstDigit();
        int playerSecondDigit = playerNumber.getSecondDigit();

        // Display lottery and player numbers - For troubleshooting purposes only
        //System.out.println("The player  number is " + playerFirstDigit + playerSecondDigit);
        //System.out.println("The lottery number is " + lotteryFirstDigit + lotterySecondDigit);


        if ((playerFirstDigit == lotteryFirstDigit) && (playerSecondDigit == lotterySecondDigit)) {
            winnings.setIsAWinner(true);
            winnings.setAmount(1000000);
        }

        else if ((playerFirstDigit == lotterySecondDigit) && (playerSecondDigit == lotteryFirstDigit)) {
            winnings.setIsAWinner(true);
            winnings.setAmount(500000);
        }

        else if ((playerFirstDigit == lotteryFirstDigit || playerFirstDigit == lotterySecondDigit) ||
                (playerSecondDigit == lotteryFirstDigit || playerSecondDigit == lotterySecondDigit)) {
            winnings.setIsAWinner(true);
            winnings.setAmount(100000);
        }

        else {
            winnings.setIsAWinner(false);
            winnings.setAmount(0);
        }
    }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */