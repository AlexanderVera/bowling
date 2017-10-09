package org.jobsity.run.interfaces;

import java.io.IOException;
import java.util.List;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.model.PlayerPins;

/**
* FileManager
* 
* @author alexander.vera
* @since 30/09/2017
*/
public interface FileManager {
	
	/**
     * Method that take the input file and make a list of file.
     * Lines with the player and his pinfalls
     *
     * @return A List<PlayerPins>, each Player/Score from file
     */
	List<PlayerPins> buildListPlayersFromFile() throws IOException, BuildException;
	
}

/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		add validateBoard method
*/