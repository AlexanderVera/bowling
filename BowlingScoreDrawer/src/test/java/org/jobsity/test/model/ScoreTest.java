package org.jobsity.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.jobsity.run.model.ScoreFrame;
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
		ScoreFrame score = new ScoreFrame();
		messageTestPerson.append("ScoreEmpty ").append(score.getShoots()[0]).append(" ").append(score.getShoots()[1])
				.append(" ").append(score.getShoots()[2]).append(" ").append(score.getTotal()).append(" ")
				.append(score.getFlagFinal()).append(" ").append(score.isSpare()).append(" ").append(score.isStrike())
				.append(" ");
		
		int[] mockZeroPins = {0,0,0};
		assertEquals(mockZeroPins[0], score.getShoots()[0]);
		assertEquals(mockZeroPins[1], score.getShoots()[1]);
		assertEquals(mockZeroPins[2], score.getShoots()[2]);

		assertEquals(0, score.getTotal());
		assertFalse(score.isSpare());
		assertFalse(score.isStrike());
		LOG.debug(messageTestPerson.toString());
		
		// Test construct
		messageTestPerson = new StringBuilder();
		ScoreFrame scoreComplete = new ScoreFrame(true);
		int[] mockPins = {2,9,0};
		scoreComplete.setShoots(mockPins);
		scoreComplete.setSpare(true);
		scoreComplete.setStrike(true);
		scoreComplete.setTotal(11);
		
		messageTestPerson.append("ScoreComplete ").append(scoreComplete.getShoots()[0]).append(" ").append(scoreComplete.getShoots()[1])
		.append(" ").append(scoreComplete.getShoots()[2]).append(" ").append(scoreComplete.getTotal()).append(" ")
		.append(scoreComplete.getFlagFinal()).append(" ").append(scoreComplete.isSpare()).append(" ").append(scoreComplete.isStrike())
		.append(" ");
		
		assertEquals(mockPins[0], scoreComplete.getShoots()[0]);
		assertEquals(mockPins[1], scoreComplete.getShoots()[1]);
		assertEquals(mockPins[2], scoreComplete.getShoots()[2]);
		
		assertEquals(11, scoreComplete.getTotal());
		assertTrue(scoreComplete.isSpare());
		assertTrue(scoreComplete.isStrike());
		
		LOG.debug(messageTestPerson.toString());
		

	}
}
