package org.jobsity.run.exceptions;

/**
* BuildException
* Custom exception
*
* @author alexander.vera
* @since 02/10/2017
*/
public class BuildException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor for BuildException
	 * */
	public BuildException() {
		super();
	}

	/**
	 * Constructor for BuildException
	 * @param message: message String to LOG 
	 * */
	public BuildException(final String message) {
		super(message);
	}

}
/*
* 
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
*/