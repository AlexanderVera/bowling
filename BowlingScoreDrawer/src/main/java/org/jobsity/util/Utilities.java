package org.jobsity.util;

import java.util.Calendar;

import org.apache.log4j.Logger;

public class Utilities {

	private final static Logger LOG = Logger.getLogger(Utilities.class.getName());
	private static StringBuilder infoMessage = new StringBuilder();


	public static boolean validNumber(String number) {
		infoMessage = new StringBuilder();
		initLog(number);
		if (number != null) {
			try {

				Double.parseDouble(number);
				appendLogMessage(" is a great number ");
				//LOG.debug(getInfoMessage().toString());

				return true;
			} catch (NumberFormatException exc) {
				// Replace to logger
				appendLogMessage(" is not a number");
				LOG.error(getInfoMessage().toString());
			}
			return false;
		} else {
			appendLogMessage(" is not a number");
			LOG.error(getInfoMessage().toString());
			return false;
		}

	}

	public static int parseValidateInteger(String number){
		if (validNumber(number)) {
			Double outDouble = Double.parseDouble(number);
			return outDouble.intValue();
		} else {
			return 0;
		}
	}
	
	public static int resultAsNumber(String number) {
		if (validNumber(number)) {
			return Integer.parseInt(number);
		} else {
			return 0;
		}
	}

	private static void appendLogMessage(String message) {
		infoMessage =getInfoMessage().append(message);
	}

	private static void initLog(String number) {
		Calendar timeLog = Calendar.getInstance();
		appendLogMessage(" ");
		appendLogMessage(timeLog.getTime().toString());
		appendLogMessage(": ");
		appendLogMessage(number);
	}



	public static StringBuilder getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(StringBuilder newMessage) {
		infoMessage = newMessage;
	}
}