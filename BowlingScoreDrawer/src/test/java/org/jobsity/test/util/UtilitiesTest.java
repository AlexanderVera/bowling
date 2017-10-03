package org.jobsity.test.util;

import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class UtilitiesTest extends TestCase{
	
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
}
