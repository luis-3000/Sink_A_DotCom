import java.io.*;

public class GameHelper {

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

		return inputLine;
	}
}
/* SAMPLE RUN

Joses-MacBook-Pro:SinkADotComGame joseluiscastillo$ javac Game.java
Joses-MacBook-Pro:SinkADotComGame joseluiscastillo$ java Game

Enter a number:  1
miss
Enter a number:  2
hit
Enter a number:  3
hit
Enter a number:  4
kill
You took 4 guesses.

*/