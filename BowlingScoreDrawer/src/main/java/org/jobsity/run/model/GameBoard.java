package org.jobsity.run.model;

import java.util.List;
/**
 * List of lines of the game
 * 
 * @author alexander.vera
 * @since 2017/10/06
 */
public class GameBoard {
	
	public GameBoard(){
		
	}
	
	public GameBoard(List<GameLine> listOfGameLines){
		this.listOfGameLines = listOfGameLines;
	}
	
	private List<GameLine> listOfGameLines;
	
	public List<GameLine> getListOfGameLines() {
		return listOfGameLines;
	}

	public void setListOfGameLines(List<GameLine> listOfGameLines) {
		this.listOfGameLines = listOfGameLines;
	}
}
