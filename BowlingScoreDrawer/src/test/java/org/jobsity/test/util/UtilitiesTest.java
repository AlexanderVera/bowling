package org.jobsity.test.util;

import java.io.IOException;

import org.jobsity.util.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class UtilitiesTest extends TestCase{
	private final String dummieScore = "Joe 10";
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
	public void splitNull(){
		boolean thrown = false;
		try {
			Utilities.split(dummieScore, "[\\s\\t]");
		} catch (NullPointerException | IOException e) {
			thrown = true;
		}
		assertFalse(thrown);
	}
	
	@Test
	public void splitFull(){
		boolean thrown = false;
		try {
			Utilities.split(dummieScore, null);
		} catch (NullPointerException | IOException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void getFullStringFromClassPathTest() throws IOException{
		try {
			StringBuilder fileString = new StringBuilder();
			fileString=Utilities.getFullStringFromClassPath("test-print-happy-path");
			assertNotNull(fileString);
		}
		catch(IOException exc) {
			throw new IOException();
		}
	}

}