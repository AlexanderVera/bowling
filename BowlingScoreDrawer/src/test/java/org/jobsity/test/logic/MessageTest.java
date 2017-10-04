package org.jobsity.test.logic;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.logic.Messages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MessageTest extends TestCase{
	
	private static final Logger LOG = Logger.getLogger(FileManagerTest.class.getName());
	
	//Test the message class in 2 scenarios: validKey and invalid Key
	@Test
	public void getPropertieTest(){
		IMessages message = new Messages();
		String testMessage = message.getMessage("src.main.labels.much.shoots");
		String noMessage = message.getMessage("this.is.not.a.key");
		assertNotNull(testMessage);
		assertNull(noMessage);
		StringBuilder logMessage = new StringBuilder("Valid key{");
		logMessage.append(testMessage).append("} {").append(noMessage).append("}");
		LOG.debug(logMessage);	
	}
}
