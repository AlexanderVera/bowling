package org.jobsity.run.logic;

import java.util.List;

import org.jobsity.run.model.Player;

public interface IScoreBoardController{

	public List<Player> addPlayer(Player player);
    //public void calcNumberOfPlayers();
	public int numberOfPlayers(List<String> playersPins);
	public void buildFrame(List<String> plainScore);
	public void printFrame();
}
