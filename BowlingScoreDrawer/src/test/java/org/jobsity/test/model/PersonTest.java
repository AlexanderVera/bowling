package org.jobsity.test.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jobsity.run.model.GameLine;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class PersonTest {
	
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void validBuildObject() {
	
		StringBuilder messageTestPerson = null;
	    // Test empty construct
		messageTestPerson = new StringBuilder();
		GameLine person = new GameLine();
		messageTestPerson.append("PersonComplete ").append(person.getPlayerName()).append(" ");
		LOG.debug(messageTestPerson.toString());
		
	    // Test construct
		messageTestPerson = new StringBuilder();
		GameLine personComplete = new GameLine("test", new ArrayList<Score>());
		messageTestPerson.append("PersonComplete ").append(personComplete.getPlayerName()).append(" ");
		LOG.debug(messageTestPerson.toString());
		
		
	}

}
