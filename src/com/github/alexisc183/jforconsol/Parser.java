package com.github.alexisc183.jforconsol;

/**
 * A function capable of parsing the string representation of an instance.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @param <T> the return type of this parsing operation
 * @since JForconsol 1.0.0
 */
@FunctionalInterface
public interface Parser<T> {
	/**
	 * Parses the given string to convert it to <code>T</code>.
	 * 
	 * @param aString the string representation of an instance
	 * @return a <code>T</code> from the converted string
	 */
	T parse(String aString);
}
