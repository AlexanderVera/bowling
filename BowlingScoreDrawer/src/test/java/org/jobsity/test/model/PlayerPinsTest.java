package org.jobsity.test.model;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.jobsity.run.model.PlayerPins;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class PlayerPinsTest {

	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());

	@Test
	public void testConstructor() {

		StringBuilder messageTestPerson = null;
		// Test empty construct
		messageTestPerson = new StringBuilder();
		PlayerPins playerScore = new PlayerPins();
		messageTestPerson.append("playerScoreEmpty ").append(playerScore.getName()).append(" ")
				.append(playerScore.getPinfalls()).append(" ");
		assertEquals("",playerScore.getName());
		assertEquals(0, playerScore.getPinfalls());
		LOG.debug(messageTestPerson.toString());

		// Test construct
		messageTestPerson = new StringBuilder();
		playerScore = new PlayerPins("test1", 4);
		messageTestPerson.append("playerScoreEmpty ").append(playerScore.getName()).append(" ")
				.append(playerScore.getPinfalls()).append(" ");
		assertEquals("test1",playerScore.getName());
		assertEquals(4, playerScore.getPinfalls());
		LOG.debug(messageTestPerson.toString());

	}
}
