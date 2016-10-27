
public class helper {
	
	/**
	 * 
	 * @param input
	 * @return the input of an int converted to the equivelant char
	 */
	public static char intToChar(int input){
		return (char)(input + 49);
	}
	
	/**
	 * clears the console
	 */
	public static void clear(){
		for(int i = 0; i < 20; i++){
			System.out.println("");
		}
	}
	
}
