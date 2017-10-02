package org.jobsity.test.logic;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.logic.FrameDrawer;
import org.jobsity.run.logic.FileManager;
import org.jobsity.run.logic.PlayerController;
import org.jobsity.run.model.Frame;
import org.junit.Test;


/**
* FileManager
*
*
* @author alexander.vera
* @since 30/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		add validateBoard method
*/
public class MainTest {
	private final static Logger LOG = Logger.getLogger(PlayerController.class.getName());
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
	@Test
	public void integrationTest(){
		
		LOG.info("Ideal case");
		//Nice frame
		printBoard("test-frame.txt");

		LOG.info("Incomplete shoot for one player");
		//Incomplete frame
		printBoard("test-frame-incomplete-shoots.txt");	
		
		LOG.info("Empty file");
		//Empty frame
		printBoard("test-frame-empty.txt");
		
		LOG.info("Extra shoots for a player");
		//Extra shoot frame
		printBoard("test-frame-extra-shoots.txt");

		LOG.info("Four players");
		//4 players frame
		printBoard("test-frame-4-players.txt");
		
		LOG.info("No File");
		//4 players frame
		printBoard("test-frame-fake.txt");
		
	}
	
	public void printBoard(String fileName){
		List<String> playerPinfalls = null;
		URL fileFromPath = Thread.currentThread().getContextClassLoader().getResource(fileName);
		if(fileFromPath != null){
			File fileScore = new File(
				Thread.currentThread().getContextClassLoader().getResource(fileName).getFile());
			IFileManager fileManager = new FileManager(fileScore);
			playerPinfalls = fileManager.buildListPlayerFromFile();

			IPlayerController playerController = new PlayerController(playerPinfalls);
			List<Frame> players = playerController.buildPlayerScore(playerPinfalls);
		
			FrameDrawer boardDrawer = null;
			boardDrawer = new FrameDrawer(players);
			boardDrawer.printFrame();
		}
		else{
			LOG.debug("File not found");
		}
	}
	
}
