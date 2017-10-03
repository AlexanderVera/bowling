package org.jobsity.run.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IMessages;

/**
 * Messages Class to manage the app messages
 * 
 * @author alexander.vera
 * @since 30/09/2017
 *
 */
public class Messages implements IMessages {

	/**
	 * Static class for the LOGS
	 **/
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	/**
	 * Load properties file
	 **/	
	private transient final InputStream messagesFile = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("message.properties");

	/**
	 * Properties parameter
	 * 
	 */
	private Properties properties;
	
	/**
	 * Empty constructor
	 * 
	 */
	public Messages(){
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
	public final Properties loadMessageFile() {
		final Properties messageProperties = new Properties();
		try {
			messageProperties.load(messagesFile);
		} catch (IOException exc) {
			LOG.error(exc.getMessage());
		}
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
/**
 * Changes history -------------------------------------------------- Author
 * Date Change ----------------- -------------- ------------------
 * alexander.vera 01/10/2017 add validateBoard method
 */