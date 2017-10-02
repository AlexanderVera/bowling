package org.jobsity.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.jobsity.run.logic.PlayerController;
import org.junit.Test;

public class PlayerControllerTest {
	
	@Test
	public void buildPlayerScoreTest(){
		
		List<String> listPlainPlayers = new ArrayList<String>();
		
		
		listPlainPlayers.add("Jeff 10");
		listPlainPlayers.add("John 3");
		listPlainPlayers.add("John 7");
		listPlainPlayers.add("Jeff 7");
		listPlainPlayers.add("Jeff 3");
		listPlainPlayers.add("John 6");
		listPlainPlayers.add("John 3");
		listPlainPlayers.add("Jeff 9");
		listPlainPlayers.add("Jeff 0");
		listPlainPlayers.add("John 10");
		listPlainPlayers.add("Jeff 10");
		
		
		listPlainPlayers.add("John 8");
		
		
		listPlainPlayers.add("John 1");
		listPlainPlayers.add("Jeff 0");
		listPlainPlayers.add("Jeff 8");
		listPlainPlayers.add("John 10");
		listPlainPlayers.add("Jeff 8");
		listPlainPlayers.add("Jeff 2");
		listPlainPlayers.add("John 10");
		listPlainPlayers.add("Jeff F");
		listPlainPlayers.add("Jeff 6");

		listPlainPlayers.add("John 9");
		
		
		listPlainPlayers.add("John 0");
		listPlainPlayers.add("Jeff 10");
		listPlainPlayers.add("John 7");

		listPlainPlayers.add("John 3");
		listPlainPlayers.add("Jeff 10");
		listPlainPlayers.add("John 4");
		listPlainPlayers.add("John 4");
		listPlainPlayers.add("Jeff 10");
		
		listPlainPlayers.add("Jeff 8");
		listPlainPlayers.add("Jeff 1");
		listPlainPlayers.add("John 10");
		listPlainPlayers.add("John 9");
		listPlainPlayers.add("John 0");
		
		
		/*
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
		*/
		
		
		PlayerController player = new PlayerController(listPlainPlayers);
		player.buildPlayerScore(listPlainPlayers);
		
	}

	
}
