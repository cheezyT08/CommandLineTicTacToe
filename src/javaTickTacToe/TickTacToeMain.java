package javaTickTacToe;

//@author Torin

import java.util.*;
public class TickTacToeMain {
	private static char d = '#';
	
	public static void main(String[] args) throws InterruptedException {
		//Initializing Scanner
		Scanner scan = new Scanner(System.in);
		
		//Change This To Your Delimiter
		
		//Initializing x and y Coordinate Vars
		int tx, ty;
		
		//Initializing team Var
		char team = 'x';
		
		//Initializing 2d Char Array As Board
		char[][] brdArr = {
						 {d, d, d}, 
						 {d, d, d}, 
						 {d, d, d}
						};
		//Running Startup Actions That Don't Loop
		//Printing Rules And Information
		System.out.print("Enter \"Yes\" To See Rules And Important Instructions: ");
		char rIn = scan.nextLine().charAt(0);
		if(rIn == 'y' || rIn == 'Y') {
			System.out.println("\n/////RULES_AND_INSTRUCTIONS\\\\\\\\\\\nFirst to three in a row wins, "
					+"blank spots will be represented with: \'"+d+"\'");
			Thread.sleep(4000);
			
			System.out.println("\nHere is how the positioning works:\n"
					+ "Each position in the board is represented by\n"
					+ "an x(left to right) coordinate, 1-3,\nand a "
					+ "y(top to bottem coordinate), also 1-3\n");
			Thread.sleep(8000);
			System.out.printf("Here Is An Example:\n"
					+"==================================================="
					+"\n|                                                 |"
					+"\n|   %3s%3s%3s    |"
					+"\n|   %3s%3s%3s    |"
					+"\n|   %3s%3s%3s    |"
					+"\n|                                                 |"
					+"\n==================================================="
					+"\nSo x=2, y=2 would be the middle and x=1, y=3 would be the bottom left corner",
					"{x = 1, y = 1}", "{x = 2, y = 1}", "{x = 3, y = 1}", 
					"{x = 1, y = 2}", "{x = 2, y = 2}", "{x = 3, y = 2}", 
					"{x = 1, y = 3}", "{x = 2, y = 3}", "{x = 3, y = 3}"
					);
			Thread.sleep(9000);
		}
		
		//Making Repeat Array And Looping Through For Duration Of Game
		boolean rep = true;
		while(rep) {
			//Printing Which Teams Turn It Is And Switching Team Var
			System.out.println("\n\n-------------------");
			System.out.printf("It Is Team %C's Turn\n", team);
			
			//Printing Board
			System.out.printf("\nHere Is The Board:\n"
					+"=================="
					+"\n|                |"
					+"\n|   %3C%3C%3C    |"
					+"\n|   %3C%3C%3C    |"
					+"\n|   %3C%3C%3C    |"
					+"\n|                |"
					+"\n==================\n",
					brdArr[0][0], brdArr[0][1], brdArr[0][2], 
					brdArr[1][0], brdArr[1][1], brdArr[1][2], 
					brdArr[2][0], brdArr[2][1], brdArr[2][2]);
			
			//Getting Input For Turn And Limiting Numbers
			tx = 0;
			ty = 0;
			boolean tRep = true;
			while(tRep) {
				System.out.print("Please Enter An X Coordinate: ");
				tx = (scan.nextInt() - 1);
				if(tx < 0) {
					tx = 0;
				}else if(tx > 2) {
					tx = 2;
				}
				System.out.print("Please Enter A Y Coordinate: ");
				ty = (scan.nextInt() - 1);
				if(ty < 0) {
					ty = 0;
				}else if(ty > 2) {
					ty = 2;
				}
				
				//Testing If Slot Is Used
				if(brdArr[ty][tx] == d) {
					tRep = false;
				} else {
					System.out.println("That Spot Has Already Been Used, Please Try Again");
					Thread.sleep(1200);
					tRep = true;
				}
			}
			
			//Setting Board And Showing Players Move
			if(team == 'x') {
				brdArr[ty][tx] = 'x';
				System.out.printf("\nYou Placed An X At X: %d, and Y: %d.", tx+1, ty+1);
				Thread.sleep(1500);
			} else if(team == 'o') {
				brdArr[ty][tx] = 'o';
				System.out.printf("\nYou Placed An O At X: %d, and Y: %d.", tx+1, ty+1);
				Thread.sleep(1000);
			} else {
				System.out.println("There Was An Error, Terminating Program...");
				System.exit(-1);
			}
				
			//Testing If Tied Ore If One Team Won
			char checker = check2d3x3CharArr(brdArr);
			
			if(testIfTied(brdArr)) {
				System.out.println("\nThe Board If Full, It Was A Tie!");
				rep = false;
			}else if(checker != d) {
				System.out.printf("\n\n-------------------"
								 +"\nTeam %C Won!", checker);
				rep = false;
				System.out.printf("\n"
						+"=================="
						+"\n|                |"
						+"\n|   %3C%3C%3C    |"
						+"\n|   %3C%3C%3C    |"
						+"\n|   %3C%3C%3C    |"
						+"\n|                |"
						+"\n==================\n",
						brdArr[0][0], brdArr[0][1], brdArr[0][2], 
						brdArr[1][0], brdArr[1][1], brdArr[1][2], 
						brdArr[2][0], brdArr[2][1], brdArr[2][2]);
			}
			
			//Switching Teams
			if(team == 'x') {
				team = 'o';
			} else {
				team = 'x';
			}
		}
		
		//PUT THIS LAST!!!
		scan.close();
	}
	
	//Method For Checking A 2d 3 by 3 Char Array For 3 In A Row
	public static char check2d3x3CharArr(char[][] cArr) {
		for(int p = 0; p < 3; p++) {
			//Testing If Equal From Top To Bottom Once Per Loop
			if((cArr[p][0]==cArr[p][1]&&cArr[p][1]==cArr[p][2])&&(cArr[p][0]!=d)) {
				return cArr[p][0];
			}
			//Testing If Equal From Left To Right Once Per Loop
			if((cArr[0][p]==cArr[1][p]&&cArr[1][p]==cArr[2][p])&&(cArr[0][p]!=d)) {
				return cArr[0][p];
			}
		}
		if((cArr[0][0]==cArr[1][1]&&cArr[1][1]==cArr[2][2])&&(cArr[1][1]!=d)) {
			return cArr[1][1];
		}
		if((cArr[0][2]==cArr[1][1]&&cArr[1][1]==cArr[2][0])&&(cArr[1][1]!=d)) {
			return cArr[1][1];
		}
		return d;
	}
	
	//To Test That None Of The Slots In Board Match Delimiter
	public static boolean testIfTied(char[][] cArr) {
		for(int x = 0; x < cArr.length; x++) {
			for(int y = 0; y < cArr[x].length; y++) {
				if(cArr[x][y]==d) {
					return false;
				}
			}
		}
		return true;
	}
}
