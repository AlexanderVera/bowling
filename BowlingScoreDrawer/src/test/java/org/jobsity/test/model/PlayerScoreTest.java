package org.jobsity.test.model;

import org.apache.log4j.Logger;
import org.jobsity.run.model.PlayerScore;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class PlayerScoreTest {
	
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void testConstructor() {

		StringBuilder messageTestPerson = null;
		// Test empty construct
		messageTestPerson = new StringBuilder();
		PlayerScore playerScore = new PlayerScore();
		messageTestPerson.append("playerScoreEmpty ").append(playerScore.getName()).append(" ").append(playerScore.getId())
				.append(" ").append(playerScore.getPinfalls()).append(" ");
		LOG.debug(messageTestPerson.toString());

		
		// Test construct
		messageTestPerson = new StringBuilder();
		playerScore = new PlayerScore("test1",4);
		messageTestPerson.append("playerScoreEmpty ").append(playerScore.getName()).append(" ").append(playerScore.getId())
				.append(" ").append(playerScore.getPinfalls()).append(" ");
		LOG.debug(messageTestPerson.toString());
		

	}
}
