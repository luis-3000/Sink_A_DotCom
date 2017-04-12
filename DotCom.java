import java.util.ArrayList;

public class DotCom {
	
	//int[] locationCells;
	private ArrayList<String> locationCells; // Improved version with ArrayList<String>

	int numOfHits = 0;

	String name = null;

	/* A setter method that updates the a DotCom's location.
	 * (Random location provided by the GameHelper placeDotCom() method. */
	public void setLocationCells(ArrayList<String> locs) {
		locationCells = locs;
	}

	/* Setter method for each Dot Com's name. */
	public void setName(String newName) {
		name = newName;
	}

	public String checkYourself(String userInput) {
		
		String result = "miss";

		// Find out if the uesr guess is in the ArrayList, by asking for its index.
		// If it's not in the list, then indexOf() returns -1
		int index = locationCells.indexOf(userInput);

		if (index >= 0) { // If index is greater than or equal to zero, then the 
						  // user's guess is definately in the list, 
			locationCells.remove(index); // so, remove it

			if (locationCells.isEmpty()) { // If the list is empty, this was the killing
				result = "kill";		   // this was the 'killing' blow!
			} else {
				result = "hit";
			}
		}

		return result; // return "miss" or "hit" or "kill"
	}

}