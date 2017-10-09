package org.jobsity.test.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.PlayerController;
import org.jobsity.run.model.PlayerScore;
import org.junit.Test;

public class PlayerControllerTest {
	private final static Logger LOG = Logger.getLogger(PlayerController.class.getName());
	@Test
	public void buildPlayerScoreTest(){

		try{
			PlayerController player = new PlayerController();
			//player.calculateScore(listPlainPlayers);
		}
		catch(IOException exc){
			LOG.error(exc.getMessage());
		}
	}

	
}
