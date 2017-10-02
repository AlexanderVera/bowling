package org.jobsity.run.interfaces;

import java.util.List;

import org.jobsity.run.model.Player;

/**
 * 
 * @author alexnder.vera
 * @since 2017/10/01
 * 
 * HISTORY CHANGES
 * 
 */
public interface IScoreBoardController{
	public int numberOfPlayers(List<String> listPlainScorePlayers);
	public List<Player> buildFrame();
	public List<Player> addPlayer(Player player, List<Player> listOfPLayers);
}
