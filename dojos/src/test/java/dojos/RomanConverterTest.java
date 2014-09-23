package dojos;

import static org.junit.Assert.*;

import org.junit.Test;

public class RomanConverterTest {
	private RomanConverter converter = new RomanConverter();
	
	@Test
	public void between_one_and_three() {
		assertEquals("I", converter.fromArabic(1));
		assertEquals("II", converter.fromArabic(2));
		assertEquals("III", converter.fromArabic(3));
	}
	@Test
	public void four() {
		assertEquals("IV", converter.fromArabic(4));
	}
	
	@Test
	public void five() {
		assertEquals("V", converter.fromArabic(5));
	}

	@Test
	public void six_to_eight() {
		assertEquals("VI", converter.fromArabic(6));
		assertEquals("VII", converter.fromArabic(7));
		assertEquals("VIII", converter.fromArabic(8));
	}
	
	@Test
	public void nine() {
		assertEquals("IX", converter.fromArabic(9));
	}
	
	@Test
	public void ten() {
		assertEquals("X", converter.fromArabic(10));
	}
	
	@Test
	public void fourteen() {
		assertEquals("XIV", converter.fromArabic(14));
	}
	
	@Test
	public void nineteen() {
		assertEquals("XIX", converter.fromArabic(19));
	}
	
	@Test
	public void twenty_three() {
		assertEquals("XXIII", converter.fromArabic(23));
	}
	
	@Test
	public void twenty_five() {
		assertEquals("XXV", converter.fromArabic(25));
	}
	
	@Test
	public void fifty_three() {
		assertEquals("LIII", converter.fromArabic(53));
	}
	
	
}
