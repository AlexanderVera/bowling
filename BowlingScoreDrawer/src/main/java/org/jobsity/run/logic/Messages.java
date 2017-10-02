package org.jobsity.run.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IMessages;

/**
* Messages
*
*
* @author alexander.vera
* @since 30/10/2017
*
*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		add validateBoard method
*/
public class Messages implements IMessages{
	
	private InputStream messagesFile;
	private Properties messageProperties;
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	public Messages(){
		messagesFile = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("message.properties");
		messageProperties = new Properties();
		try{
			messageProperties.load(messagesFile);
		}
		catch(IOException exc){
			LOG.error(exc.getMessage());
		}
	}
	
	/**
     * Method to return a text message used in the app
     *
     * @return A String value with the message
     * @param key: String key
     */
	public String getMessage(String key) {
		return messageProperties.getProperty(key);
	}

}
