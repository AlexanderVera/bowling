package org.jobsity.run.interfaces;

import java.util.List;

import org.jobsity.run.model.PlayerScore;

public interface IPlayerController {
	public List<List<PlayerScore>> buildFrameOfPlayer(List<String> plainPlayerScores);
	public List<PlayerScore> buildPlayerScore(List<String> plainPlayerScores);
}
