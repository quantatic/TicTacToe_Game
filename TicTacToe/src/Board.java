
public class Board {
	private char[][] board; //ACCESS IN THE FORMAT [Y][X]
	
	public Board(){
		board = new char[3][3];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = helper.intToChar(8 - (i * 3 + (2 - j))); //add the required number at each position on the board, with some shenanigans to get them to line up right
			}
		}
	}
	
	/**
	 * prints out the board
	 */
	public void printBoard(){
		System.out.println("");
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j]);
				if(j == 0 || j == 1){ //if we are on the first or second column
					System.out.print(" | ");
				}
			}
			if(i == 0 || i == 1){ //if we are on the first or second row
				System.out.println("\n---------");
			}
		}
	}
	
	public boolean isValidMove(int x, int y){
		return x <= 2 && y <= 2 && x >= 0 && y >= 0 && !(board[y][x] == 'X' || board[y][x] == 'O'); //return true if the move is valid
	}
	/**
	 * Makes the requested move at x,y
	 * @param x
	 * @param y
	 * @param player
	 * @return whether or not the move is valid, and whether or not it was made
	 */
	
	public boolean updateBoard(int x, int y, char player){
		if(x <= 2 && y <= 2 && x >= 0 && y >= 0 && !(board[y][x] == 'X' || board[y][x] == 'O')){ //double check to make sure someone has not already moved there, and that is a position on the board
			board[y][x] = player;
			return true;
		}
		return false;
	}
	
	/**
	 * tests to see whether the passed player has won
	 * @param player
	 * @return whether or not the passed player has won
	 */
	
	public boolean testWin(char player){
		for(int i = 0; i < board.length; i++){ //it's probably easiest to just list out every possibility that some shennanigans like an algorithm
			if(board[i][0] == player && board[i][1] == player && board[i][2] == player){
				return true;
			}
			if(board[0][i] == player && board[1][i] == player && board[2][i] == player){
				return true;
			}
		}
		if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
			return true;
		}
		if(board[0][2] == player && board[1][1] == player && board[2][0] == player){
			return true;
		}
		return false;
	}
}
