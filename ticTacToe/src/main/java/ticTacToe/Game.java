/**
 * 
 */
package ticTacToe;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

class Game {
	private static final int FIELDS_IN_GAME = 9;
	private final Player black;
	private final Player white;

	public Game(Player white, Player black) {
		this.white = white;
		this.black = black;
  }
	boolean gameIsOver() {
		return allFieldsAreTaken() || white.hasWon() || black.hasWon();
	}
	private boolean allFieldsAreTaken() {
		return black.numberOfFields() + white.numberOfFields() >= FIELDS_IN_GAME;
	}
	boolean fieldAlreadyTaken(int field) {
    return white.alreadyHas(field) || black.alreadyHas(field);
  }
}