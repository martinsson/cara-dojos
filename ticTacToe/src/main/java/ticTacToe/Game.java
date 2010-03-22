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
	boolean gameIsOver(Player white, Player black) {
		return allFieldsAreTaken(white, black) 
		|| white.hasWon()
		|| black.hasWon();
	}
	private boolean allFieldsAreTaken(Player white, Player black) {
		return black.fields.size() 
		+ white.fields.size()
		>= FIELDS_IN_GAME;
	}
}