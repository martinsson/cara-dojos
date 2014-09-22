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
	}


	protected void checkConversionOf(int arabicNumber, String expectedRomanNumber) {
		String result = romans.convert(arabicNumber);
		Assertions.assertThat(result).isEqualTo(expectedRomanNumber);
	}
	
}
