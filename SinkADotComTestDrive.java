
public class SinkADotComTestDrive {
	public static void main (String[] args) {
		
		DotCom dot = new DotCom();

		int[] locations = {2, 3, 4};

		dot.setLocationCells(locations);

		String userGuess = "2"; // make a fake user guess

		String result = dot.checkYourself(userGuess);

		String testResult = "failed";

		if (result.equals("hit")) {
			testResult = "passed"; //if the fake guess(2) gives bakc a 'hit', it's working
		}

		System.out.println(testResult); // passed or failed
	}	

}