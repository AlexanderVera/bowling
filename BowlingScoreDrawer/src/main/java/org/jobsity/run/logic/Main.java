package org.jobsity.run.logic;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.jobsity.run.interfaces.IBoardDrawer;
import org.jobsity.run.interfaces.IFileManager;
import org.jobsity.run.interfaces.IPlayerController;
import org.jobsity.run.model.Player;



/**
 * @author alexander.vera
 * @since 2017/09/30
 */


public class Main {
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	public static void main(String[] args) {

		List<String> playerPinfalls = null;
		if(args.length>0){
			File fileScore = new File(args[0]);
			IFileManager fileManager = new FileManager(fileScore);
			playerPinfalls = fileManager.buildListPlayerFromFile();
		
			IPlayerController playerController = new PlayerController(playerPinfalls);
			List<Player> players = playerController.buildPlayerScore(playerPinfalls);
			
			IBoardDrawer boardDrawer = null;
			boardDrawer = new BoardDrawer(players);
			boardDrawer.printFrame();
		}
		else{
			LOG.info("Insert file route");
			File fileScore = new File("D:\\alexander.vera\\documentos\\documentacion\\frame.txt");
			IFileManager fileManager = new FileManager(fileScore);
			playerPinfalls = fileManager.buildListPlayerFromFile();
		
			IPlayerController playerController = new PlayerController(playerPinfalls);
			List<Player> players = playerController.buildPlayerScore(playerPinfalls);
			
			IBoardDrawer boardDrawer = null;
			boardDrawer = new BoardDrawer(players);
			boardDrawer.printFrame();
			

		}
	}

}
