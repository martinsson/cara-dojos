package ticTacToe;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class TicTacToeTest {
private Game game = new Game(null, null);

/*
a game is over when all fields are taken
a game is over when all fields in a column are taken by a player
a game is over when all fields in a row are taken by a player
a game is over when all fields in a diagonal are taken by a player
a player can take a field if not already taken
players take turns taking fields until the game is over
 */
	@Test public void gameIsOverWhenAllFieldsAreTaken() throws Exception {
		List<Integer> fieldsTakenByWhite = list(1, 2, 5, 6, 7);
		List<Integer> fieldsTakenByBlack = list(3, 4, 8, 9);
		boolean gameIsOver = isGameOver(fieldsTakenByWhite,
				fieldsTakenByBlack);
		assertTrue("game should have been over when all fields are taken", gameIsOver);
	}

	@Test
	public void gameIsOverWhenEitherOnePlayerHasWonOrAllFieldsAreTaken() throws Exception {
		List<Integer> fieldsTakenByWhite = list(1, 2, 5, 6, 7);
		List<Integer> fieldsTakenByBlack = list(3, 4, 8, 9);
		assertFalse("white has no row column or diagonal", onePlayerHasWon(fieldsTakenByWhite));
		assertFalse("black has no row column or diagonal", onePlayerHasWon(fieldsTakenByBlack));
		
		assertTrue("game is over because all fields are taken", isGameOver(fieldsTakenByBlack, fieldsTakenByWhite));
		
	}
	
	@Test public void gameIsOverWhenAFullColumnIsTakenByAPlayer() throws Exception {
		assertTrue("first column is taken", isGameOver(1, 4, 7));
		assertTrue("Second column is taken", isGameOver(2, 5, 8));
		assertTrue("third column is taken", isGameOver(3, 6, 7, 9));
	}

	@Test public void gameIsOverWhenARowIsTakenByAPlayer() throws Exception {
		assertTrue("first row is taken", isGameOver(1, 2, 3));
		assertTrue("Second row is taken", isGameOver(1, 4, 5, 6, 7));
		assertTrue("third row is taken", isGameOver(1, 5, 7, 8, 9));
	}
	
	@Test public void gameIsNotOverIfNoFullRowNorColumnIsTakenByAPlayer() throws Exception {
		assertFalse("No row nor column is taken", isGameOver(1, 4, 5, 8));
	}

	@Test public void gameIsOverWhenADiagonalIsTakenByAPlayer() throws Exception {
		assertTrue("the diagonal '\' is taken", isGameOver(1, 5, 9));
		assertTrue("the diagonal '/' is taken", isGameOver(3, 5, 6, 7));
	}
	@Test public void aPlayerCanTakeAFieldIfNotAlreadyTaken() throws Exception {
		List<Integer> fieldsTakenByWhite = list(2, 3, 4);
		List<Integer> fieldsTakenByBlack = list(5, 6, 7);
		boolean couldTakeField = play(fieldsTakenByWhite, fieldsTakenByBlack, 1);
		assertTrue("should have been allowed to take field",
				couldTakeField);
	}

	@Test public void aPlayerCanNOTTakeAFieldIfAlreadyTaken() throws Exception {
		List<Integer> fieldsTakenByWhite = list(2, 3, 4);
		List<Integer> fieldsTakenByBlack = list(1);
		boolean couldTakeField = play(fieldsTakenByWhite, fieldsTakenByBlack, 1);
		assertFalse("should not have been allowed to take field",
				couldTakeField);
	}

	@Test
	public void playersTakeTurnsUntilGameIsOver() throws Exception {
		List<Integer> white = list();
		List<Integer> black = list();
		play(white, black, 9);
		play(black, white, 8);
		play(white, black, 6);
		play(black, white, 7);
		assertFalse("game should not be over", isGameOver(white, black));
		play(white, black, 3);
		assertTrue("game should not be over", isGameOver(white, black));
	}

	private boolean play(List<Integer> white, List<Integer> black, int fieldToTake) {
		Player whitePlayer = new Player(white);
		Player blackPlayer = new Player(black);
		Game currentGame = new Game(blackPlayer, whitePlayer);
	  return whitePlayer.takeField(currentGame, fieldToTake);
  }
	
	private boolean isGameOver(List<Integer> fieldsTakenByBlack,
			List<Integer> fieldsTakenByWhite) {
		return game.gameIsOver(new Player(fieldsTakenByWhite), new Player(fieldsTakenByBlack));
	}
	
	private boolean onePlayerHasWon(List<Integer> fieldsTakenByPlayer) {
		return new Player(fieldsTakenByPlayer).hasWon();
	}
	
	private List<Integer> list(Integer...fields) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (Integer field : fields) {
			ret.add(field);
		}
		return ret;
	}

	private boolean isGameOver(Integer...fieldsTakenByAPlayer) {
		return isGameOver(asList(fieldsTakenByAPlayer), Collections.EMPTY_LIST);
	}
}
