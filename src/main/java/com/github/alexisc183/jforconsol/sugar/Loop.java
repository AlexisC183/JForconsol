package com.github.alexisc183.jforconsol.sugar;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Looping methods that can be used in expressions.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public final class Loop {
	private Loop() {
		
	}
	
	/**
	 * Takes a mutable object and mutates it until a given condition is true.
	 * 
	 * @param <T> the type of the object to mutate on each iteration
	 * @param seed a mutable object that determines the looping of this method
	 * @param condition a condition used to stop the loop when it is true
	 * @param mutator a mutator of the seed
	 * @return the mutated seed
	 * @throws NullPointerException if the condition or the mutator is <code>null</code>
	 */
	public static <T> T until(T seed, Predicate<T> condition, Consumer<T> mutator) {
		Objects.requireNonNull(condition, "\"condition\" cannot be null");
		Objects.requireNonNull(mutator, "\"mutator\" cannot be null");
		
		while (!condition.test(seed)) {
			mutator.accept(seed);
		}
		
		return seed;
	}
	
	/**
	 * Takes an initial value as a seed, and rebinds it until a given condition is true.
	 * 
	 * @param <T> the type of the object to rebind on each iteration
	 * @param seed the object which the looping of this method is based on
	 * @param condition a condition used to stop the loop when it is true
	 * @param accumulator a function that supplies the next value based on the seed
	 * @return the last value bound in the iteration process
	 * @throws NullPointerException if the condition or the accumulator is <code>null</code>
	 */
	public static <T> T untilPure(T seed, Predicate<T> condition, UnaryOperator<T> accumulator) {
		Objects.requireNonNull(condition, "\"condition\" cannot be null");
		Objects.requireNonNull(accumulator, "\"accumulator\" cannot be null");
		
		while (!condition.test(seed)) {
			seed = accumulator.apply(seed);
		}
		
		return seed;
	}
}