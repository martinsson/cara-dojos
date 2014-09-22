package dojos;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RomanNumeralsTest {

	RomanNumerals romans = new RomanNumerals();
	@Test
	public void One_evaluates_to_I() {
		checkConversionOf(1, "I");
		checkConversionOf(2, "II");
		checkConversionOf(3, "III");
		checkConversionOf(4, "IV");
		checkConversionOf(5, "V");
		checkConversionOf(6, "VI");
		checkConversionOf(7, "VII");
		checkConversionOf(8, "VIII");
		checkConversionOf(9, "IX");
		checkConversionOf(10, "X");
		checkConversionOf(11, "XI");
		checkConversionOf(12, "XII");
		checkConversionOf(13, "XIII");
		checkConversionOf(14, "XIV");
		checkConversionOf(15, "XV");
		checkConversionOf(16, "XVI");
		checkConversionOf(17, "XVII");
		checkConversionOf(18, "XVIII");
		checkConversionOf(19, "XIX");
		checkConversionOf(20, "XX");
	}


	protected void checkConversionOf(int arabicNumber, String expectedRomanNumber) {
		String result = romans.convert(arabicNumber);
		Assertions.assertThat(result).isEqualTo(expectedRomanNumber);
	}
	
}
