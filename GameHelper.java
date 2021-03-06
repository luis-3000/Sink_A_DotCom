import java.io.*;
import java.util.*;

public class GameHelper {

	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = 49;
	private int[] grid = new int[gridSize];
	private int dotComCount = 0;

	/* Method to get input from the user. */
	public String getUserInput(String prompt) {
		String inputLine =  null;
		System.out.print(prompt + " ");
		try {
			BufferedReader bf = new BufferedReader(
									new InputStreamReader(System.in));
			inputLine = bf.readLine();
			if (inputLine.length() == 0) {
				return null;
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine.toLowerCase();
	}

	/* Method to place a Dot Com on the grid of the game. */
	public ArrayList<String> placeDotCom(int numOfDotComs) {
		ArrayList<String> alphaCells = new ArrayList<String>(); 
		String[] alphaCoords = new String[numOfDotComs]; // Holds alpha-numeric, 'f6' type coordinates
		String temp = null; 							//temporary string for concat
		int[] coords = new int[numOfDotComs]; 			// current candidate coords
		int attempts = 0; 								// attempts counter
		boolean success = false; 						// flag = found a good location?
		int location = 0; 								// current starting location

		dotComCount++; 									// nth Dot Com to place
		int increment = 1; 								// set horizontal increment
		if ((dotComCount % 2) == 1) { 					// if odd Dot Com (place vertically)
			increment = gridLength; 					// set vertical increment
		}

		while ( !success & attempts++ < 200) { 			// main search loop
			location = (int) (Math.random() * gridSize); // get random starting point
			//System.out.print(" try " + location);
			int nthPos = 0; 							 // nth position in dotcom to place
			success = true; 							 // assume success initially
			while (success && nthPos < numOfDotComs) {  // look for adjacent unused spots
				if (grid[location] == 0) {   			// it not already used
					coords[nthPos++] = location; 		// save location
					location += increment; 				// try 'next' adjacent
					if (location >= gridSize) { 		// out of bounds - 'bottom' of grid
						success =  false; 				// then failure
					}
					if (nthPos > 0 && (location % gridLength == 0) ) { // out of bounds -right edge of grid
						success = false;				// failure
					}
				} else {								// Found already used location
					//System.out.print(" used " + location);
					success = false;					// failure
				}
			}
		}

		// Turn location into alpha-numeric coordinates
		int x = 0;
		int row = 0;
		int column = 0;
		//System.out.println("\n");
		while (x < numOfDotComs) {
			grid[coords[x]] = 1; // Mark master gird points as 'used'
			row = (int) (coords[x] / gridLength); // get row value
			column = coords[x] % gridLength; 	// get numeric column value
			temp = String.valueOf(alphabet.charAt(column)); // convert to alpha-numeric

			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;

			// This is the statement that tells you exactly where the DotCom is located
			System.out.println(" Coordinate  " + x + " = " + alphaCells.get(x-1));
		}

		//System.out.println("\n");

		return alphaCells;
	}
}
/* SAMPLE RUN

Joses-MacBook-Pro:SinkADotComGame joseluiscastillo$ javac DotComBust.java
Joses-MacBook-Pro:SinkADotComGame joseluiscastillo$ java DotComBust
Game instructions: 
Your goal is to sink three dot coms.
facebook.com, Microsoft.com, Google.com
Try to sink them all in the fewest number of guesses.
 Coordinate  1 = c0
 Coordinate  2 = c1
 Coordinate  3 = c2
 Coordinate  1 = a6
 Coordinate  2 = b6
 Coordinate  3 = c6
 Coordinate  1 = g3
 Coordinate  2 = g4
 Coordinate  3 = g5

Enter a guess:  c0
hit

Enter a guess:  c1
hit

Enter a guess:  c2
kill

Enter a guess:  a6
hit

Enter a guess:  b6
hit

Enter a guess:  c6
kill

Enter a guess:  g3
hit

Enter a guess:  g4
hit

Enter a guess:  g5
kill
All Dot Coms are dead! your stocks for those companies is now worthless.
It only took you  9 guesses.
You got out before your options sank.

*/