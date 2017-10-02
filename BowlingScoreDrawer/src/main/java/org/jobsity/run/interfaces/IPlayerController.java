package org.jobsity.run.interfaces;

import java.util.List;

import org.jobsity.run.model.Player;

public interface IPlayerController {
	public List<Player> buildPlayerScore(List<String> plainPlayerScores);
}
