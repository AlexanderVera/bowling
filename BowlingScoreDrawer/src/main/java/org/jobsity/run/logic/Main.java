package org.jobsity.run.logic;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.util.Utilities;


/**
* Main
* @author alexander.vera
* @since 29/10/2017
*
*/

public class Main {
	/**
	 * Static method to manage the log
	 **/
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	/**
	 * Main method to run the app
	 **/
	public static void main(final String[] args) {
		if(args.length>0){
			try{
				StringBuilder gameFrame = Utilities.printBoard(args[0], false);
				System.out.print(gameFrame);
			}
			catch(BuildException bExc){
				LOG.debug(bExc.getMessage());				
			}
			catch(IOException exc){
				LOG.error(exc.getMessage());
			}
		}
		else{
			LOG.info("Insert file path");
		}
	}
}
/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	30/10/2017 		run app with a external name of file
* alexander.vera	30/10/2017 		add if for args[0] condition
* alexander.vera	04/10/2017		remove parameter to PlayerController constructor
*/