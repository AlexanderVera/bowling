package org.jobsity.test.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.jobsity.run.logic.Main;
import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UtilitiesTest extends TestCase{
	
	/**
	 * Static method to manage the log
	 **/
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	@Test
	public void validNumberTest(){
		assertTrue(Utilities.validNumber("3"));
		assertFalse(Utilities.validNumber("a"));
		assertFalse(Utilities.validNumber(""));
		assertTrue(Utilities.validNumber("2.5"));
		assertFalse(Utilities.validNumber(" "));
	}
	
	@Test
	public void parseValidateInteger(){
		assertEquals(3, Utilities.parseValidateInteger("3"));
		assertNotSame(3, Utilities.parseValidateInteger("3a"));
		assertEquals(0, Utilities.parseValidateInteger("F"));
	}
	
	@Test
	public void generatePlayerFileTest(){
		Utilities.generatePlayerFile(2);
	}
	
	@Test
	public void split(){
		String str = "Joe 10";
		LOG.debug(Utilities.split(str, "[\\s\\t]+").get(1));
	    
	}

}