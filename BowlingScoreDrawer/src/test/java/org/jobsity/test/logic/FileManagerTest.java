package org.jobsity.test.logic;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.FileManager;
import org.junit.Test;

public class FileManagerTest {

	private static final Logger LOG = Logger.getLogger(FileManagerTest.class.getName());
	private static final File FRAME_TEST_FILE = new File(
			Thread.currentThread().getContextClassLoader().getResource("test-frame.txt").getFile());

	
	@Test
	public void buildListPlayerFromFileTest() {
		
		LOG.debug("Filemanager.buildListPlayerFromFileTest with no file");
		FileManager fileManager = new FileManager(new File(""));
		fileManager.buildListPlayerFromFile();
		
		File fileTest = FRAME_TEST_FILE;
		LOG.debug("FileManager.buildListPlayerFromFileTest with a full file");
		fileManager = new FileManager(fileTest);
		fileManager.buildListPlayerFromFile();
	}

	@Test
	public void validateBoardTest() {

		LOG.debug("FileManager.validateBoardTest with no file");
		FileManager fileManager = new FileManager(new File(""));
		try {
			fileManager.validateBoard(new File(""));
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		}
		
		File fileTest = FRAME_TEST_FILE;
		LOG.debug("FileManager.validateBoardTest with a full file");
		fileManager = new FileManager(fileTest);
		try {
			fileManager.validateBoard(new File(""));
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		}
	}

}
