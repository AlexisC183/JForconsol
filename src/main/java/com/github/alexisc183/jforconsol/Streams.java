package com.github.alexisc183.jforconsol;

import java.util.function.Function;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility methods for streams.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public final class Streams {
	private Streams() {
		
	}
	
	/**
	 * Performs an action for each element of the provided stream and gets an optional exception returned from any iteration on the elements.
	 * <p>
	 * This is a short-circuiting terminal operation. This in part means that the iterative process of this method will terminate immediately when any iteration returns an instance of <code>X</code>.
	 * <p>
	 * The <code>action</code> argument is actually a function and is meant to perform side effects that may throw an exception. This exception can be caught and then returned. The function returning <code>null</code> can be understood as the action terminating successfully.
	 * 
	 * @param <T> the type of the stream elements
	 * @param <X> the type of the exception to be caused within the action 
	 * @param source the stream to be iterated over
	 * @param action the action to perform on the elements
	 * @return a non-empty optional, if any iteration on the elements returned an exception
	 */
	public static <T, X extends Throwable> Optional<X> forEachWithThrown(Stream<? extends T> source, Function<? super T, X> action) {
		return source
			   .map(action)
			   .filter(x -> x instanceof X)
			   .findAny();
	}
}