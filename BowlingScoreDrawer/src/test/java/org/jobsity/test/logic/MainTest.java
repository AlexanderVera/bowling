package org.jobsity.test.logic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.logic.FrameDrawer;
import org.jobsity.run.logic.FileManager;
import org.jobsity.run.logic.PlayerController;
import org.jobsity.run.model.Frame;
import org.jobsity.run.model.PlayerScore;
import org.junit.Test;

/**
 * MainTest
 *
 * @author alexander.vera
 * @since 30/09/2017
 *
 */
public class MainTest {
	private final static Logger LOG = Logger.getLogger(PlayerController.class.getName());

	/**
	 * Create the test case Test
	 */
	//@Test
	public void printFrameIdealCase() {
		LOG.info("Ideal case");
		// Nice frame
		try {
			printBoard("test-frame.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * Incomplete frame. Some player don't have all his shoots
	 */
	//@Test
	public void printFrameIncompleteCase() {
		LOG.info("Incomplete shoot for one player");
		try {
			printBoard("test-frame-incomplete-shoots.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * Empty frame. The file is empty
	 */
	//@Test
	public void printFrameEmptyCase() {
		LOG.info("Empty file");
		try {
			printBoard("test-frame-empty.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * Extra shoot frame. Some player have more shoots
	 */
	//@Test
	public void printFrameExtraCase() {
		LOG.info("Extra shoots for a player");
		try {
			printBoard("test-frame-extra-shoots.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * 4 players frame. Game with 4 players
	 */
	@Test
	public void printFrame4PlayersCase() {
		LOG.info("Four players");
		try {
			printBoard("test-frame-4-players.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * No file to the game
	 */
	//@Test
	public void printFrameNoFileCase() {
		LOG.info("No File");
		try {
			printBoard("test-frame-fake.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * Game with a single player with all strike
	 */
	//@Test
	public void printFrameAllStrikeCase() {
		LOG.info("All Strike");
		try {
			printBoard("test-frame-all-strike.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}

	/**
	 * Game with a player with all fails
	 */
	//@Test
	public void printFrameAllFailCase() {
		LOG.info("All fail");
		try {
			printBoard("test-frame-all-fail.txt");
		} catch (BuildException | IOException e) {
			LOG.debug(e.getMessage());
		}
	}


	/***
	 * Method get the fileName and print the frame of game
	 * 
	 * @param fileName
	 */
	public void printBoard(String fileName) throws BuildException, IOException {
		List<PlayerScore> playerPinfalls = null;
		// Get the .txt file from the classpath
		URL fileFromPath = Thread.currentThread().getContextClassLoader().getResource(fileName);
		if (fileFromPath != null) {
			// Load the file object by the URL File Path
			File fileScore = new File(Thread.currentThread().getContextClassLoader().getResource(fileName).getFile());
			// Call a class FileManager to get list of String score in the file
			IFileManager fileManager = new FileManager(fileScore);
			playerPinfalls = fileManager.buildListPlayerFromFile();
			// Call a class PlayerController to convert the player scores in a
			// List of Frame to print
			IPlayerController playerController = new PlayerController();
			List<Frame> players = playerController.buildPlayerScore(playerPinfalls);
			// Call a class Frame drawer to print, all the frames by player
			FrameDrawer boardDrawer = new FrameDrawer(players);
			boardDrawer.printFrame();
		} else {
			LOG.debug("File not found");
		}
	}
}

/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera 	01/10/2017 		group test methods in one
* 
**/