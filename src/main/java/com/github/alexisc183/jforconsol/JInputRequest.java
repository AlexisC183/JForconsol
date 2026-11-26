package com.github.alexisc183.jforconsol;

import java.util.Objects;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

/**
 * Request of an input value to the user with the certainty of said value always being correct.
 * <p>
 * The purpose of this class is to provide abstraction to operations of requesting input data through for example, a command line interface. Programmers can opt to instantiate this class instead of making the logic of data collection by themselves, in this way reducing significantly the code and preventing undesired input data.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @param <T> the type of the input to request
 * @since JForconsol 1.0.0
 */
public class JInputRequest<T> {
	private Parser<T> parseMethod;
	private boolean isCancellable;
	private Predicate<T> condition;
	private String errorMessage;
	private String requestMessage;
	
	/**
	 * Creates an input request with the specified parsing method.
	 * 
	 * @param function the function that converts a string to <code>T</code>
	 * @throws NullPointerException if the provided function is <code>null</code>
	 */
	public JInputRequest(Parser<T> function) {
		parseMethod = Objects.requireNonNull(function);
	}
	
	/**
	 * Checks whether this <code>JInputRequest</code> allows pressing its cancel button or not.
	 * 
	 * @return <code>true</code> if this <code>JInputRequest</code> allows pressing its cancel button; <code>false</code> otherwise.
	 */
	public boolean isCancellable() {
		return isCancellable;
	}
	
	/**
	 * Sets whether this <code>JInputRequest</code> allows pressing its cancel button or not depending on the provided value.
	 * 
	 * @param value If <code>true</code>, pressing cancel won't result in an invalid requested input, otherwise will.
	 */
	public void setCancellable(boolean value) {
		isCancellable = value;
	}
	
	/**
	 * Returns the <code>condition</code> object for this input request.
	 * 
	 * @return the predicate used to test the requested input
	 */
	public Predicate<T> getCondition() {
		return condition;
	}
	
	/**
	 * Sets the <code>condition</code> property. This predicate is used to test a requested input.
	 * 
	 * @param condition the predicate used to test the requested input
	 */
	public void setCondition(Predicate<T> condition) {
		this.condition = condition;
	}
	
	/**
	 * Returns the <code>errorMessage</code> object for this input request.
	 * 
	 * @return the string that may be printed if the user enters an invalid input
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Sets the <code>errorMessage</code> property. This error message may be printed if the user enters an invalid input.
	 * 
	 * @param errorMessage the string that may be printed if the user enters an invalid input
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Returns the <code>requestMessage</code> object for this input request.
	 * 
	 * @return the string that is part of the {@link javax.swing.JOptionPane} dialog used by this instance
	 */
	public String getRequestMessage() {
		return requestMessage;
	}
	
	/**
	 * Sets the <code>requestMessage</code> property. This request message is part of the {@link javax.swing.JOptionPane} dialog used by this instance.
	 * 
	 * @param requestMessage the string that is part of the <code>JOptionPane</code> dialog used by this instance
	 */
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	
	/**
	 * Requests the input by invoking a {@link javax.swing.JOptionPane} dialog.
	 * 
	 * @return a <code>T</code> from the converted input, or <code>null</code> if cancel was pressed according to the <code>isCancellable</code> property
	 * @see #isCancellable()
	 */
	public T request() {
		while (true) {
			String input = JOptionPane.showInputDialog(requestMessage);
			
			if (isCancellable && input == null) {
				return null;
			}
			
			try {
				return processInput(input);
			}
			catch (Exception e) {
				System.out.println(errorMessage == null ? "Error!" : errorMessage);
			}
		}
	}
	
	private T processInput(String input) throws Exception {
		if (input == null) {
			throw new IllegalStateException();
		}
		
		T instance = parseMethod.parse(input);
		
		if (condition != null && !condition.test(instance)) {
			throw new IllegalArgumentException();
		}
		
		return instance;
	}
}
