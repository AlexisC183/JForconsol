package com.github.alexisc183.jforconsol;

import java.util.Objects;

/**
 * A type capable of parsing the string representation of a character.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public class CharParser implements Parser<Character> {
	/**
	 * Creates a character parser.
	 */
	public CharParser() {
		
	}
	
	/**
	 * Parses the given string to convert it to character.
	 * 
	 * @param charString the string representation of a character
	 * @return a character from the converted string
	 * @throws FormatException if the provided string is not of length 1
	 * @throws NullPointerException if the provided string is <code>null</code>
	 */
	@Override
	public Character parse(String charString) {
		Objects.requireNonNull(charString);
		
		if (charString.length() != 1) {
			throw new FormatException("Passed string must be of length 1");
		}
		
		return charString.charAt(0);
	}
}
