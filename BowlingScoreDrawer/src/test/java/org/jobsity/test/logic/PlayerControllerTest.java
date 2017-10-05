package org.jobsity.test.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.logic.PlayerController;
import org.jobsity.run.model.PlayerScore;
import org.junit.Test;

public class PlayerControllerTest {
	private final static Logger LOG = Logger.getLogger(PlayerController.class.getName());
	@Test
	public void buildPlayerScoreTest(){
		
		List<PlayerScore> listPlainPlayers = new ArrayList<PlayerScore>();
		
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("John",3));
		listPlainPlayers.add(new PlayerScore("John",7));
		listPlainPlayers.add(new PlayerScore("Jeff",7));
		listPlainPlayers.add(new PlayerScore("Jeff",3));
		listPlainPlayers.add(new PlayerScore("John",6));
		listPlainPlayers.add(new PlayerScore("John",3));
		listPlainPlayers.add(new PlayerScore("Jeff",9));
		listPlainPlayers.add(new PlayerScore("Jeff",0));
		listPlainPlayers.add(new PlayerScore("John",10));
		listPlainPlayers.add(new PlayerScore("Jeff",10));	
		listPlainPlayers.add(new PlayerScore("John",8));
		listPlainPlayers.add(new PlayerScore("John",1));
		listPlainPlayers.add(new PlayerScore("Jeff",0));
		listPlainPlayers.add(new PlayerScore("Jeff",8));
		listPlainPlayers.add(new PlayerScore("John",10));
		listPlainPlayers.add(new PlayerScore("Jeff",8));
		listPlainPlayers.add(new PlayerScore("Jeff",2));
		listPlainPlayers.add(new PlayerScore("John",10));
		listPlainPlayers.add(new PlayerScore("Jeff",0));
		listPlainPlayers.add(new PlayerScore("Jeff",6));
		listPlainPlayers.add(new PlayerScore("John",9));
		listPlainPlayers.add(new PlayerScore("John",0));
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("John",7));
		listPlainPlayers.add(new PlayerScore("John",3));
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("John",4));
		listPlainPlayers.add(new PlayerScore("John",4));
		listPlainPlayers.add(new PlayerScore("Jeff",10));
		listPlainPlayers.add(new PlayerScore("Jeff",8));
		listPlainPlayers.add(new PlayerScore("Jeff",1));
		listPlainPlayers.add(new PlayerScore("John",10));
		listPlainPlayers.add(new PlayerScore("John",9));
		listPlainPlayers.add(new PlayerScore("John",0));

		try{
			PlayerController player = new PlayerController();
			player.buildPlayerScore(listPlainPlayers);
		}
		catch(BuildException | IOException exc){
			LOG.error(exc.getMessage());
		}
	}

	
}
