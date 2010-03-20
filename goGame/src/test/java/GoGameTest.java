import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class GoGameTest {
	int nbLibertiesByDefault = 4;

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
	public void theNumberLibertiesOfAGroupOfTwoIsTheSumOfAllStonesLiberties() {
		List<String> fieldsTaken = asList("22", "32");
		List<String> cornersForB2 = neighboringFields("22");
		List<String> cornersForC2 = neighboringFields("32");
		int totalLiberties = cornersFreeForTwoFields(fieldsTaken, cornersForB2, cornersForC2);
		assertThat(totalLiberties, equalTo(6));
	}

	@Test
	public void theNumberLibertiesOfAGroupOfThreeIsTheSumOfAllStonesLiberties() {
		List<String> fieldsTaken = asList("22", "32", "42");
		List<String> cornersForB2 = neighboringFields("22");
		List<String> cornersForC2 = neighboringFields("32");
		List<String> cornersForD2 = neighboringFields("42");
		int totalLiberties = cornersFreeForTwoFields(fieldsTaken, cornersForB2, cornersForC2, cornersForD2);
		assertThat(totalLiberties, equalTo(8));
	}

	/*
	 * 11 12 13 21 22 23 31 32 33
	 */
	@Test
	public void neighboringFieldsReturnsTheFourSurroundingFields() throws Exception {
		assertThat(neighboringFields("22"), equalTo(asList("12", "23", "32", "21")));
	}

	private List<String> neighboringFields(String field) {
		String row = field.substring(0, 1);
		String column = field.substring(1);

		String rowAbove = String.valueOf(Integer.valueOf(row) - 1);
		String rowBelow = String.valueOf(Integer.valueOf(row) + 1);
		String columnLeft = String.valueOf(Integer.valueOf(column) - 1);
		String columnRight = String.valueOf(Integer.valueOf(column) + 1);

		return asList(rowAbove.concat(column), row.concat(columnRight), rowBelow.concat(column), row.concat(columnLeft));
	}

	private int cornersFreeForTwoFields(List<String> fieldsTaken, List<String>... cornersForEveryElement) {
		Set<String> corners = new TreeSet<String>();
		for (List<String> cornersForX : cornersForEveryElement) {
			corners.addAll(cornersForX);
		}
		corners.removeAll(fieldsTaken);
		return corners.size();
	}

	@Test
	public void aGroupCanBeTakenIfItHasOnlyOneLIberty() {

	}

	// theGameISOverWhenAPlayerCapturesAtleastOneStone
	// aPlayerCanPlayOnAFieldThatISntTakenAlready
	// /...

}
