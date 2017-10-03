package org.jobsity.test.logic;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.logic.Messages;
import org.junit.Test;

public class MessageTest {
	
	private static final Logger LOG = Logger.getLogger(FileManagerTest.class.getName());
	
	@Test
	public void getPropertieTest(){
		IMessages message = new Messages();
		String testMessage = message.getMessage("src.main.labels.much.shoots");
		LOG.debug(testMessage);
		
	}
}
