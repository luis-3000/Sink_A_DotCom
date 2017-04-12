import java.util.*;

public class DotComBust {

	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;

	private void setUpGame() {

		// First make three DotCom objects, give them names and stick them
		// in the ArrayList
		DotCom one = new DotCom();
		one.setName("facebook.com");
		DotCom two = new DotCom();
		two.setName("Microsoft");
		DotCom three = new DotCom();
		three.setName("Google.com");
		
		// Add the newly created companies to hour target list
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);

		System.out.println("Game instructions: ");
		System.out.println("Your goal is to sink three dot coms.");
		System.out.println("facebook.com, Microsoft.com, Google.com");
		System.out.println("Try to sink them all in the fewest number of guesses.");

		// Repeat with each DotCom in the list
		for (DotCom dotComToSet : dotComsList) {
			// Ask the helper for a Dotcom location (an ArrayList of Strings)
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}
	}

	private void startPlaying() {
		// As long as the DotCom list is NOT empty
		while (!dotComsList.isEmpty()) {
			String userGuess = helper.getUserInput("\nEnter a guess: ");
			checkUserGuess(userGuess);
		}

		finishGame();
	}

	private void checkUserGuess (String userGuess) {

		// The user has made at least one guess at this point
		numOfGuesses++; // increment the number of guesses the user has made

		String result = "miss"; // default value for 'result', assuming a 'miss' initially

		// Repeat for all DotComs in the list
		for (DotCom dotComToTest: dotComsList) {
			result = dotComToTest.checkYourself(userGuess); // Check the user guess, looking
			if (result.equals("hit")) {						// for a 'hit' or a 'kill'
				break; // get out of the loop early, no point in testing the others
			}

			if (result.equals("kill")) {
				dotComsList.remove(dotComToTest); // This guy's dead, so take him out of the
												  // DotComsList, and get out of the loop
				break;
			}
		}

		// Print the result for the user to see
		System.out.println(result);
	}

	private void finishGame() {

		System.out.println("All Dot Coms are dead! your stocks for those companies is now worthless.");

		// Print a message telling user how he/she did in the game
		if (numOfGuesses <= 18) {
			System.out.println("It only took you  " + numOfGuesses + " guesses.");
			System.out.println("You got out before your options sank.");
		} else {
			System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
			System.out.println("Fish are dancing with your options.");
		}	
	}

	public static void main (String[] args) {
		DotComBust game = new DotComBust(); // Create the game object
		game.setUpGame();					// Tell the game object to set up the game
		game.startPlaying(); 				// Tell gae object to start the main game play loop
	}										// (which keeps asking for the user input and checking the guesses)
 
}