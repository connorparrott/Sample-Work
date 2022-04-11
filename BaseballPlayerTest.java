import java.util.Scanner;

public class BaseballPlayerTest {
	public static void main(String args[]) {
		BaseballPlayer George = new BaseballPlayer("George");
		BaseballPlayer Henry = new BaseballPlayer("Henry");
		BaseballPlayer Veronica = new BaseballPlayer("Veronica");
		Scanner scanner = new Scanner(System.in);
		
		
		/*
		 * This system creates a player using user input and asks them to enter how large their data set is. 
		 * It then provides them an opportunity to type the result of the at bat which will then be used to calculate their player's batting average and on base percent.
		 *Unfortunately  at this time, the loop wont catch misinputs and allow the user to retry entering, instead it moves to the next input. So each misinput would decrease the number of inputs by one. This could be fixed later on by catching an exception I think 
		 *or perhaps something more elegant could be used to keep the invalid input from using up one of the user's tries.
		 */
		System.out.println("What is your player's name?");
		String userInput = scanner.nextLine();
		BaseballPlayer inputPlayer = new BaseballPlayer(userInput);
		System.out.println("How many times has your Baseball player been up to bat?");
		int numAtBats = scanner.nextInt();
		for(int i = 0; i < numAtBats; i++) {
			System.out.println("What was the result of your player's at bat? ('out', 'hit', 'error' or ball)");
			String result = scanner.nextLine();
			inputPlayer.atBat(result);
		}
		//I decided to make these separate methods to make it more versatile, but the print method could just be put into the get method if it is deemed unnecessary to have both.
		inputPlayer.getBattingAverage();
		inputPlayer.getOnBasePercent();
		inputPlayer.printBattingAverage();
		inputPlayer.printOnBasePercent();
		
		
		
	/*
	 * the next three players are static with standard inputs to make things easy, particularly for testing.
	 * Veronica is one of each
	 */
		Veronica.atBat("hit");
		Veronica.atBat("ball");
		Veronica.atBat("error");
		Veronica.atBat("out");
		Veronica.getBattingAverage();
		Veronica.getOnBasePercent();
		Veronica.printBattingAverage();
		Veronica.printOnBasePercent();
		
	//Henry is an exact replica of the example code
		Henry.atBat("hit");
		Henry.atBat("out");
		Henry.atBat("out");
		Henry.atBat("ball");
		Henry.atBat("error");
		Henry.getBattingAverage();
		Henry.getOnBasePercent();
		Henry.printBattingAverage();
		Henry.printOnBasePercent();
		
		
	//George is used to test what happens if nothing is entered. It does give NaN for the percentages as it is dividing by zero. I could put a case to catch this using if statements if its super important.
		George.getBattingAverage();
		George.getOnBasePercent();
		George.printBattingAverage();
		George.printOnBasePercent();
		
	}
}
