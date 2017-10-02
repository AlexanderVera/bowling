package org.jobsity.run.logic;

import java.util.List;

import org.jobsity.run.interfaces.IBoardDrawer;
import org.jobsity.run.interfaces.IMessages;
import org.jobsity.run.model.Player;
import org.jobsity.run.model.Score;

/**
 * 
 * @author alexnder.vera
 * @since 2017/10/01
 * 
 * HISTORY CHANGES
 * 
 */
public class BoardDrawer implements IBoardDrawer {

	private static final int NUMBER_OF_ROUNDS = 10;
	private static final int NUMBER_OF_PINS = 10;
	private List<Player> players;
	private IMessages messages;

	public BoardDrawer(List<Player> players) {
		this.players  = players;
		setMessages(new Messages());
	}

	public void printFrame() {


		if (getPlayers() != null) {
			StringBuilder scoreByPlayer = new StringBuilder(messages.getMessage("src.main.labels.frame"));
			scoreByPlayer.append("		");
			for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
				scoreByPlayer.append((i + 1) + "	");
			}
			scoreByPlayer.append("\n");
			System.out.print(scoreByPlayer.toString());

			for (Player tmpPlayer : getPlayers()) {
				scoreByPlayer = new StringBuilder(tmpPlayer.getName());
				scoreByPlayer.append("\n");
				System.out.print(scoreByPlayer);

				scoreByPlayer = new StringBuilder(messages.getMessage("src.main.labels.pingfall"));
				scoreByPlayer.append(" ");
				for (Score score : tmpPlayer.getScore()) {
					scoreByPlayer.append("	");
					if (!score.isFlagFinal()) {
						if (score.isStrike()) {
							scoreByPlayer.append(" ");
							scoreByPlayer.append("X");
						} else {
							scoreByPlayer.append(score.getShoots()[0]);
							scoreByPlayer.append(" ");
							if (score.isSpare()) {
								scoreByPlayer.append("/");
							} else {
								scoreByPlayer.append(score.getShoots()[1]);
							}
						}
					}

					if (score.getFlagFinal()) {
						if (score.getShoots()[0] >= NUMBER_OF_PINS) {
							scoreByPlayer.append("X");
						} else {
							scoreByPlayer.append(score.getShoots()[0]);
						}
						scoreByPlayer.append(" ");
						if (score.getShoots()[1] >= NUMBER_OF_PINS) {
							scoreByPlayer.append("X");
						} else {
							scoreByPlayer.append(score.getShoots()[1]);
						}

						scoreByPlayer.append(" ");

						if (score.getShoots()[2] >= NUMBER_OF_PINS) {
							scoreByPlayer.append("X");
						} else {
							scoreByPlayer.append(score.getShoots()[2]);
						}

					}
				}

				scoreByPlayer.append("\n");
				System.out.print(scoreByPlayer.toString());

				scoreByPlayer = new StringBuilder(messages.getMessage("src.main.labels.score"));
				scoreByPlayer.append("    ");
				for (Score score : tmpPlayer.getScore()) {
					scoreByPlayer.append(" 	");
					scoreByPlayer.append(score.getTotal());
					scoreByPlayer.append(" ");
				}
				scoreByPlayer.append("\n\n");
				System.out.print(scoreByPlayer.toString());

			}
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public IMessages getMessages() {
		return messages;
	}

	public void setMessages(IMessages messages) {
		this.messages = messages;
	}

	
}
