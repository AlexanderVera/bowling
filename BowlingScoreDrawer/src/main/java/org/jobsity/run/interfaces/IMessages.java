package org.jobsity.run.interfaces;

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

public interface IMessages {
	
	/**
     * Method to return a text message used in the app
     *
     * @return A String value with the message
     * @param key: String key
     */
	public String getMessage(String key);
}
