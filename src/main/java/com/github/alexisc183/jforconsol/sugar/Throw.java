package com.github.alexisc183.jforconsol.sugar;

/**
 * Utility class for throwing exceptions in expressions. Usage example:
 * <pre>
 * public String tellAge(int age) {
 *     return age &lt; 0
 *     ?
 *         Throw.unchecked(new IllegalArgumentException())
 *     :
 *         "The age is " + age;
 * }
 * </pre>
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public final class Throw {
	private Throw() {
		
	}

	/**
	 * Throws the provided checked exception.
	 * 
	 * @param <T> the expected return type from this method invocation
	 * @param <X> the type of the exception to be thrown
	 * @param exception the exception to throw
	 * @return nothing, just throws
	 * @throws X always
	 */
	public static <T, X extends Throwable> T checked(X exception) throws X {
		throw exception;
	}
	
	/**
	 * Throws the provided unchecked exception.
	 * 
	 * @param <T> the expected return type from this method invocation
	 * @param exception the exception to throw
	 * @return nothing, just throws
	 */
	public static <T> T unchecked(RuntimeException exception) {
		throw exception;
	}
}