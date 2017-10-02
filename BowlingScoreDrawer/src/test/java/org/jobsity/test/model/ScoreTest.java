package org.jobsity.test.model;

import org.apache.log4j.Logger;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ScoreTest {
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());

	@Test
	public void testConstructor() {

		StringBuilder messageTestPerson = null;
		// Test empty construct
		messageTestPerson = new StringBuilder();
		Score score = new Score();
		messageTestPerson.append("ScoreEmpty ").append(score.getFirstTry()).append(" ").append(score.getSecondTry())
				.append(" ").append(score.getTirdTry()).append(" ").append(score.getTotal()).append(" ")
				.append(score.getFlagFinal()).append(" ").append(score.isSpare()).append(" ").append(score.isStrike())
				.append(" ");
		LOG.debug(messageTestPerson.toString());

		// Test construct
		messageTestPerson = new StringBuilder();
		Score scoreComplete = new Score(2, 1, 0, false);
		messageTestPerson.append("ScoreComplete ").append(scoreComplete.getFirstTry()).append(" ").append(scoreComplete.getSecondTry())
		.append(" ").append(scoreComplete.getTirdTry()).append(" ").append(scoreComplete.getTotal()).append(" ")
		.append(scoreComplete.getFlagFinal()).append(" ").append(scoreComplete.isSpare()).append(" ").append(scoreComplete.isStrike())
		.append(" ");
		LOG.debug(messageTestPerson.toString());
		

	}
}
