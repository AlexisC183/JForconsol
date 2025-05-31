package com.github.alexisc183.jforconsol;

import java.util.function.Predicate;
import java.util.Objects;
import java.util.Scanner;

/**
 * Requests of an input value to the user with the certainty of said value always being correct.
 * <p>
 * The purpose of this class is the same as {@link JInputRequest}. The major difference is that the <code>InputRequest</code> class fits those environments where GUIs cannot be rendered, like server-oriented operating systems. On the other hand the <code>JInputRequest</code> class is implemented with a Java Swing component, concretely a {@link javax.swing.JOptionPane} dialog.
 * <p>
 * The <code>InputRequest</code> class is implemented by using the {@link java.util.Scanner} class with the {@link System#in} field. Once any of the <code>request</code> methods of the <code>InputRequest</code> class is invoked, the <code>Scanner</code> class is instantiated and only once. This last operation means that at some point the <code>Scanner</code> instance must be closed. The <code>InputRequest</code> class provides a method for that.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @see JInputRequest
 * @since JForconsol 1.0.0
 */
public final class InputRequest {
	private static boolean isClosed;
	private static Scanner scanner;
	
	private static boolean isSkippable;
	private static String errorMessage;
	private static String requestMessage;
	
	private InputRequest() {
		
	}
	
	/**
	 * Checks whether requests can be skipped by pressing Enter or not.
	 * 
	 * @return <code>true</code> if requests can be skipped by pressing Enter; <code>false</code> otherwise.
	 */
	public static boolean isSkippable() {
		return isSkippable;
	}
	
	/**
	 * Sets whether requests can be skipped by pressing Enter or not depending on the provided value.
	 * 
	 * @param value If <code>true</code>, pressing Enter won't result in an invalid requested input, otherwise will.
	 */
	public static void setSkippable(boolean value) {
		isSkippable = value;
	}
	
	/**
	 * Returns the error message for the input request.
	 * 
	 * @return the string that may be printed if the user enters an invalid input
	 */
	public static String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Sets the error message that may be printed if the user enters an invalid input.
	 * 
	 * @param errorMessage the string that may be printed if the user enters an invalid input
	 */
	public static void setErrorMessage(String errorMessage) {
		InputRequest.errorMessage = errorMessage;
	}
	
	/**
	 * Returns the request message for the input request.
	 * 
	 * @return the string that is printed when requesting the input
	 */
	public static String getRequestMessage() {
		return requestMessage;
	}
	
	/**
	 * Sets the request message that is printed when requesting the input.
	 * 
	 * @param requestMessage the string that is printed when requesting the input
	 */
	public static void setRequestMessage(String requestMessage) {
		InputRequest.requestMessage = requestMessage;
	}
	
	/**
	 * Requests the input with no additional processing.
	 * 
	 * @return a string of the input, or <code>null</code> if the request was skipped according to the <code>isSkippable</code> method
	 * @see #isSkippable()
	 */
	public static String request() {
		return request(s -> s, null);
	}
	
	/**
	 * Requests the input and processes it with the provided conversion method.
	 * 
	 * @param <T> the type of the input to request
	 * @param parseMethod the method that converts a string to <code>T</code>
	 * @return a <code>T</code> from the converted input, or <code>null</code> if the request was skipped according to the <code>isSkippable</code> method
	 * @see #isSkippable()
	 */
	public static <T> T request(Parser<T> parseMethod) {
		return request(parseMethod, null);
	}
	
	/**
	 * Requests the input and processes it with the provided conversion method and predicate.
	 * 
	 * @param <T> the type of the input to request
	 * @param parseMethod the method that converts a string to <code>T</code>
	 * @param condition the predicate used to test the input
	 * @return a <code>T</code> from the converted input, or <code>null</code> if the request was skipped according to the <code>isSkippable</code> method
	 * @see #isSkippable()
	 */
	public static <T> T request(Parser<T> parseMethod, Predicate<T> condition) {
		doGeneralChecks(parseMethod);
		return ensureInstance(parseMethod, condition);
	}

	private static <T> void doGeneralChecks(Parser<T> parseMethod) {
		Objects.requireNonNull(parseMethod);
		
		if (isClosed) {
			throw new IllegalStateException("This class has been closed");
		}
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
	}
	
	private static <T> T ensureInstance(Parser<T> parseMethod, Predicate<T> condition) {
		while (true) {
			T instance = null;
			
			System.out.print(requestMessage == null ? "" : requestMessage);
			
			String input = scanner.nextLine();
			
			if (isSkippable && input.equals("")) {
				return instance;
			}
			
			try {
				instance = parseMethod.parse(input);
				
				if (condition != null && !condition.test(instance)) {
					throw new IllegalArgumentException();
				}
				
				return instance;
			}
			catch (Exception e) {
				System.out.println(errorMessage == null ? "Invalid input!" : errorMessage);
			}
		}
	}

	/**
	 * Closes the <code>Scanner</code> associated with this class.
	 * <p>
	 * Once this method is invoked, all of the <code>request</code> methods become unusable and throw an <code>IllegalStateException</code> regardless of whether the scanner was ever open.
	 */
	public static void close() {
		if (scanner != null) {
			scanner.close();
		}
		
		isClosed = true;
	}
}
