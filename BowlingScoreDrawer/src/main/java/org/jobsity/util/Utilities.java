package org.jobsity.util;


/**
* Utilities
* Object with a useful utilities for the app
*
* @author alexander.vera
* @since 30/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* 
*/
public class Utilities {

	//private final static Logger LOG = Logger.getLogger(Utilities.class.getName());

	/**
     * Method validate if a string input is a number
     *
     * @return A Boolean, true is the number is valid
     * @param number: String with a number value to validate
     */
	public static boolean validNumber(String number){
		if (number != null) {
			try {
				Double.parseDouble(number);
				return true;
			} catch (NumberFormatException exc) {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
     * Method that convert a string with a number value and return a int
     * if the string is not a valid number return 0
     * @return A Boolean, true is the number is valid
     * @param number: int
     */	
	public static int parseValidateInteger(String number){
		if (validNumber(number)) {
			Double outDouble = Double.parseDouble(number);
			return outDouble.intValue();
		} else {
			return 0;
		}
	}
	
	
	/**
	 * Generate a ramdom valid frames
	 * @params numPlayer: int, number of player to generate
	 */
	public static void generatePlayerFile(int numPlayer){
		for(int i=0;i<numPlayer;i++){
			for(int j=0;j<20;j++ ){
				int points = (int)(Math.random() * 9) + 1;
				System.out.println("Player"+i+" "+points);
			}
			System.out.println("Player"+i+" "+0);
		}
	}
	

}