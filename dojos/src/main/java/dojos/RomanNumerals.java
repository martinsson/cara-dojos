package dojos;

public class RomanNumerals {

	public String convert(int number) {
		if (number >= 5 && number <= 8)
			return "V" + convert(number -5);
		if (number == 4)
			return "IV";
		if (number == 3)
			return "III";
		if (number == 2)
			return "II";
		if (number == 1)
			return "I";
		else return "";
		
	}

}
