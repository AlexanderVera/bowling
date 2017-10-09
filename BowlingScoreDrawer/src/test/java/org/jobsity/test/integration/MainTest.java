package org.jobsity.test.integration;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;



/**
 * MainTest
 *
 * @author alexander.vera
 * @since 30/09/2017
 *
 */
@RunWith(JUnit4.class)
public class MainTest extends TestCase{
	private final static Logger LOG = Logger.getLogger(MainTest.class.getName());

	/**
	 * Create the test case Test
	 */
	@Test
	public void printBoardIdealCase() {
		LOG.info("******* Happy path case *******");
		//Happy path
		runTestCase("test-print-happy-path", "test-frame.txt");
	}

	/**
	 * Incomplete Board. Some player don't have all his shoots
	 */
	@Test
	public void printBoardIncompleteCase() {
		runExceptionTestCase("Incomplete shoot for one player", "test-frame-incomplete-shoots.txt");
	}

	/**
	 * Empty Board. The file is empty
	 */
	@Test
	public void printBoardEmptyCase() {
		runExceptionTestCase("Empty file case", "test-frame-empty.txt");
	}

	/**
	 * Extra shoot game. Some player have more shoots
	 */
	@Test
	public void printBoardExtraCase() {
		runExceptionTestCase("Extra shoots for a player case", "test-frame-extra-shoots.txt");
	}

	/**
	 * 4 players Board. Game with 4 players
	 */
	@Test
	public void printBoard4PlayersCase() {
		LOG.info("******* Four players case *******");
		runTestCase("test-print-four-player", "test-frame-4-players.txt");		
	}

	/**
	 * No file to the game
	 */
	@Test
	public void printBoardNoFileCase() {
		runExceptionTestCase("No File case", "test-frame-extra-shoots.txt");
	}
	
	/**
	 * A letter shoot
	 */
	@Test
	public void printBoardLetterCase() {
		runExceptionTestCase("Letter shoot case", "test-frame-letter.txt");
	}
	
	/**
	 * A 12 points shoot
	 */
	@Test
	public void printBoard12Case() {
		runExceptionTestCase("12 points case", "test-frame-12-points.txt");
	}
	
	/**
	 * A invalid shoot
	 */
	@Test
	public void printBoardInvalidCase() {
		runExceptionTestCase("Invalid shoot case", "test-frame-invalid.txt");
	}

	/**
	 * Game with a single player with all strike
	 */
	@Test
	public void printBoardAllStrikeCase() {
		LOG.info("All Strike");
		runTestCase("test-print-all-strike", "test-frame-all-strike.txt");
	}

	/**
	 * Game with a player with all fails
	 */
	@Test
	public void printBoardAllFailCase() {
		LOG.info("All fail");
		runTestCase("test-print-all-fail", "test-frame-all-fail.txt");
	}
	
	/**
	 * Run test case and apply assert
	 * @param expectedName
	 * @param actualName
	 */
	private void runTestCase(String expectedName, String actualName){
		try {
			StringBuilder expectedHappyPath = Utilities.getFullStringFromClassPath(expectedName);
			StringBuilder actualHappyBuildPath = Utilities.printBoard(actualName,true);
			LOG.info(actualHappyBuildPath);
			assertEquals(expectedHappyPath.toString().trim(), actualHappyBuildPath.toString().trim());
		}
		catch(IOException | NumberFormatException | BuildException exc) {
			LOG.error(exc.getMessage());
		}
	}
	
	/**
	 * Run test case and assert with a thrown boolean
	 * Thrown will be false if the test build successfully
	 * Expected true
	 * @param logLabel
	 * @param testFileName
	 */
	private void runExceptionTestCase(String logLabel, String testFileName) {
			StringBuilder logMessages = new StringBuilder();
			logMessages.append("******* ");
			logMessages.append(logLabel);
			logMessages.append(" *******");
			LOG.info(logMessages.toString());
			boolean thrown = false;
			try {
				Utilities.printBoard(testFileName,true);
			} catch (BuildException | NumberFormatException | IOException e) {
				LOG.error(e.getMessage());
				thrown = true;
			}
			assertTrue(thrown);
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