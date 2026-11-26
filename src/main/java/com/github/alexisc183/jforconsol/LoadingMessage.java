package com.github.alexisc183.jforconsol;

import java.util.Objects;

/**
 * An animated loading message thought to be used for operations that take time to be completed.
 * <p>
 * This class uses a new <code>Thread</code> in order to display on the "standard" output stream an animation.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public class LoadingMessage {
	/**
	 * Styles for <code>LoadingMessage</code> instances.
	 */
	public enum LoadingStyle {
		/**
		 * The "periods" style. 
		 */
		PERIODS,
		
		/**
		 * The "lines" style. 
		 */
		LINES,
		
		/**
		 * The "rotary line" style. 
		 */
		ROTARY_LINE
	}
	
	private String message;
	private LoadingStyle loadingStyle;
	private Thread thread;
	private boolean isRunning;
	
	/**
	 * Creates a loading message.
	 */
	public LoadingMessage() {
		loadingStyle = LoadingStyle.PERIODS;
	}
	
	/**
	 * Returns the <code>message</code> object for this loading message.
	 * 
	 * @return the string thought for notifying the user something. It is printed beside the asynchronous animation.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the <code>message</code> property. This message is thought for notifying the user something.
	 * 
	 * @param message the string thought for notifying the user something. It is printed beside the asynchronous animation.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Returns the <code>loadingStyle</code> object for this loading message.
	 * 
	 * @return the <code>loadingStyle</code> that determines the appearance of the asynchronous animation of this instance
	 */
	public LoadingStyle getLoadingStyle() {
		return loadingStyle;
	}

	/**
	 * Sets the <code>loadingStyle</code> property.&nbsp;The default value is <code>LoadingStyle.PERIODS</code>.
	 * 
	 * @param loadingStyle the <code>loadingStyle</code> that determines the appearance of the asynchronous animation of this instance
	 * @throws NullPointerException if the provided loading style is <code>null</code>
	 */
	public void setLoadingStyle(LoadingStyle loadingStyle) {
		this.loadingStyle = Objects.requireNonNull(loadingStyle);
	}
	
	/**
	 * Checks whether the asynchronous animation produced by the <code>toggle()</code> method of this instance is on or not.
	 * 
	 * @return <code>true</code> if the asynchronous animation produced by the <code>toggle()</code> method of this instance is on; <code>false</code> otherwise.
	 * @see #toggle()
	 */
	public boolean isRunning() {
		return isRunning;
	}
	
	private void rotaryLineAction() {
		if (message != null) {
			System.out.print(' ');
		}
		System.out.print('\u2500');
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(1000);
				System.out.print("\b\\");
				Thread.sleep(1000);
				System.out.print("\b|");
				Thread.sleep(1000);
				System.out.print("\b/");
				Thread.sleep(1000);
				System.out.print("\b" + '\u2500');
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void linesAction() {
		if (message != null) {
			System.out.print(' ');
		}
		System.out.print("___");
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(1000);
				System.out.print("\b\b\b-__");
				Thread.sleep(1000);
				System.out.print("\b\b\b_-_");
				Thread.sleep(1000);
				System.out.print("\b\b_-");
				Thread.sleep(1000);
				System.out.print("\b_");
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void periodsAction() {
		System.out.print("   ");
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(1000);
				System.out.print("\b\b\b.  ");
				Thread.sleep(1000);
				System.out.print("\b\b. ");
				Thread.sleep(1000);
				System.out.print("\b.");
				Thread.sleep(1000);
				System.out.print("\b\b\b   ");
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	/**
	 * Starts this loading message or stops its asynchronous animation, according to the <code>isRunning</code> property.
	 * <p>
	 * If <code>isRunning</code> is <code>false</code>, then this method prints the <code>message</code> object of this instance and starts an asynchronous animation.
	 * <p>
	 * If <code>isRunning</code> is <code>true</code>, then this method stops the asynchronous animation of this instance.
	 * 
	 * @see #isRunning()
	 */
	public void toggle() {
		if (isRunning) {
			thread.interrupt();
			System.out.println();
			
			isRunning = false;
		}
		else {
			start();
			
			isRunning = true;
		}
	}
	
	private void start() {
		if (message != null) {
			System.out.print(message);
		}
		
		thread = new Thread(
			switch (loadingStyle) {
				case ROTARY_LINE -> this::rotaryLineAction;
				case LINES -> this::linesAction;
				default -> this::periodsAction;
			}
		);
		
		thread.start();
	}
}