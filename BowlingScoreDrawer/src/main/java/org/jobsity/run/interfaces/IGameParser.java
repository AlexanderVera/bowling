package org.jobsity.run.interfaces;

import java.io.IOException;

import org.jobsity.run.exceptions.BuildException;
import org.jobsity.run.model.GameBoard;

public interface IGameParser {
	
	public GameBoard buildFrameFromPlayers() throws BuildException, IOException;
}
