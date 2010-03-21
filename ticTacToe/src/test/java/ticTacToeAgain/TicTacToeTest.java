package ticTacToeAgain;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;

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
	
	private Boolean gameIsOver(List<Integer> fieldsTakenByPlayer) {
	  return rules.gameIsOver(fieldsTakenByPlayer);
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
	
	
	class GameRules {

		Boolean gameIsOver(List<Integer> fieldsTakenByPlayer) {
			return aColumnIsTaken(fieldsTakenByPlayer)  
			|| aRowIsTaken(fieldsTakenByPlayer)
			|| aDiagonalIsTaken(fieldsTakenByPlayer);
		}
		
		private boolean aColumnIsTaken(List<Integer> fieldsTakenByPlayer) {
			return anyOf(hasItems(1, 4, 7), hasItems(2, 5, 8), hasItems(3, 6, 9)).matches(fieldsTakenByPlayer);
		}
		
		private boolean aDiagonalIsTaken(List<Integer> fieldsTakenByPlayer) {
			return anyOf(hasItems(1, 5, 9), hasItems(3, 5, 7)).matches(fieldsTakenByPlayer);
		}
		
		private boolean aRowIsTaken(List<Integer> fieldsTakenByPlayer) {
			return anyOf(hasItems(1, 2, 3), hasItems(4, 5, 6), hasItems(7, 8, 9)).matches(fieldsTakenByPlayer);
		}
		
	}
}
