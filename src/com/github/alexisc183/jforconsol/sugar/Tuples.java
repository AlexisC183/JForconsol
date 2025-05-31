package com.github.alexisc183.jforconsol.sugar;

/**
 * Utility class for creating tuples. It contains overloads of a single static method that is meant to be statically imported, to achieve syntactic sugar for tuple creation like this:
 * <pre>
 * import static com.github.alexisc183.jforconsol.sugar.Tuples.$;
 * 
 * var status = $("Invalid credentials", 5);
 * </pre>
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public final class Tuples {
	private Tuples() {
		
	}
	
	/**
	 * Creates a 2-tuple.
	 * 
	 * @param <T> the type of the first item
	 * @param <U> the type of the second item
	 * @param item1 the first item
	 * @param item2 the second item
	 * @return a tuple
	 */
	public static <T, U> BiTuple<T, U> $(T item1, U item2) {
		return new BiTuple<T, U>(item1, item2);
	}
	
	/**
	 * Creates a 3-tuple.
	 * 
	 * @param <T> the type of the first item
	 * @param <U> the type of the second item
	 * @param <V> the type of the third item
	 * @param item1 the first item
	 * @param item2 the second item
	 * @param item3 the third item
	 * @return a tuple
	 */
	public static <T, U, V> TriTuple<T, U, V> $(T item1, U item2, V item3) {
		return new TriTuple<T, U, V>(item1, item2, item3);
	}
	
	/**
	 * Creates a 4-tuple.
	 * 
	 * @param <T> the type of the first item
	 * @param <U> the type of the second item
	 * @param <V> the type of the third item
	 * @param <W> the type of the fourth item
	 * @param item1 the first item
	 * @param item2 the second item
	 * @param item3 the third item
	 * @param item4 the fourth item
	 * @return a tuple
	 */
	public static <T, U, V, W> TetraTuple<T, U, V, W> $(T item1, U item2, V item3, W item4) {
		return new TetraTuple<T, U, V, W>(item1, item2, item3, item4);
	}
}