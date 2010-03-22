package ticTacToe;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.hamcrest.Matcher;

public class Player {

	private static final Matcher<Iterable<Integer>> DIAGONAL = anyOf(hasItems(3, 5, 7), hasItems(1, 5, 9));
	private static final Matcher<Iterable<Integer>> ROW = anyOf(hasItems(1, 2, 3), hasItems(4, 5, 6), hasItems(7, 8, 9));
	private static final Matcher<Iterable<Integer>> COLUMN = anyOf(hasItems(1, 4, 7), hasItems(2, 5, 8), hasItems(3, 6, 9));
	public Player(List<Integer> fieldsTaken) {
		fields = fieldsTaken;
  }

	private final List<Integer> fields;

	boolean hasWon() {
  	return anyOf(COLUMN, ROW, DIAGONAL).matches(fields);
  }

	boolean takeField(Game game, int field) {
  	if (! game.fieldAlreadyTaken(field)) {
  		return fields.add(field);
  	}
  	return false;
  }

	boolean alreadyHas(int field) {
	  return fields.contains(field);
  }

	public int numberOfFields() {
	  return fields.size();
  }
}
