package org.jobsity.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.jobsity.run.logic.ScoreBoardControllerImpl;
import org.jobsity.run.model.Player;
import org.jobsity.run.model.Score;
import org.junit.Test;

public class ScoreBoardControllerTest {


	@Test
	public void scoreBoardConstructorTest(){
		List<String> listPlainPlayers = new ArrayList<String>();
		
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		
		new ScoreBoardControllerImpl(listPlainPlayers);
		
		listPlainPlayers = new ArrayList<String>();
		new ScoreBoardControllerImpl(listPlainPlayers);
		
		listPlainPlayers = null;
		new ScoreBoardControllerImpl(listPlainPlayers);
		
	}
	
	@Test
	public void addPlayerTest() {
		List<Player> nullListPlayers = null;
		List<Player> emptyListPlayers = new ArrayList<Player>();
		List<Player> fullListPlayers = new ArrayList<Player>();
		
		Player tempPlayer = null;
		Player nullPlayer = null;
		Player emptyPlayer = new Player();
		List<Score> testScores = new ArrayList<Score>(); 
		Player fullPlayer = new Player(1L, "test", testScores);
		
		for(int i = 0; i< 10; i++){
			tempPlayer = new Player();
			tempPlayer.setId(System.currentTimeMillis());
			StringBuilder name = new StringBuilder("Test");
			name.append(i);
			tempPlayer.setName(name.toString());
			tempPlayer.setScore(testScores);
			fullListPlayers.add(tempPlayer);
		}
		
		List<String> listPlainPlayers = new ArrayList<String>();
		
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		listPlainPlayers.add("Carl 10");
		
		ScoreBoardControllerImpl testScoreBoard = new ScoreBoardControllerImpl(listPlainPlayers);
		testScoreBoard.addPlayer(nullPlayer, nullListPlayers);
		testScoreBoard.addPlayer(emptyPlayer, emptyListPlayers);
		testScoreBoard.addPlayer(fullPlayer, fullListPlayers);

		testScoreBoard.addPlayer(nullPlayer, emptyListPlayers);
		testScoreBoard.addPlayer(emptyPlayer, fullListPlayers);
		testScoreBoard.addPlayer(fullPlayer, nullListPlayers);
		
		testScoreBoard.addPlayer(nullPlayer, fullListPlayers);
		testScoreBoard.addPlayer(emptyPlayer, nullListPlayers);
		testScoreBoard.addPlayer(fullPlayer, emptyListPlayers);
		
	}

	@Test
	public void numberOfPlayersTest() {
		
	}

	@Test
	public void calculateScoreTest() {
		
	}

	@Test
	public void evaluateBonusTest() {

	}
	
	@Test
	public void buildFrameTest() {
		
	}

}
