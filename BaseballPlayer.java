import java.util.Scanner;

public class BaseballPlayer {
	
	//Initialize variables
	//keep track of the players name
	private String name;
	//keep track of the totalAtBats specifically for the batting average (DOES NOT include balls)
	private float totalAtBatsBattingAverage;
	//keep track of total at bats (DOES include balls)
	private float totalAtBatsOnBase;
	//stores the actual on-base percent after it is calculated
	private float onBasePercent;
	//stores the total number of base hits (used in calculating the on-base percent)
	private float onBaseHits;
	//stores the batting average after it is calculated
	private float battingAverage;
	//stores the number of hits (used in calculating the batting average)
	private float numberOfHits;
	
	//Constructor (Creates a baseball player and initializes the players statistics) REQUIRES A NAME TO CREATE THE PLAYER
		public BaseballPlayer(String name) {
			this.name = name;
			totalAtBatsOnBase = 0;
			totalAtBatsBattingAverage = 0;
			onBasePercent = 0;
			battingAverage = 0;
	}
	
	//gets and returns the name of the baseball player
		public String getName() {
			return name;
		}
	
	//gets and returns the total on-base at bats
		public float getAtBatsOnBase() {
			return totalAtBatsOnBase;
		}
	//gets and returns the total batting average at bats
		public float getAtBatsBattingAverage() {
			return totalAtBatsBattingAverage;
		}
		
		
		/*
		 * I created both a print and a get method so depending on what the programmer wants to do with the data they can pick which method they want to use.
		 * Calculates Base percent by taking the number of base hits and dividing it by the total OnBase hits. It returns this value for the user to use.
		 */
		
		public float getOnBasePercent() {
			onBasePercent = onBaseHits / totalAtBatsOnBase;
			return onBasePercent;
		}
		
		//Calculates and prints the OnBase percent by taking the number of base hits and dividing it by the total OnBase hits. This value is printed to the console.
		public void printOnBasePercent() {
			onBasePercent = onBaseHits / totalAtBatsOnBase;
			System.out.println(name + "'s OB% would be " + onBaseHits + " for " + totalAtBatsOnBase + " or " + onBasePercent);
		}
		
		//Calculates BattingAverage by taking the number of total hits and dividing it by the total BattingAverage hits. It returns this value for the user to use.
		public float getBattingAverage() {
			battingAverage = numberOfHits / totalAtBatsBattingAverage;
			return battingAverage;
		}
		
		//Calculates and prints the Batting Average by taking the number of total hits and dividing it by the total Batting Average hits. This value is printed to the console.
		public void printBattingAverage() {
			battingAverage = numberOfHits / totalAtBatsBattingAverage;
			System.out.println(name + "'s batting average would be " + numberOfHits + " for " + totalAtBatsBattingAverage + " or " + battingAverage);
		}
		
		/*
		 * These are here if someone needed to set a players statistics (If perhaps they already had statistics they wanted to enter or for general testing :P)
		 * These could probably be private or even commented out and left in just in case someone wanted to do this, but I figured I'd leave them in just in case.
		 */
		
		public void setOnBaseHits(float onBaseHits) {
			this.onBaseHits = onBaseHits;
		}
		
		public void setNumberOfHits(float numberOfHits) {
			this.numberOfHits = numberOfHits;
		}
		
		public void setTotalOnBaseHits(float totalAtBatsOnBase) {
			this.totalAtBatsOnBase= totalAtBatsOnBase;
		}
		
		public void setTotalBattingAverageHits(float totalAtBatsBattingAverage) {
			this.totalAtBatsBattingAverage = totalAtBatsBattingAverage;
		}
		
		
		//this is void for now, it will return what the result of the at bat is
		/*
		 * Once we learn exceptions I would throw an exception if the String entered didnt match one of the pre-listed ones.
		 * Also, the comments were used to find the problem in my code (why it wasnt giving me the expected numbers and I found that I needed different variables for
		 * calculating total at bats for batting average and for the on base percent
		 * 
		 * When writing this, I thought of it in terms of receiving input (sort of like a text based baseball game?), so my method for writing the program has that in mind. I could pretty 
		 * easily convert this into an interface where a user inputs one of the four outcomes of an at bat and then receives feedback accordingly.
		 */
		
		public void atBat(String battingResult) {
			
		//checks if the player earned a hit by checking if the string entered equals "hit"
			if(battingResult.equals("hit")) {
				System.out.println(name + " comes to bat and gets a hit.");
		//increments the number of hits, base hits and total at bats for both on base and batting average calculations.		
				numberOfHits++;
				totalAtBatsOnBase++;
				totalAtBatsBattingAverage++;
				onBaseHits++;
				
				//For Debugging
				//System.out.println("number of hits" + numberOfHits + " number of at bats " + totalAtBats + " on base hits " + onBaseHits);

		//checks if the player was struck out by checking if the string entered equals "out"
			} else if(battingResult.equals("out")) {
				System.out.println(name + " comes to bat and gets out.");
		//increments the number of at bats for both on base and batting average calculations.
				totalAtBatsOnBase++;
				totalAtBatsBattingAverage++;
				
				//For Debugging
				//System.out.println("number of hits" + numberOfHits + " number of at bats " + totalAtBats + " on base hits " + onBaseHits);
		
		//checks if the player received 4 balls and walked on to base by checking if the string entered equals "ball"
			} else if(battingResult.equals("ball")) {
				System.out.println(name + " comes to bat and gets on four balls for a free pass.");
		//increments number of on base hits and the total at bats on base, but not total at bats for the batting average as this doesn't affect the batting average.
				onBaseHits++;
				totalAtBatsOnBase++;
				
				//For Debugging
				//System.out.println("number of hits" + numberOfHits + " number of at bats " + totalAtBats + " on base hits " + onBaseHits);
				
		//checks if the player got on base through defensive error. Does this by checking if the string equals "error"
			} else if(battingResult.equals("error")) {
				System.out.println(name + " comes to bat and gets on base by defensive error.");
		//increments the number of base hits, total at bats for both on base and batting average.
				onBaseHits++;
				totalAtBatsOnBase++;
				totalAtBatsBattingAverage++;
				
				//For Debugging
				//System.out.println("number of hits" + numberOfHits + " number of at bats " + totalAtBats + " on base hits " + onBaseHits);

		//this keeps the programmer from crashing the program with a little built in bug fixing. It will provide the programmer an error message if they enter an incorrect string into the atBat method.
		
			} else {
				System.out.println("That is an invalid input");
				
				//For Debugging (though it wasn't necessary :P)
				//System.out.println("number of hits" + numberOfHits + " number of at bats " + totalAtBats + " on base hits " + onBaseHits);
			}
		}
		
		//Here's just for fun, idk where to put this as its not particularly useful in the Baseball player class (as you would have to create a Baseball Player for your team. Maybe you could enter the team name as the variable name?
		public BaseballPlayer[] createTeam() {
			Scanner scanner = new Scanner(System.in);
			System.out.println("How many Baseball players do you have?");
			int numPlayers = scanner.nextInt();
			BaseballPlayer player[] = new BaseballPlayer[numPlayers];
			for(int i = 0; i < numPlayers; i++) {
				System.out.println("Please enter your player name");
				String playerName = scanner.nextLine(); 
				player[i] = new BaseballPlayer(playerName);
			}
			
			return player;
		}
}
