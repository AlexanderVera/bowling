package org.jobsity.run.interfaces;

import java.io.IOException;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.model.GameBoard;

/**
 * GameParser Class. Covert a List of Player score into a valid Game lines
 * 
 * @author alexander.vera
 * @since 06/10/2017
 *
 */
public interface IGameParser {
	
	public GameBoard buildBoardFromPlayers() throws BuildException, IOException;
}

/*
* Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
*/