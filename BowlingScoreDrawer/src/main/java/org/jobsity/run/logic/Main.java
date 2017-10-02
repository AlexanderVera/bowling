package org.jobsity.run.logic;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IFrameDrawer;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.Frame;



/**
* Main
*
*
* @author alexander.vera
* @since 29/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	30/10/2017 		run app with a external name of file
* alexander.vera	30/10/2017 		add if for args[0] condition
* 
*/

public class Main {
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {

		if(args.length>0){
			printBoard(args[0]);
		}
		else{
			LOG.info("Insert file path");
		}
	}
	
	public static void printBoard(String fileName){
		List<String> playerPinfalls = null;
		File fileScore = new File(fileName);
		IFileManager fileManager = new FileManager(fileScore);
		playerPinfalls = fileManager.buildListPlayerFromFile();

		IPlayerController playerController = new PlayerController(playerPinfalls);
		List<Frame> players = playerController.buildPlayerScore(playerPinfalls);
		
		IFrameDrawer boardDrawer = null;
		boardDrawer = new FrameDrawer(players);
		boardDrawer.printFrame();
	}

}
