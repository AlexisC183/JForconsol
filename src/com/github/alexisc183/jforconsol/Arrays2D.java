package com.github.alexisc183.jforconsol;

import java.util.Objects;

/**
 * Some support for arrays that contain nested arrays of same length.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public final class Arrays2D {
	private Arrays2D() {
		
	}
	
	/**
	 * Checks whether the provided array contains nested arrays of the same length or not.
	 * <p>
	 * If the array has length zero, a 0x0 array is assumed. This method returns <code>false</code> if nested arrays equaling <code>null</code> are found because by definition, multidimensional arrays (2D arrays in this case) are arrays of arrays, not arrays of nulls.
	 * 
	 * @param array a multidimensional or jagged array to be checked
	 * @return <code>true</code> if the provided array contains nested arrays of the same length; <code>false</code> otherwise.
	 * @throws NullPointerException if the provided array is <code>null</code>
	 */
	public static boolean is2DArray(Object[][] array) {
		Objects.requireNonNull(array);
		
		if (array.length == 0) {
			// A 0x0 array is assumed
			return true;
		}
		if (array[0] == null) {
			// Multidimensional arrays are arrays of arrays, not arrays of nulls
			return false;
		}
		
		final int FIRST_ROW_LENGTH = array[0].length;
	
		for (int i = 1; i < array.length; i++) {
			if (array[i] == null || array[i].length != FIRST_ROW_LENGTH) {
				return false;
			}
		}
		
		return true;
	}
}
