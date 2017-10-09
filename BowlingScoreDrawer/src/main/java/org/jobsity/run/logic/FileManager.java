package org.jobsity.run.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;

/**
 * FileManager Class. Manage the input file with the scores.
 * 
 * @author alexander.vera
 * @since 30/10/2017
 *
 */
public class FileManager implements IFileManager {
	/**
	 * Name of the file with the data games
	 * 
	 * @return String fileName
	 */
	private String fileName;

	/**
	 * Object to load the file
	 * 
	 * @return
	 */
	private File file;

	/**
	 * Parameter to get the message or key words
	 */
	private final IMessages messages;

	/**
	 * Constructor for FileManager Class
	 * 
	 * @throws IOException
	 */
	public FileManager(File file) throws IOException {
		this.file = file;
		messages = new Messages();
	}
	
	
	/**
	 * Method that take the input file and make a list of file lines With the player
	 * and his pinfalls
	 * 
	 * @return A list of String, each line is a player info
	 */
	public List<PlayerScore> buildListPlayersFromFile() throws BuildException, IOException, NumberFormatException {
		List<PlayerScore> playerShoots = null;
		BufferedReader bufferPlayerLines;
		bufferPlayerLines = validateFile(getFile());
		String playerLine;
		if (bufferPlayerLines != null) {
			playerShoots = new ArrayList<PlayerScore>();
			while ((playerLine = bufferPlayerLines.readLine()) != null) {
				if (playerLine.trim().length() > 0) {
					playerShoots.add(buildPlayerByLine(playerLine));
				}
			}
			Collections.sort(playerShoots);
		}
		else {
			throw new BuildException(messages.getMessage("src.main.messages.no.players"));
		}
		return playerShoots;
	}

	/**
	 * 
	 * @param plainPlayer
	 *            String with a line player/pins (e.g. 'Lady 20')
	 * @return PlayerScore a new player with then name and a score
	 */
	private PlayerScore buildPlayerByLine(String plainPlayer)
			throws BuildException, NumberFormatException, NullPointerException, IOException {
		// Initialize a new player
		PlayerScore newPlayerScore = new PlayerScore();
		// Split the player line in a name and pins
		List<String> plainScoreSplited = Utilities.split(plainPlayer.trim(), "[\\s\\t]+");

		// Set the name in the Player
		newPlayerScore.setName(plainScoreSplited.get(0));

		// Validate if the line come with a valid number of pins. (Maria 10 =>
		// Ok, Juan => Bad)
		String playerName = newPlayerScore.getName();

		if (plainScoreSplited.size() > 1) {
			if (Utilities.validNumber(plainScoreSplited.get(1))
					|| Utilities.FAIL_SHOOT.equals(plainScoreSplited.get(1))) {
				int pins = Utilities.parseValidateInteger(plainScoreSplited.get(1));
				// if the shoot is more than 10
				if (pins > Utilities.STRIKE_POINTS) {
					throw new BuildException(Utilities.infoPlayerMessage(playerName,"src.main.messages.much.pins"));
				} else {
					newPlayerScore.setPinfalls(pins);
				}
			} else {
				// if one shoot is not a number
				throw new NumberFormatException(Utilities.infoPlayerMessage(playerName,"src.main.messages.alpha.shoot"));
			}
		} else {
			// If the number of pins is not valid.
			throw new BuildException(Utilities.infoPlayerMessage(playerName,"src.main.messages.invalid.shoot"));
		}
		return newPlayerScore;
	}

	/**
	 * Read a file and check if the file has content or exists
	 *
	 * @param file
	 *            File with score game
	 * @return a BufferedReader with the content file.
	 */
	public BufferedReader validateFile(File file) throws IOException, BuildException {
		if (file.exists()) {
			if (file.length() == 0) {
				throw new BuildException(messages.getMessage("src.main.messages.empty.file"));
			} else {
				return new BufferedReader(new FileReader(file));
			}
		} else {
			throw new IOException(messages.getMessage("src.main.messages.file.not.found"));
		}
	}

	/**
	 * Getter from fileName
	 * 
	 * @return String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter from fileName
	 * 
	 * @param {string}
	 *            fileName Name of the file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Getter from file object
	 * 
	 * @return File
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Setter from fileName
	 * 
	 * @param file Set the File
	 */
	public void setFile(File file) {
		this.file = file;
	}

}

/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera 	01/10/2017 		add validateBoard method 
* alexander.vera 	04/10/2017 		add getANewPlayer method 
* alexander.vera 	04/10/2017 		change getANewPlayer name by buildPlayerByLine
*/