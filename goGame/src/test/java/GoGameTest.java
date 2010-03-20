import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Test;

public class GoGameTest {
	int nbLibertiesByDefault = 4;
	private Stones stones = new Stones();
	
	// a stone can
	@Test
	public void aStoneCanBeTakenIfItHasIOneLiberty() {
		int nbCornersTaken = 3;
		boolean canBeTaken = canBeTaken(nbCornersTaken);
		assertThat(canBeTaken, equalTo(true));
	}

	public boolean canBeTaken(int nbCornersTaken) {
		int nbLiberties = liberties(nbCornersTaken);
		return (nbLiberties == 1);
	}

	private int liberties(int nbCornersTaken) {
		int nbLiberties = nbLibertiesByDefault - nbCornersTaken;
		return nbLiberties;
	}

	@Test
	public void aStoneCannotBeTakenIfItHasITwoLiberties() {
		int nbCornersTaken = 2;
		assertThat(canBeTaken(nbCornersTaken), equalTo(false));
	}

	@Test
	public void theNumberLibertiesOfAGroupOfTwoIsTheNumberOfFieldsSurrounding() {
		List<String> fieldsTaken = asList("22", "32");
		int totalLiberties = stones.libertiesFor(fieldsTaken);
		assertThat(totalLiberties, equalTo(6));
	}

	@Test
	public void theNumberLibertiesOfAGroupOfThreeAlignedStones() {
		List<String> fieldsTaken = asList("22", "32", "42");
		int totalLiberties = stones.libertiesFor(fieldsTaken);
		assertThat(totalLiberties, equalTo(8));
	}

	@Test @Ignore
	public void aGroupCanBeTakenIfItHasOnlyOneLIberty() {

	}
	
	class Stones {

		private Set<String> neighboringFields(List<String> fieldsTaken) {
    	Set<String> corners = new TreeSet<String>();
    	for (String field : fieldsTaken) {
    		corners.addAll(new Field(field).neighboringFields());
    	}
    	corners.removeAll(fieldsTaken);
    	return corners;
    }

		int libertiesFor(List<String> fieldsTaken) {
    	return neighboringFields(fieldsTaken).size();
    }
	}
	
	/*
	 * 11 12 13 21 22 23 31 32 33
	 */
	@Test
	public void neighboringFieldsReturnsTheFourSurroundingFields() throws Exception {
		assertThat(new Field("22").neighboringFields(), equalTo(asList("12", "23", "32", "21")));
	}
	class Field {
		private final String row;
		private final String column;

		public Field(String field) {
			row = field.substring(0, 1);
			column = field.substring(1);
		}

		List<String> neighboringFields() {

			int rowAbove = Integer.valueOf(row) - 1;
			int rowBelow = Integer.valueOf(row) + 1;
			int columnLeft = Integer.valueOf(column) - 1;
			int columnRight = Integer.valueOf(column) + 1;

			return asList(rowAbove + column, row + columnRight, rowBelow + column, row + columnLeft);
		}
	}


	// theGameISOverWhenAPlayerCapturesAtleastOneStone
	// aPlayerCanPlayOnAFieldThatISntTakenAlready
	// /...

}
