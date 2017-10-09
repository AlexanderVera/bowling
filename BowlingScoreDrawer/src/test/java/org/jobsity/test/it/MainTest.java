package org.jobsity.test.it;

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
	public void printFrameIdealCase() {
		LOG.info("Happy path");
		//Happy path
		runTestCase("test-print-happy-path", "test-frame.txt");
	}

	/**
	 * Incomplete frame. Some player don't have all his shoots
	 */
	@Test
	public void printFrameIncompleteCase() {
		LOG.info("Incomplete shoot for one player");
		boolean thrown = false;
		try {
			Utilities.printBoard("test-frame-incomplete-shoots.txt",true);
		} catch (BuildException | IOException e) {
			LOG.error(e.getMessage());
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * Empty frame. The file is empty
	 */
	@Test
	public void printFrameEmptyCase() {
		LOG.info("Empty file");
		boolean thrown = false;
		try {
			Utilities.printBoard("test-frame-empty.txt",true);
		} catch (BuildException | IOException e) {
			LOG.error(e.getMessage());
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * Extra shoot frame. Some player have more shoots
	 */
	@Test
	public void printFrameExtraCase() {
		LOG.info("Extra shoots for a player");
		boolean thrown = false;
		try {
			Utilities.printBoard("test-frame-extra-shoots.txt",true);
		} catch (BuildException | IOException e) {
			LOG.error(e.getMessage());
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * 4 players frame. Game with 4 players
	 */
	@Test
	public void printFrame4PlayersCase() {
		LOG.info("Four players");
		runTestCase("test-print-four-player", "test-frame-4-players.txt");		
	}

	/**
	 * No file to the game
	 */
	@Test
	public void printFrameNoFileCase() {
		LOG.info("No File");
		boolean thrown = false;
		try {
			Utilities.printBoard("test-frame-fake.txt",true);
		} catch (BuildException | IOException e) {
			LOG.error(e.getMessage());
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	 * Game with a single player with all strike
	 */
	@Test
	public void printFrameAllStrikeCase() {
		LOG.info("All Strike");
		runTestCase("test-print-all-strike", "test-frame-all-strike.txt");
	}

	/**
	 * Game with a player with all fails
	 */
	@Test
	public void printFrameAllFailCase() {
		LOG.info("All fail");
		runTestCase("test-print-all-fail", "test-frame-all-fail.txt");
	}
	
	/**
	 * Run test case and apply assert
	 * @param expectedName
	 * @param actualName
	 */
	public void runTestCase(String expectedName, String actualName){
		try {
			StringBuilder expectedHappyPath = Utilities.getFullStringFromClassPath(expectedName);
			StringBuilder actualHappyBuildPath = Utilities.printBoard(actualName,true);
			LOG.info(actualHappyBuildPath);
			assertEquals(expectedHappyPath.toString().trim(), actualHappyBuildPath.toString().trim());
		}
		catch(IOException | BuildException exc) {
			LOG.error(exc.getMessage());
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