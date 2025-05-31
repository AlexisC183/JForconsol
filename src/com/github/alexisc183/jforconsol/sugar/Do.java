package com.github.alexisc183.jforconsol.sugar;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Utility for mutating objects in an expression-only context.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public final class Do {
	private Do() {
		
	}
	
	/**
	 * Mutates an object and then returns it.
	 * 
	 * @param <T> the type of the mutable object
	 * @param mutable the object to mutate
	 * @param mutator a procedure that may perform side effects on the mutable object
	 * @return the mutable object
	 * @throws NullPointerException if the mutator is <code>null</code>
	 */
	public static <T> T to(T mutable, Consumer<T> mutator) {
		Objects.requireNonNull(mutator).accept(mutable);
		return mutable;
	}
}