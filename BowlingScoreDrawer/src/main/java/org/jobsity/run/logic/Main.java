package org.jobsity.run.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IFrameDrawer;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.Frame;
import org.jobsity.run.model.PlayerScore;


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
				printBoard(args[0]);
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

	/**
	 * 
	 * @param fileName
	 */
	private static void printBoard(final String fileName) throws BuildException, IOException{
		List<PlayerScore> playerPinfalls;
		final File fileScore = new File(fileName);
		final IFileManager fileManager = new FileManager(fileScore);
		playerPinfalls = fileManager.buildListPlayerFromFile();
		final IPlayerController playerController = new PlayerController();
		final List<Frame> players = playerController.buildPlayerScore(playerPinfalls);
		IFrameDrawer boardDrawer = null;
		boardDrawer = new FrameDrawer(players);
		boardDrawer.printFrame();
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