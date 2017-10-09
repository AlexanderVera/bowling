package org.jobsity.test.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.logic.FileManager;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class FileManagerTest extends TestCase{

	private static final Logger LOG = Logger.getLogger(FileManagerTest.class
			.getName());
	private static final File FRAME_TEST_FILE = new File(Thread.currentThread()
			.getContextClassLoader().getResource("test-frame.txt").getFile());

	@Test
	public void buildListPlayerFromNoFileTest() {
		boolean thrown = false;
		try {
			LOG.debug("Filemanager.buildListPlayerFromFileTest with no file");
			FileManager fileManager = new FileManager(new File(""));
			fileManager.buildListPlayersFromFile();
		} catch (IOException | BuildException e) {
			LOG.error(e.getMessage());
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void buildListPlayerFromFullFileTest(){
		File fileTest = FRAME_TEST_FILE;
		try {
			FileManager fileManager = new FileManager(new File(""));
			LOG.debug("FileManager.buildListPlayerFromFileTest with a full file");
			fileManager = new FileManager(fileTest);
			List<PlayerScore> playerScores = fileManager.buildListPlayersFromFile();
			assertNotNull(playerScores);
		}
		catch(IOException | BuildException exc) {
			LOG.error(exc.getMessage());
		}
	}
	@Test
	public void validateBoardNoFileTest() {
		LOG.debug("FileManager.validateBoardTest with no file");
		boolean thrown = false;
		try {
			FileManager fileManager = new FileManager(new File(""));
			fileManager.validateFile(new File(""));
		} catch (IOException | BuildException e) {
			LOG.error(e.getMessage());
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	//@Test
	public void validateBoardFullFileTest() {
		LOG.debug("FileManager.validateBoardTest with a full file");
		FileManager fileManager;
		try {
			File fileTest = FRAME_TEST_FILE;
			fileManager = new FileManager(fileTest);
			BufferedReader validFile = fileManager.validateFile(fileTest);
			assertNotNull(validFile);
		} catch (IOException | BuildException e) {
			LOG.error(e.getMessage());
		}
	}
	
	@Test
	public void validateCompleteGame(){
		try {
			LOG.debug("Complete game");
			File file = Utilities.getFileFromClassPath("test-frame.txt", true);
			FileManager filemanager = new FileManager(file);
			List<PlayerScore> playerScores = filemanager.buildListPlayersFromFile();
			assertNotNull(playerScores);
			assertEquals("Jeff", playerScores.get(0).getName());
			LOG.debug(playerScores.get(0).getName());
		} catch (IOException | BuildException e) {
			LOG.debug(e.getMessage());
		}
	}

}
