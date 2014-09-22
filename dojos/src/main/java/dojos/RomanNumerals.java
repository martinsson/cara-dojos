package dojos;

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {
	Map<Integer, String> specials = new HashMap<Integer, String>() {{
		put(100, "C");
		put(0, "");
		put(1, "I");
		put(2, "II");
		put(3, "III");
		put(4, "IV");
		put(5, "V");
		put(9, "IX");
		put(10, "X");
	}};

	public String convert(int number) {
		if (number == 0) return "";
		if (number >= 100 )
			return "C" + convert(number -100);
		if (number >= 90 )
			return "XC" + convert(number -90);
		if (number >= 50 )
			return "L" + convert(number -50);
		if (number >= 40 )
			return "XL" + convert(number -40);
		
//		if (number >= 10 )
//			return specials.get(10) + convert(number - 10);
//		else if (number == 9)
//			return specials.get(9) + convert(number - 9);
//		else if (number >= 5)
//			return specials.get(5) + convert(number -5);
//		else 
//			return specials.get(number) + convert(number - number);
		else 
			while (there are still some special cases)
				if number is in between special cases, 
					specials.get(lowerlimit) + convert (number - lowerlimit);
			return specials.get(number) + convert(number - number);
		
	}

}
