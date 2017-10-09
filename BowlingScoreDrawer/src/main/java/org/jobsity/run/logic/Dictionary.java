package org.jobsity.run.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jobsity.run.interfaces.Messages;

/**
 * Dictionary Class. Manage the app messages.
 * 
 * @author alexander.vera
 * @since 30/09/2017
 *
 */
public class Dictionary implements Messages {

	/**
	 * Load properties file
	 **/	
	private final InputStream messagesFile = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("message.properties");

	/**
	 * Properties parameter
	 * 
	 */
	private Properties properties;
	
	/**
	 * Empty constructor
	 * @throws IOException 
	 * 
	 */
	public Dictionary() throws IOException{
		this.properties=loadMessageFile();
	}
	
	/**
	 * Method to return a text message used in the app
	 *
	 * @return A String value with the message
	 * @param key:
	 *            String key
	 */
	public String getMessage(final String key) {
		return properties.getProperty(key);
	}

	/**Method to get a properties class from a properties file
	 **/
	public final Properties loadMessageFile() throws IOException{
		final Properties messageProperties = new Properties();
		messageProperties.load(messagesFile);
		return messageProperties;
	}

	/**
	 *Getter from properties method
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 *Setter from properties method
	 */
	public void setProperties(final Properties properties) {
		this.properties = properties;
	}

}
/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
 * 
 */