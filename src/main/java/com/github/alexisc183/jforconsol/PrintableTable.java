package com.github.alexisc183.jforconsol;

/**
 * Prints of a 2D array in a tabular fashion on the "standard" output stream.
 * 
 * @author AlexisC183
 * @version 1, 2025-05-30
 * @since JForconsol 1.0.0
 */
public class PrintableTable {
	/**
	 * Constants for aligning elements of arrays printed with a <code>PrintableTable</code> instance.
	 */
	public enum ElementAlignment {
		/**
		 * Alignment to the left.
		 */
		LEFT,
		
		/**
		 * Alignment to the right.
		 */
		RIGHT
	}
	
	private Object[][] array;
	private ElementAlignment elementAlignment;
	private byte[] greaterElementLengths;
	private byte i, j;
	private boolean hasSeparators;
	
	/**
	 * Creates a printable table.
	 */
	public PrintableTable() {
		
	}

	/**
	 * Returns the <code>elementAlignment</code> object for this printable table.
	 * 
	 * @return the <code>elementAlignment</code> that determines how each element of the table is padded
	 */
	public ElementAlignment getElementAlignment() {
		return elementAlignment;
	}

	/**
	 * Sets the <code>elementAlignment</code> property. If <code>null</code> is passed, then the elements of a <code>Number</code> array are aligned to the right.
	 * 
	 * @param elementAlignment the <code>elementAlignment</code> that determines how each element of the table is padded
	 */
	public void setElementAlignment(ElementAlignment elementAlignment) {
		this.elementAlignment = elementAlignment;
	}
	
	/**
	 * Returns <code>true</code> if this <code>PrintableTable</code> has column separators.
	 * 
	 * @return <code>true</code> if this <code>PrintableTable</code> has column separators; <code>false</code> otherwise.
	 */
	public boolean hasSeparators() {
		return hasSeparators;
	}
	
	/**
	 * Sets whether this <code>PrintableTable</code> has column separators or not depending on the provided value.
	 * 
	 * @param value If <code>true</code>, arrays printed by this instance will show column separators, otherwise won't.
	 */
	public void setSeparators(boolean value) {
		hasSeparators = value;
	}
	
	private void validateArray() {
		if (!Arrays2D.is2DArray(array)) {
			throw new IllegalArgumentException("All nested arrays of the provided array must be created and have the same length");
		}
	}
	
	private void determineGreaterElementLengths() {
		greaterElementLengths = new byte[array[0].length];
		
		for (i = 0; i < array[0].length; i++) {
			for (j = 0; j < array.length; j++) {
				if (array[j][i] == null) {
					if (greaterElementLengths[i] < 4) {
						greaterElementLengths[i] = 4;
					}
				}
				else {
					if (greaterElementLengths[i] < array[j][i].toString().length()) {
						greaterElementLengths[i] = (byte)array[j][i].toString().length();
					}
				}
			}
		}
	}
	
	/**
	 * Prints the provided array on the "standard" output stream.
	 * 
	 * @param array a 2D array to be printed
	 * @throws IllegalArgumentException if:
	 * <ul>
	 * <li>nested arrays equaling <code>null</code> are found
	 * <li>the provided array contains nested arrays of different lengths
	 * </ul>
	 * @throws NullPointerException if the provided array is <code>null</code>
	 * @see Arrays2D#is2DArray(Object[][])
	 */
	public void print(Object[][] array) {
		this.array = array;
		
		validateArray();
		
		if (array.length != 0) {
			determineGreaterElementLengths();
			
			for (i = 0; i < array.length; i++) {
				for (j = 0; j < array[0].length; j++) {
					printElement();
				}
				
				System.out.println();
			}
		}
	}
	
	private void printElement() {
		final Object ELEMENT = array[i][j];
		final int ELEMENT_LENGTH = ELEMENT == null ? 4 : ELEMENT.toString().length();
		
		if (!(array instanceof Number[][]) && elementAlignment == null || elementAlignment == ElementAlignment.LEFT) {
			System.out.print(ELEMENT + " ".repeat(greaterElementLengths[j] - ELEMENT_LENGTH) + " ");
		}
		else {
			System.out.print(" ".repeat(greaterElementLengths[j] - ELEMENT_LENGTH) + ELEMENT + " ");
		}
			
		if (hasSeparators) {
			System.out.print("| ");
		}
	}
}