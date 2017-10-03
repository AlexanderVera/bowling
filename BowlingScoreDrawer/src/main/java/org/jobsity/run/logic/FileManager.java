package org.jobsity.run.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IMessages;


/**
* FileManager
* Class for manage file of the game
* @author alexander.vera
* @since 30/10/2017
*
*/
public class FileManager implements IFileManager {
	
	/**
	 * Name of the file with the data games
	 * */
	private String fileName;
	
	/**
	 * Static parameter to set the LOG
	 * */
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	/**
	 * Object to load the file
	 * */
	private File file;
	
	/**
	 * Parametter to get the message or key words
	 * */
	private transient final IMessages messages;

	/**
	 * Contructor for FileManager Class
	 * */
	public FileManager(final File file) {
		this.file = file;
		messages = new Messages();
	}

	/**
     * Method that take the input file and make a list of file lines 
     * With the player and his pinfalls
     * @return A list of String, each line is a player info
     */
	public List<String> buildListPlayerFromFile() {


		List<String> playerLines = null;
		try {
			BufferedReader bufferPlayerLines;
			bufferPlayerLines = validateBoard(getFile());
			String playerLine;
			if (bufferPlayerLines != null) {
				playerLines = new ArrayList<String>();
				while ((playerLine = bufferPlayerLines.readLine()) != null) {
					if(playerLine.trim().length()>0){
						playerLines.add(playerLine);
					}
				}
			}
		} catch (IOException exc) {
			LOG.error(exc.getMessage());
		}
		return playerLines;
	}

	
	/**
     * Read a file and check if the file has content or exists
     *
     * @param Object file
     * @return a BufferedReader with the content file.
     */
	public BufferedReader validateBoard(final File file) throws IOException {
		if(file.exists()){
			if (file.length() == 0) {
				throw new IOException(messages.getMessage("src.main.messages.empty.file"));
			} else {
				return new BufferedReader(new FileReader(file));
			}			
		}
		else {
			throw new IOException(messages.getMessage("src.main.messages.file.not.found"));
		}
	}

	/**
	 * Getter from fileName
	 * */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter from fileName
	 * */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Getter from file object
	 * */
	public File getFile() {
		return file;
	}

	/**
	 * Setter from fileName
	 * */
	public void setFile(final File file) {
		this.file = file;
	}

}

/**
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		add validateBoard method
*/