package org.jobsity.test.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jobsity.run.model.Frame;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class FrameTest {
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void validBuildObject() {
	
		StringBuilder messageTestPerson = null;
	    // Test empty construct
		messageTestPerson = new StringBuilder();
		Frame person = new Frame();
		messageTestPerson.append("PersonComplete ").append(person.getPlayerName()).append(" ").append(person.getId());
		LOG.debug(messageTestPerson.toString());
		
	    // Test construct
		messageTestPerson = new StringBuilder();
		Frame personComplete = new Frame(System.currentTimeMillis(), "test", new ArrayList<Score>());
		messageTestPerson.append("PersonComplete ").append(personComplete.getPlayerName()).append(" ").append(personComplete.getId());
		LOG.debug(messageTestPerson.toString());
		
		
	}
	
}
