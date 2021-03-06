package org.jobsity.test.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.BowlingBoardDrawer;
import org.jobsity.run.model.GameBoard;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.ScoreFrame;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

/**
 * 
 * @author alexander.vera
 * @since 2017/10/01
 * 
 * HISTORY CHANGES
 * 
 */
@RunWith(JUnit4.class)
public class BoardDrawerTest extends TestCase{
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
		
	/*Test the printFrame in 3 scenarios: null, empty and full list. 
	*/
	@Test
	public void printNullBoardTest() {	
		List<GameLine> nullListGame = null;
		GameBoard game;
		StringBuilder boardToPrint;
		try{
			game = new GameBoard(nullListGame);
			boardToPrint = printBoardCases("Print frame with a null list", game);
			LOG.debug(boardToPrint.toString());
			assertEquals(new StringBuilder("\n").toString().trim(), boardToPrint.toString().trim());
		}
		catch(IOException exc){
			LOG.debug(exc.getMessage());
		}
	}
	
	@Test
	public void printEmptyBoardTest() {	
		List<GameLine> emptyListGame = new ArrayList<GameLine>();
		GameBoard game;
		StringBuilder boardToPrint;
		StringBuilder expectedString;
		try{
			game = new GameBoard(emptyListGame);
			boardToPrint = printBoardCases("Print frame with a empty list", game);
			expectedString = Utilities.getFullStringFromClassPath("test-print-empty.txt");
			LOG.debug(boardToPrint.toString());
			assertEquals(expectedString.toString().trim(), boardToPrint.toString().trim());
		}
		catch(IOException exc){
			LOG.debug(exc.getMessage());
		}
	}
	
	
	@Test
	public void printFullBoardTest() {	
		GameBoard game;
		StringBuilder boardToPrint;
		try{	
			game = new GameBoard(makeFullListOfBoards());
			boardToPrint = printBoardCases("Print frame with a dummie List", game);
			LOG.debug(boardToPrint.toString());
		}
		catch(IOException exc){
			LOG.debug(exc.getMessage());
		}
	}
	
	
	/***
	 * PrintFrame
	 * @param frames
	 * @throws IOException 
	 */
	public StringBuilder printBoardCases(String message, GameBoard frame) throws IOException{
		StringBuilder messageTest = new StringBuilder(message);		
		BowlingBoardDrawer boardTestEmpty = new BowlingBoardDrawer(frame.getListOfGameLines());
		LOG.debug(messageTest.toString());
		return boardTestEmpty.printBoard();		
	}
	
	/**
	 * @return List<GameLine> List of gameLines with dummie players
	 */
	public List<GameLine> makeFullListOfBoards(){
		List<GameLine> fullGameLines = new ArrayList<GameLine>();
		GameLine gameLine = null;
		for(int i = 0; i< 10; i++){
			gameLine = new GameLine();
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			gameLine.setPlayerName(name.toString());
			List<ScoreFrame> scores = new ArrayList<ScoreFrame>(); 
			gameLine.setScore(scores);
			fullGameLines.add(gameLine);
		}
		return fullGameLines;
	}
	
}
