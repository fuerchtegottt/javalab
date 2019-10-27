package de.g3ll3rt.jpachisi;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private FieldMetrics metrics;
	private int roundLimit;
	private ArrayList<Player> playerList;
	private boolean gameFinished;
	private GameStats gameStats;
	private PlayingField playingField;

	public Game() {
		roundLimit = 200;
		playerList = new ArrayList<>();
		metrics = new FieldMetrics();
		playingField = new PlayingField(new FieldMetrics());
	}

	public void addPlayer(Player player) {
		if (playerList.size() < 4) {
			playerList.add(player);
		}
	}

	public GameStats getStats() {
		return this.gameStats;
	}

	public void doTurn(Player player) {
      if (gameStats.getRoundCnt() > roundLimit){
    	  player.declarePokeVictory();
    	  gameStats.addAction(player.getName() + " canceled match after " + roundLimit + " rounds.");
      } else {
    	  player.doTurn(playingField);
      }
	}

	private void initGame() {

		gameStats = new GameStats();
		for (Player player : playerList) {
			player.initPlayer();
		}

	}

	public void checkWinConditions() {
		for (Player player : playerList) {
			if (player.hasWon()) {
				gameStats.setWinningFraction(player.getFraction());
				gameFinished = true;
			}
		}
	}

	public void startGame() {
		initGame();
		playingField.init();
		gameFinished = false;
		do {
			gameStats.addRound();
			for (Player nextPlayer : playerList) {
				if (gameFinished == false) {
					doTurn(nextPlayer);
					checkWinConditions();
				}
			}
		} while (gameFinished == false);
		gameStats.addAction("winning fraction: " + gameStats.getWinner());

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.addPlayer(new Player("Spieler 1", Fraction.BLUE));
		game.addPlayer(new Player("Spieler 2", Fraction.RED));
		game.addPlayer(new Player("Spieler 3", Fraction.GREEN));
		game.addPlayer(new Player("Spieler 4", Fraction.YELLOW));
		game.startGame();
		System.out.println(game.getStats());
	}
}
