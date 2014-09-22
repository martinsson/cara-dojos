package dojos;

public class RomanNumerals {

	public String convert(int number) {
		if (number == 5)
			return "V";
		if (number == 4)
			return "IV";
		if (number == 3)
			return "III";
		if (number == 2)
			return "II";
		return "I";
		
	}

}
