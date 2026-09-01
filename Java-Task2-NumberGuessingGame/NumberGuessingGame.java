import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        // Scanner reads input typed by the user in the terminal
        Scanner scanner = new Scanner(System.in);

        // Random is used to generate the secret number each round
        Random random = new Random();

        // Keeps track of how many rounds have been played, for the summary at the end
        int roundNumber = 1;

        // "playAgain" controls whether the outer loop (the whole game) keeps running
        boolean playAgain = true;

        System.out.println("=== Welcome to the Number Guessing Game ===");
        System.out.println("I will think of a number between 1 and 100. Try to guess it!");

        // Outer loop: runs once per round (a "round" = one full guessing game)
        while (playAgain) {

            // nextInt(100) gives a number from 0 to 99, so we add 1 to shift the range to 1-100
            int secretNumber = random.nextInt(100) + 1;

            int maxAttempts = 7;   // task requirement: limit attempts
            int attemptsUsed = 0;  // counts how many guesses this round has used
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + roundNumber + " ---");

            // Inner loop: runs once per guess within a round
            // It continues as long as attempts remain AND the player hasn't guessed correctly yet
            while (attemptsUsed < maxAttempts && !guessedCorrectly) {

                System.out.print("Enter your guess (attempt " + (attemptsUsed + 1) + " of " + maxAttempts + "): ");

                // Basic input validation: make sure the user typed a whole number
                if (!scanner.hasNextInt()) {
                    System.out.println("That's not a valid number. Please try again.");
                    scanner.next(); // discard the invalid token so we don't loop forever
                    continue;       // skip the rest of this loop iteration, don't count it as an attempt
                }

                int guess = scanner.nextInt();
                attemptsUsed++; // a valid guess was made, so it counts toward the attempt limit

                if (guess < secretNumber) {
                    System.out.println("Too Low!");
                } else if (guess > secretNumber) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Correct! You guessed it in " + attemptsUsed + " attempt(s).");
                    guessedCorrectly = true; // this ends the inner loop
                }
            }

            // If the loop ended because attempts ran out (not because they guessed right)
            if (!guessedCorrectly) {
                System.out.println("You Lost! The number was: " + secretNumber);
            }

            // Round summary, as required by the task checklist
            if (guessedCorrectly) {
                System.out.println("Round " + roundNumber + " — guessed in " + attemptsUsed + " attempts");
            } else {
                System.out.println("Round " + roundNumber + " — not guessed within " + maxAttempts + " attempts");
            }

            // Ask if the player wants another round
            System.out.print("Play again? (yes/no): ");
            String response = scanner.next();

            // equalsIgnoreCase so "Yes", "YES", "yes" all work the same way
            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
            }

            roundNumber++; // increment for the next round's label, even if we're about to stop
        }

        System.out.println("\nThanks for playing! Goodbye.");
        scanner.close(); // good practice: release the input resource when done
    }
}