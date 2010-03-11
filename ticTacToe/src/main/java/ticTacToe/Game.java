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
	private static final Matcher<Iterable<Integer>> DIAGONAL = anyOf(hasItems(3, 5, 7), hasItems(1, 5, 9));
	private static final Matcher<Iterable<Integer>> ROW = anyOf(hasItems(1, 2, 3), hasItems(4, 5, 6), hasItems(7, 8, 9));
	private static final Matcher<Iterable<Integer>> COLUMN = anyOf(hasItems(1, 4, 7), hasItems(2, 5, 8), hasItems(3, 6, 9));
	private static final int FIELDS_IN_GAME = 9;
	private Matcher<Iterable<Integer>> gameIsOver() {
		return anyOf(COLUMN, ROW, DIAGONAL);
	}
	boolean onePlayerHasWon(List<Integer> fieldsTakenByPlayer) {
		return gameIsOver().matches(fieldsTakenByPlayer);
	}
	boolean gameIsOver(List<Integer> fieldsTakenByWhite, List<Integer> fieldsTakenByBlack) {
		return allFieldsAreTaken(fieldsTakenByWhite, fieldsTakenByWhite) 
		|| onePlayerHasWon(fieldsTakenByWhite)
		|| onePlayerHasWon(fieldsTakenByBlack);
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