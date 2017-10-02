package org.jobsity.test.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jobsity.run.model.Person;
import org.jobsity.run.model.Player;
import org.jobsity.run.model.Score;
import org.jobsity.util.Utilities;
import org.junit.Test;

public class PersonTest {

	/**
	 * Create the test case
	 *
	 * @param 
	 */ 
	
	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	@Test
	public void validBuildObject() {
	
		StringBuilder messageTestPerson = null;
	    // Test empty construct
		messageTestPerson = new StringBuilder();
		Person person = new Player();
		messageTestPerson.append("PersonComplete ").append(person.getName()).append(" ").append(person.getId());
		LOG.debug(messageTestPerson.toString());
		
	    // Test construct
		messageTestPerson = new StringBuilder();
		Person personComplete = new Player(System.currentTimeMillis(), "test", new ArrayList<Score>());
		messageTestPerson.append("PersonComplete ").append(personComplete.getName()).append(" ").append(personComplete.getId());
		LOG.debug(messageTestPerson.toString());
		
		
	}

}
