package com.github.alexisc183.jforconsol;

/**
 * A prime number generator.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public class PrimeNumber {
	private int incrementableNumber;
	
	/**
	 * Creates an instance ready to compute prime numbers.
	 */
	public PrimeNumber() {
		incrementableNumber = 1;
	}
	
	/**
	 * Uses the last prime number computed with this instance to return the next prime number.
	 * 
	 * @return the next prime number of this instance or two (2) if this method is invoked for the first time
	 */
	public int next() {
		byte exactDivisions;
		int divisor;
		
		do {
			exactDivisions = 2;
			divisor = 2;
			incrementableNumber++;
			
			while (exactDivisions < 3 && divisor < incrementableNumber) {
				if (incrementableNumber % divisor == 0) {
					exactDivisions++;
				}
				divisor++;
			}
		}
		while (exactDivisions != 2);
		
		return incrementableNumber;
	}
	
	/**
	 * Resets this instance to its initial state.
	 */
	public void reset() {
		incrementableNumber = 1;
	}
}
