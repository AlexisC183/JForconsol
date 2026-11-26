package com.github.alexisc183.jforconsol;

/**
 * Thrown to indicate that the program has attempted to convert a string to another type, but the string does not have the appropriate format.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @see CharParser#parse(String)
 * @since JForconsol 1.0.0
 */
public class FormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a format exception with no detail message and with no cause.
     */
	public FormatException() {
    	
    }

	/**
	 * Creates a format exception with the specified detail message. The cause is not initialized as stated in {@link java.lang.RuntimeException#RuntimeException(java.lang.String)}.
	 * 
	 * @param message a string that provides details about this format exception
	 */
    public FormatException(String message) {
        super(message);
    }

    /**
     * Creates a format exception with the specified detail message and cause.
     * 
     * @param message a string that provides details about this format exception
     * @param cause the throwable that led this format exception to be thrown
     */
    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a format exception with the specified cause.
     * <p>
     * This constructor defaults the detail message of this instance to <code>cause == null ? null : cause.toString()</code>; the created format exception is basically a wrapper to the original throwable.
     * 
     * @param cause the throwable that led this format exception to be thrown
     */
    public FormatException(Throwable cause) {
        super(cause);
    }
}