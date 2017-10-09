package org.jobsity.run.interfaces;

import java.util.List;

import org.jobsity.run.model.GameLine;

/**
* BowlingScore
*
* @author alexander.vera
* @since 01/10/2017
*/
public interface BowlingScore {
	/**
	 * 
	 * @param listOfPLayers
	 * @return List<GameLine>
	 */
	List<GameLine> calculateScore(List<GameLine> listOfPLayers); 
}

/**
 * Changes history
* -------------------------------------------------- 
* Author             Date          Change 
* ----------------- -------------- ------------------
* alexander.vera	01/10/2017 		improve the buildPlayerPin method
* alexander.vera	02/10/2017 		create validation for a incorrect scores
*/