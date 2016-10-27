import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameManager {
	private static Board myBoard;
	private static Scanner input = new Scanner(System.in);
	private static char turn = ' ';
	private static boolean gameInProgress = false;
	
	public static void play(){
		gameInProgress = true;
		myBoard = new Board(); //set up the board from scratch
		turn = getFirstPlayer(); //find who goes first
		
		while(gameInProgress){
			move();
			gameInProgress = !myBoard.testWin(turn); //game is in progress if nobody has won yet
			if(gameInProgress){
				turn = turn == 'X' ? 'O' : 'X'; //makes use of a simple extended if statement to flip the current player
			}
		}
		helper.clear();
		myBoard.printBoard();
		System.out.println("\n\n" + turn + " has won!");
		System.out.print("(Enter to continue)");
		
		try { //used to wait for user to press enter
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace(); //I need to do this apparently
		}
		System.out.println("Would you like to play again?");
		System.out.print("Y to play again, anything else to quit: ");
		clearBuffer();
		if(input.next().toUpperCase().charAt(0) == 'Y'){
			clearBuffer();
			play();
		}
	}
	
	private static char getFirstPlayer(){
		System.out.println("Who goes first?");
		char turn; //temporary variable for finding the person who goes first
		do{
			System.out.print("Enter X/O: ");
			turn = input.nextLine().toUpperCase().charAt(0); //get the first character from the input
			if(!(turn == 'X' || turn == 'O')){ //if person hasn't input either X or O
				turn = ' '; 
				System.out.println("Bad input! Please try again!"); //alert the player that they entered bad input
			}
		} while(turn == ' ');
		System.out.println(turn + " will go first!");//make sure that the player knows this
		return turn;
	}
	
	private static void move(){
		int positionMove;
		boolean validMoveEntered = false;
		do{
			myBoard.printBoard();
			System.out.println("\n\nWhere would " + turn + " like to move? Enter 1-9:");
			try{
				positionMove = input.nextInt();
			} catch(InputMismatchException e){
				input.nextLine(); //clear the input buffer
				positionMove = -1; //set it to an obviously invalid move so user must try again
			}
			validMoveEntered = myBoard.isValidMove((positionMove - 1) % 3, 2 - ((positionMove - 1) / 3)); //see if the entered number is a valid move
			helper.clear();
			if(!validMoveEntered){
				System.out.println("Invalid Move! Please try again.\n"); //inform the player that they made a bad move
			}
		} while(!validMoveEntered);
		myBoard.updateBoard((positionMove - 1) % 3, 2 - ((positionMove - 1) / 3), turn); //actually make the move
	}
	
	/**
	 * use this to clear the buffer probably
	 */
	private static void clearBuffer(){
		input.nextLine();
	}
}
