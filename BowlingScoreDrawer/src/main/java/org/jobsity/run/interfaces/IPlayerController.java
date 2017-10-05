package org.jobsity.run.interfaces;

import java.util.List;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.model.Frame;
import org.jobsity.run.model.PlayerScore;

/**
* IPlayerController
*
* @author alexander.vera
* @since 01/10/2017
*/
public interface IPlayerController {
	/**
     * Method that take a list of Shoot lines with a name and pins for each player
     *
     * @return A String value with the message
     * @param plainPlayerScores
     */
	List<Frame> buildPlayerScore(List<PlayerScore> plainPlayerScores) throws BuildException;
}

/**
 * Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		improve the buildPlayerScore method
* alexander.vera	02/10/2017 		create validation for a incorrect scores
*/