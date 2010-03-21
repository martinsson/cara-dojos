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
	boolean gameIsOver(List<Integer> fieldsTakenByWhite, List<Integer> fieldsTakenByBlack) {
		Player white = new Player(fieldsTakenByWhite);
		Player black = new Player(fieldsTakenByBlack);
		return allFieldsAreTaken(fieldsTakenByWhite, fieldsTakenByWhite) 
		|| white.hasWon(this)
		|| black.hasWon(this);
	}
	private boolean allFieldsAreTaken(List<Integer> fieldsTakenByBlack, List<Integer> fieldsTakenByWhite) {
		return fieldsTakenByBlack.size() 
		+ fieldsTakenByWhite.size()
		>= FIELDS_IN_GAME;
	}
	private boolean canTakeField(List<Integer> fieldsTakenByWhite, List<Integer> fieldsTakenByBlack, int fieldToTake) {
		return ! (fieldsTakenByWhite.contains(fieldToTake)
				||fieldsTakenByBlack.contains(fieldToTake));
	}
	boolean play(List<Integer> fieldsTakenByPlayer, List<Integer> fieldsOfOtherPlayer, int field) {
		if (canTakeField(fieldsTakenByPlayer, fieldsOfOtherPlayer, field)) {
			return fieldsTakenByPlayer.add(field);
		}
		return false;
	}
}