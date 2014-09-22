package dojos;

public class RomanNumerals {

	public String convert(int number) {
		if (number >= 100 )
			return "C" + convert(number -100);
		if (number >= 90 )
			return "XC" + convert(number -90);
		if (number >= 50 )
			return "L" + convert(number -50);
		if (number >= 40 )
			return "XL" + convert(number -40);
		if (number >= 10 )
			return "X" + convert(number -10);
		else if (number == 9)
			return "IX";
		else if (number >= 5)
			return "V" + convert(number -5);
		else if (number == 4)
			return "IV";
		else if (number == 3)
			return "III";
		else if (number == 2)
			return "II";
		else if (number == 1)
			return "I";
		else return "";
		
	}

}
