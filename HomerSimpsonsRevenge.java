package recursion;

import java.io.File;
import java.util.Scanner;

public class HomerSimpsonsRevenge {
	
	private static char[][] maze;
	public HomerSimpsonsRevenge() {
	}
	/*
	
	private static char[][] maze = new char[][]{
		{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
		{'#', '.', '.', '.','#','.', '.', '.', '.', '.', '.', '#'},
		{'.', '.', '#', '.', '#', '.', '#','#','#','#','.', '#'},
		{'#', '#', '#', '.', '#', '.','.','.','.','#','.','#'},
		{'#', '.','.','.','.','#','#','#','.','#','.','#'},
		{'#','#','#','#','.','#','F','#','.','#','.','#',},
		{'#','.','.','#','.','#','.','#','.','#','.','#',},
		{'#','#','.','#','.','#','.','#','.','#','.','#'},
		{'#','.','.','.','.','.','.','.','.','#','.','#',},
		{'#','#','#','#','#','#','.','#','#','#','.','#',},
		{'#','.','.','.','.','.','.','#','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#'}
	};
	*/
	
	private static int startXPosition = 0;
	private static int startYPosition = 0;
	private static int startXHand = 0;
	private static int startYHand = 0;
	
	public static void main(String[] args) {
		//Read file and create maze.
		try {
			int numLines = 0;
			int numElements = 0;
			File file = new File("maze7.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				//System.out.println(line); Here for debugging to ensure the file is being read.
				numElements = line.length()/2;
				numLines++;
			}
			scan.close();
			scan = new Scanner(file);
			maze = new char[numLines][numElements];
			int counter = 0;
			while(scan.hasNext()) {
				String line = scan.nextLine();
					for(int j = 0; j < numElements; j++) {
						maze[counter][j] = line.charAt(1+(2*j));
					}
					counter++;
			}
			
		} catch(Exception fileNotFound) {
			System.out.println("Couldn't find your file");
		}
		
		/*Initiation of the recursive method
		Hard coded start:
		printMaze();
		if(findPath(0, 2, 0, 3)){
		System.out.println("Homer found his way out!");
		printMaze();
		} else{
		System.out.println("Couldn't win");
		printMaze();
		}
		*/
		
		printMaze();
		findStartPosition(maze[0].length, maze.length);
		if(findPath(startXPosition, startYPosition, startXHand, startYHand)){
			System.out.println("Homer found his way out!");
			printMaze();
		} else {
			System.out.println("Couldn't win");
			printMaze();
		}
		
	}
		
	
	public static boolean findPath(int xLocation, int yLocation, int xHandLocation, int yHandLocation) {
		//Logic for solving the Maze
		printMaze();
		System.out.println(xLocation);
		System.out.println(yLocation);
		if(maze[yLocation][xLocation] == 'F') {
			return true;
		}
			
		if(xLocation == xHandLocation) {
			if(yLocation < yHandLocation) {
				//EAST
				if(maze[yHandLocation][xHandLocation] == '.' || maze[yHandLocation][xHandLocation] == 'O' || maze[yHandLocation][xHandLocation] == 'F'){
					//check if hand is on dot, turn right.
					maze[yLocation][xLocation] = 'O';
					xLocation = xHandLocation;
					yLocation = yHandLocation;
					xHandLocation -=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
					
				} else if(maze[yLocation][xLocation +1] == '.' || maze[yLocation][xLocation + 1] == 'O' || maze[yLocation][xLocation + 1] == 'F') {
					//check if front is a dot, move forward.
					maze[yLocation][xLocation] = 'O';
					xLocation += 1;
					xHandLocation +=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else {
					//If both are blocked, turn left.
					maze[yLocation][xLocation] = 'O';
					xHandLocation += 1;
					yHandLocation -= 1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				}
				
			} else if(yLocation > yHandLocation) {
				//WEST
				if(maze[yHandLocation][xHandLocation] == '.' || maze[yHandLocation][xHandLocation] == 'O' || maze[yHandLocation][xHandLocation] == 'F'){
					//check if hand is on dot, turn right.
					maze[yLocation][xLocation] = 'O';
					xLocation = xHandLocation;
					yLocation = yHandLocation;
					xHandLocation +=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else if(maze[yLocation][xLocation -1] == '.' || maze[yLocation ][xLocation - 1] == 'O' || maze[yLocation][xLocation - 1] == 'F') {
					//check if front is a dot, move forward.
					maze[yLocation][xLocation] = 'O';
					xLocation -= 1;
					xHandLocation -=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else {
					//If both are blocked, turn left.
					maze[yLocation][xLocation] = 'O';
					xHandLocation -= 1;
					yHandLocation += 1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
				}
			}
			
		} else if(yLocation == yHandLocation) {
			if(xLocation > xHandLocation) {
				//SOUTH
				if(maze[yHandLocation][xHandLocation] == '.' || maze[yHandLocation][xHandLocation] == 'O' || maze[yHandLocation][xHandLocation] == 'F'){
					//check if hand is on dot, turn right.
					maze[yLocation][xLocation] = 'O';
					xLocation = xHandLocation;
					yLocation = yHandLocation;
					yHandLocation -=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else if(maze[yLocation +1][xLocation] == '.' || maze[yLocation +1][xLocation] == 'O' || maze[yLocation +1][xLocation] == 'F' ) {
					//check if front is a dot, move forward.
					maze[yLocation][xLocation] = 'O';
					yLocation += 1;
					yHandLocation +=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else {
					//If both are blocked, turn left.
					maze[yLocation][xLocation] = 'O';
					xHandLocation += 1;
					yHandLocation += 1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
				}
			} else if(xLocation < xHandLocation) {
				//NORTH
				if(maze[yHandLocation][xHandLocation] == '.' || maze[yHandLocation][xHandLocation] == 'O' || maze[yHandLocation][xHandLocation] == 'F'){
					//check if hand is on dot, turn right.
					maze[yLocation][xLocation] = 'O';
					xLocation = xHandLocation;
					yLocation = yHandLocation;
					yHandLocation +=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else if(maze[yLocation - 1][xLocation] == '.' || maze[yLocation -1][xLocation] == 'O' || maze[yLocation -1][xLocation] == 'F') {
					//check if front is a dot, move forward.
					maze[yLocation][xLocation] = 'O';
					yLocation -= 1;
					yHandLocation -=1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
					
				} else {
					//If both are blocked, turn left.
					maze[yLocation][xLocation] = 'O';
					xHandLocation -= 1;
					yHandLocation -= 1;
					return findPath(xLocation, yLocation, xHandLocation, yHandLocation);
				}
			}
		}
		System.out.println(xLocation);
		System.out.println(yLocation);
		return false;
	}
		

	public static void findStartPosition(int numElements, int numLines) {
		//Check Top and Bottom
		for(int i = 0; i < numElements; i++) {
			
			//Top
			if(maze[0][i] == '.') {
				startYPosition = 0;
				startXPosition = i;
				startXHand = startXPosition -1;
				startYHand = startYPosition;
				
				//Bottom
			} else if(maze[numLines-1][i] == '.') {
				startYPosition = maze.length-1;
				startXPosition = i;
				startXHand = startXPosition +1;
				startYHand = startYPosition;
			}
		}
		//Check Left and Right side
		for(int i = 0; i < numLines;i++) {
			
			//Left
			if(maze[i][0] == '.') {
				startYPosition = i;
				startXPosition = 0;
				startXHand = startXPosition;
				startYHand = startYPosition +1;
				
				//Right
			} else if(maze[i][numElements-1] == '.') {
				startYPosition = i;
				startXPosition = maze[i].length -1;
				startXHand = startXPosition;
				startYHand = startYPosition -1;
			}
		}
	}
	
//Print the maze nicely with spaces and a line between each one to easily see changes with each step.
	public static void printMaze() {
		for(int i = 0; i < maze.length;i++) {
			for(int j = 0; j < maze[i].length;j++) {
				System.out.print(maze[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("********************************************************");
		System.out.println();
		
	}
}


