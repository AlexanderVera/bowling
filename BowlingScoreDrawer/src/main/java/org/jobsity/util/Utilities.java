package org.jobsity.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IBoardDrawer;
import org.jobsity.run.interfaces.IGameParser;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.logic.FileManager;
import org.jobsity.run.logic.BoardDrawer;
import org.jobsity.run.logic.GameParser;
import org.jobsity.run.logic.Messages;
import org.jobsity.run.logic.PlayerController;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.PlayerScore;

/**
 * Utilities Object with a useful utilities for the app.
 *
 * @author alexander.vera
 * @since 30/10/2017
 *
 */
public class Utilities {

	/**
	 * Pins that result as strike
	 **/
	public static final int STRIKE_POINTS = 10;

	/***
	 * Fail shoot
	 */
	public static final String FAIL_SHOOT = "F";

	/**
	 * Max number of shoots by game
	 */
	public static final int SHOOTS_BY_GAME = 21;

	/**
	 * Shoots by normal turn
	 **/
	public static final int SHOOTS_BY_TURN = 2;

	/**
	 * Turn by normal turn
	 **/
	public static final int TURNS_BY_GAME = 10;

	/**
	 * 
	 **/
	public static final int LAST_TURN = 9;
	
	/**
	 * Symbol of spare
	 **/
	public static final char SPARE_SYMBOL = '/';
	
	/**
	 * Symbol of strike
	 **/
	public static final char STRIKE_SYMBOL = 'X';

	/**
	 * Method validate if a string input is a number
	 *
	 * @return A Boolean, true is the number is valid
	 * @param number:
	 *            String with a number value to validate
	 */
	public static boolean validNumber(String number) {
		if (number != null) {
			try {
				Double.parseDouble(number);
				return true;
			} catch (NumberFormatException exc) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Method that convert a string with a number value and return a int if the
	 * string is not a valid number return 0
	 * 
	 * @return A Boolean, true is the number is valid
	 * @param number:
	 *            int
	 */
	public static int parseValidateInteger(String number) {
		if (validNumber(number)) {
			Double outDouble = Double.parseDouble(number);
			return outDouble.intValue();
		} else {
			return 0;
		}
	}

	/**
	 * Generate a ramdom valid frames
	 * 
	 * @params numPlayer: int, number of player to generate
	 */
	public static List<String> generatePlayerFile(int numPlayer) {
		List<String> stringPlayers = new ArrayList<String>();
		StringBuilder playerBuilder;
		for (int i = 0; i < numPlayer; i++) {
			for (int j = 0; j < 20; j++) {
				playerBuilder  = new StringBuilder();
				int points = (int) (Math.random() * 9) + 1;
				playerBuilder.append("Player").append(i).append(" ").append(points);
				stringPlayers.add(playerBuilder.toString());
			}
			playerBuilder  = new StringBuilder();
			playerBuilder.append("Player").append(i).append(" ").append(0);
			stringPlayers.add(playerBuilder.toString());
		}
		return stringPlayers;
	}

	public static List<PlayerScore> generateMockPlayers(){
		List<String> mockStringPlayers = generatePlayerFile(1);
		List<PlayerScore> mockPlayerScores = new ArrayList<PlayerScore>();
		mockStringPlayers.forEach(mockPlayer -> {
			PlayerScore mockPlayerScore = new PlayerScore();
			try {
				List<String> stringPlayerScore = Utilities.split(mockPlayer, "[\\s\\t]+");
				mockPlayerScore.setName(stringPlayerScore.get(0));
				mockPlayerScore.setPinfalls(Utilities.parseValidateInteger(stringPlayerScore.get(1)));
				mockPlayerScores.add(mockPlayerScore);
			} catch (NullPointerException | IOException e) {
			}
		});
		return mockPlayerScores;
	}
	
	/**
	 * Take a player line and split by spaces or tabs
	 * 
	 * @param strLine
	 *            regexp Regular expression for the split
	 * @return List<String>
	 */
	public static List<String> split(String strLine, String regexp) throws NullPointerException, IOException {
		IMessages message = new Messages();
		if (strLine == null || regexp == null) {
			throw new NullPointerException(message.getMessage("src.main.messages.null.var"));
		} else {
			return Arrays.asList(strLine.split(regexp));
		}
	}

	/**
	 * 
	 * @param playerName
	 *            name of the player to show in the message
	 * @param keyMessage
	 *            key to load the message from the properties file
	 * @return String message to print
	 * @throws IOException
	 * @throws BuildException
	 */
	public static String infoPlayerMessage(String playerName, String keyMessage) throws IOException, BuildException {
		IMessages messages = new Messages();
		if (playerName != null && keyMessage != null) {
			StringBuilder infoMessage = new StringBuilder(playerName);
			infoMessage.append("! ").append(messages.getMessage(keyMessage));
			return infoMessage.toString();
		} else {
			throw new BuildException(messages.getMessage("src.main.messages.null.var"));
		}
	}

	/**
	 * 
	 * @param fileName
	 * @param classPath
	 *            Flag, if true load the file from the classPath
	 * @return
	 */
	public static File getFileFromClassPath(String fileName, boolean classPath) throws IOException {
		File file;
		IMessages messages = new Messages();
		if (classPath) {
			// Get the .txt file from the classpath
			URL fileFromPath = Thread.currentThread().getContextClassLoader().getResource(fileName);
			if (fileFromPath != null) {
				// Load the file object by the URL File Path
				file = new File(Thread.currentThread().getContextClassLoader().getResource(fileName).getFile());
			} else {
				throw new IOException(messages.getMessage("src.main.messages.file.not.found"));
			}
		} else {
			file = new File(fileName);
			if (!file.exists()) {
				throw new IOException(messages.getMessage("src.main.messages.file.not.found"));
			}
		}
		return file;
	}
	
	public static StringBuilder printBoard(String fileName, boolean classPath) throws BuildException, IOException, NumberFormatException{
			GameBoard gameBoard = new GameBoard();
			File fileScore = getFileFromClassPath(fileName, classPath);
			IFileManager fileManager = new FileManager(fileScore);
			IGameParser gameParser = new GameParser(fileManager.buildListPlayersFromFile());
			gameBoard = gameParser.buildBoardFromPlayers();
			final IPlayerController playerController = new PlayerController();
			gameBoard.setListOfGameLines(playerController.calculateScore(gameBoard.getListOfGameLines()));
			IBoardDrawer boardDrawer = null;
			boardDrawer = new BoardDrawer(gameBoard);
			return boardDrawer.printBoard();
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static StringBuilder getFullStringFromClassPath(String fileName) throws IOException{
		File file;
		IMessages messages = new Messages();
		try {
			file = getFileFromClassPath(fileName, true);
			if(file != null && file.exists()){
				BufferedReader buffer = new BufferedReader(new FileReader(file));
				StringBuilder fileText = new StringBuilder();
				String textLine;
				while ((textLine = buffer.readLine()) != null) {
					fileText.append(textLine);
					fileText.append("\n");
				}
				buffer.close();
				return fileText;
			}
			else{
				throw new IOException(messages.getMessage("src.main.messages.file.not.found"));
			}
		}
		catch(IOException exc) {
			throw new IOException(messages.getMessage("src.main.messages.file.not.found"));			
		}
	}
}

/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera 02/10/2017 add generatePlayerFile method
*/