package dojos;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RomanNumeralsTest {

	@Test
	public void One_evaluates_to_I() {
		RomanNumerals romans = new RomanNumerals();
		String result = romans.convert(1);
		Assertions.assertThat(result).isEqualTo("I");
	}

}
