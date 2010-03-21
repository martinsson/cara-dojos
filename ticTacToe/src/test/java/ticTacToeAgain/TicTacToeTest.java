package ticTacToeAgain;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

public class TicTacToeTest {
	/*
	a game is over when all fields are taken
	a game is over when all fields in a column are taken by a player
	a game is over when all fields in a row are taken by a player
	a game is over when all fields in a diagonal are taken by a player
	a player can take a field if not already taken
	players take turns taking fields until the game is over
	 */
	private GameRules rules = new GameRules();
	
	@Test
  public void gameIsOverWhenAllFieldsAreTaken() throws Exception {
		boolean allFieldsAreTaken = true;
		boolean gameIsOver = allFieldsAreTaken;
		assertThat(gameIsOver, is(true));
  }
	
	@Test
  public void gameIsOverWhenSecondColumnIsTaken() throws Exception {
	  List<Integer> fieldsTakenByPlayer = asList(2, 5, 8);
	  assertThat(gameIsOver(fieldsTakenByPlayer), is(true));
  }

	@Test
  public void gameIsOverWhenThirdColumnIsTaken() throws Exception {
	  List<Integer> fieldsTakenByPlayer = asList(3, 6, 9);
	  assertThat(gameIsOver(fieldsTakenByPlayer), is(true));
  }
	
	@Test
  public void gameIsOverWhenOneColumnAndMoreIsTaken() throws Exception {
		List<Integer> fieldsTakenByPlayer = asList(1, 2, 4, 5, 8);
	  assertThat(gameIsOver(fieldsTakenByPlayer), is(true));
  }

	@Test
  public void gameIsOverWhenARowIsTaken() throws Exception {
	  assertThat(gameIsOver(asList(1, 2, 3)), is(true));
	  assertThat(gameIsOver(asList(4, 5, 6)), is(true));
	  assertThat(gameIsOver(asList(7, 8, 9)), is(true));
  }

	@Test
  public void gameIsOverWhenADiagonalIsTaken() throws Exception {
	  assertThat(gameIsOver(asList(1, 5, 9)), is(true));
	  assertThat(gameIsOver(asList(1, 3, 5, 7, 8)), is(true));
  }

	@Test
  public void aPlayerCanTakeAFieldIfNotAlreadyTaken() throws Exception {
	  List<Integer> takenByWhite = new ArrayList<Integer>(asList(1, 2, 5));
	  boolean canTakeField = canTakeField(4, takenByWhite);
	  assertTrue(canTakeField);
  }

	@Test
  public void aPlayerCanNOTTakeAFieldHeAlreadyTook() throws Exception {
	  List<Integer> takenByWhite = new ArrayList<Integer>(asList(1, 2, 5));
	  boolean canTakeField = canTakeField(5, takenByWhite);
	  assertFalse(canTakeField);
  }

	@Test
  public void aPlayerCannotTakeAFieldTakenByAnotherPlayer() throws Exception {
	  List<Integer> takenByWhite = new ArrayList<Integer>(asList(1, 2, 5));
		List<Integer> takenByBlack = new ArrayList<Integer>(asList(3, 4, 6));
		int fieldToTake = 6;
	  boolean canTakeField = canTakeField(fieldToTake, takenByWhite, takenByBlack);
	  assertFalse(canTakeField);
  }

	private boolean canTakeField(int fieldToTake, List<Integer> takenByWhite, List<Integer> takenByBlack) {
	  return !(takenByWhite.contains(fieldToTake) || takenByBlack.contains(fieldToTake));
  }
	
	private boolean canTakeField(int fieldToTake, List<Integer> takenByWhite) {
		return !takenByWhite.contains(fieldToTake);
	}
	private Boolean gameIsOver(List<Integer> fieldsTakenByPlayer) {
		return rules.gameIsOver(fieldsTakenByPlayer);
	}

	private static final Matcher<Iterable<Integer>> ANY_COLUMN = anyOf(hasItems(1, 4, 7), hasItems(2, 5, 8), hasItems(3, 6, 9));
	private static final Matcher<Iterable<Integer>> ANY_ROW = anyOf(hasItems(1, 2, 3), hasItems(4, 5, 6), hasItems(7, 8, 9));
	private static final Matcher<Iterable<Integer>> ANY_DIAGONAL = anyOf(hasItems(1, 5, 9), hasItems(3, 5, 7));
	class GameRules {

		Boolean gameIsOver(List<Integer> fieldsTakenByPlayer) {
			return anyOf(ANY_COLUMN, ANY_ROW, ANY_DIAGONAL).matches(fieldsTakenByPlayer);
		}
		
	}
}
