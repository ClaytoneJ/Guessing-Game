//Clayton Johnson
//5/21/2023
//CS 141 lab 4 Guessing game
//This program is a guessing game that will show you the statistics of the games you played when you are done
import java.util.Scanner;

public class CJGuess {
   public static final int answerCap = 100;//constant that determines highest answer
   
   public static void main(String[] args) {		
      int gameCount = 0;
      int guessCount = 0;
      int guessNumLow = 9999;//assumes user will not guess more than 9999 times
      gameIntro(answerCap);
      Scanner input = new Scanner(System.in);
      boolean playAgain = true;
      //while loop that continues playing games until the user says no at the prompt
      while (playAgain) {
         int answer = (int) (Math.random() * answerCap) + 1;            
         int guessNum = 0;
         boolean correctGuess = false;
         //calls startGame method and assigns the return value to guessNum
         guessNum = startGame(answerCap, answer, guessNum, correctGuess, input);
         //assigns the guessNumLow variable the lowest guessNum assuming that the player wont do 9999 guesses
         if (guessNum < guessNumLow) {
            guessNumLow = guessNum;
         }
         //gameCount is incremented and the guessNum value is added to the guessCount every iteration 
         gameCount++;
         guessCount += guessNum;
         boolean goodInput = false;
        //while loop that loops until the user input is good(starts with y or n) 
         while (goodInput == false) {
               System.out.println("would you like to play again?(Yes/No)");
               String replay = input.next();
               //decides if input is good based on the value of the first character converted to lowercase
               char fLetter = replay.charAt(0);
               char lcfLetter = Character.toLowerCase(fLetter);         
               if (lcfLetter == 'n') {
                  System.out.println("Thank you for playing!");
                  playAgain = false;
                  goodInput = true;
               } else if (lcfLetter == 'y') {
                  playAgain = true;
                  goodInput = true;
               } else {
                  System.out.println("Please enter yes or no");
                  goodInput = false;
               }         
         }
      } 
      showStats(gameCount, guessCount, guessNumLow);
      input.close();
   }
   //method that prints out the game intro
   public static void gameIntro(int answerCap) {
      System.out.println("This program is a guessing game,");
      System.out.println("I will pick a number between 1 and " + answerCap + ",");
      System.out.println("Then you will guess which number I picked,");
      System.out.println("I will tell you if the number you guessed is higher or lower than the answer.");
      System.out.println("Good luck!");
   }
   //method that plays a singular game and returns the number of guesses
   public static int startGame(int answerCap, int answer, int guessNum, boolean correctGuess, Scanner input) {
      System.out.println("Guess my number between 1 and " + answerCap);
      int guess;
      /*while loop that prompts the user for a guess, tells them if it is higher or 
      lower than the answer, and then exits once the user guesses the answer*/ 
      while (correctGuess == false) {
         System.out.print("Guess: ");
         guess = input.nextInt();
         guessNum++;         
         if (guess < answer) {
            System.out.println("Higher");
         } else if (guess > answer) {
            System.out.println("Lower");
         } else { correctGuess = true;
            if (guessNum == 1) {
               System.out.println("You got it right in 1 guess");
            }   else { 
               System.out.println("You got it right in " + guessNum + " guesses");
            }
         }         
      }
      return guessNum;      
   }
   //Method that shows the statistics of the games played
   public static void showStats(int gameCount, int guessCount, int guessNumLow) {
      double guessesPerGame = (double) guessCount / gameCount;
      System.out.println("Total number of games: " + gameCount);
      System.out.println("Total number of guesses: " + guessCount);
      System.out.println("Lowest number of guesses: " + guessNumLow);
      System.out.printf("Guesses per game: %.2f", guessesPerGame);      
   }
}