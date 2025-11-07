import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main
{
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		char repetition;

		ArrayList<String> possibleAnswer = new ArrayList<>();
		String filePath = "Hangman words.txt";
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while((line = reader.readLine()) != null) {
				possibleAnswer.add(line.trim());
			}
		} catch(FileNotFoundException e) {
			System.out.println("[ERROR] File not found!");
		} catch(IOException e) {
			System.out.println("[ERROR] Something went wrong!");
		}

		do {
			ArrayList<Character> character = new ArrayList<>();
			ArrayList<Character> guessedCharacter = new ArrayList<>();
			int wrongAnswer = 0;
			repetition = ' ';
			String correctWord = possibleAnswer.get(random.nextInt(possibleAnswer.size()));

			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Welcome to Hangman game");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
			for(int i = 0; i < correctWord.length(); i++) {
				character.add('_');
			}

			while(wrongAnswer < 6) {
				getHangman(wrongAnswer);

				System.out.print("Word: ");
				for(char underscore : character) {
					System.out.print(underscore + " ");
				}
				System.out.println();

				System.out.print("Enter a letter: ");
				char guess = scanner.next().toUpperCase().charAt(0);
				guessedCharacter.add(guess);

				if (correctWord.indexOf(guess) >= 0) {
					if (character.contains(guess)) {
						System.out.println("-You already guessed it right!-");
					} else {
						System.out.println("[Correct guess!]\n");
						for (int i = 0; i < correctWord.length(); i++) {
							if(correctWord.charAt(i) == guess) {
								character.set(i, guess);
							}
						}
					}

				} else {
					System.out.println("[Wrong guess!]\n");
					wrongAnswer++;
				}


				if (!character.contains('_')) {
					System.out.println("The word is...");
					for(char underscore : character) {
						System.out.print(underscore + " ");
					}
					System.out.println("\n[Congratulations] You won!");
					break;
				}

				if (wrongAnswer >= 6) {
					getHangman(wrongAnswer);
					System.out.println("Game Over!");
					System.out.println("The correct answer is: " + correctWord);
				}
			}

			while(repetition != 'N' && repetition != 'Y') {
				try {
					System.out.print("\nDo you want to continue [y/n]: ");
					repetition = scanner.next().toUpperCase().charAt(0);

					if (repetition != 'N' && repetition != 'Y') {
						throw new InputMismatchException();
					}
					if (repetition == 'Y') {
						System.out.println();
					}
				} catch (InputMismatchException e) {
					System.out.println("[ERROR] Invalid input!");
					scanner.nextLine();
				}
			}
		} while(repetition != 'N');
		System.out.println("Thank you for playing!");
	}

	static void getHangman(int wrongAnswer) {
		switch (wrongAnswer) {
		case 0:
			System.out.println("___");
			System.out.println("|");
			System.out.println("|");
			break;
		case 1:
			System.out.println("___o");
			System.out.println("|");
			System.out.println("|");
			break;
		case 2:
			System.out.println("___o");
			System.out.println("| /");
			System.out.println("|");
			break;
		case 3:
			System.out.println("___o");
			System.out.println("| /|");
			System.out.println("|");
			break;
		case 4:
			System.out.println("___o");
			System.out.println("| /|\\");
			System.out.println("|");
			break;
		case 5:
			System.out.println("___o");
			System.out.println("| /|\\");
			System.out.println("| /");
			break;
		case 6:
			System.out.println("___o");
			System.out.println("| /|\\");
			System.out.println("| /\\");
			break;
		}
	}
}
