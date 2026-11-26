package com.github.alexisc183.jforconsol.sugar;

/**
 * Data structure containing four items that may be of different data types.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @param <T> the type of the first item
 * @param <U> the type of the second item
 * @param <V> the type of the third item
 * @param <W> the type of the fourth item
 * @param item1 the first item
 * @param item2 the second item
 * @param item3 the third item
 * @param item4 the fourth item
 * @since JForconsol 1.0.0
 */
public record TetraTuple<T, U, V, W>(T item1, U item2, V item3, W item4) {

}