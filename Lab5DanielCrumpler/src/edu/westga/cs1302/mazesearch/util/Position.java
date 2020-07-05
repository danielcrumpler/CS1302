package edu.westga.cs1302.mazesearch.util;

/**
 * The Class Position.
 * 
 * @author CS1302
 */
public class Position {

	private int row;
	private int col;

	/**
	 * Instantiates a new position.
	 *
	 * @precondition none
	 * @postcondition getRow() == row && getCol() == col
	 * 
	 * @param row the row
	 * @param col the column
	 */
	public Position(int row, int col) {
		this.set(row, col);
	}

	/**
	 * Sets the position.
	 *
	 * @precondition none
	 * @postcondition getRow() == row && getCol() == col
	 * 
	 * @param row the row
	 * @param col the column
	 */
	public void set(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Gets the row.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Decrement row.
	 * 
	 * @precondition none
	 * @postcondition getRow() = getRow()@prev - 1
	 */
	public void decrementRow() {

		this.row--;
	}

	/**
	 * Increment row.
	 * 
	 * @precondition none
	 * @postcondition getRow() = getRow()@prev + 1
	 */
	public void incrementRow() {
		this.row++;
	}

	/**
	 * Gets the column.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the column
	 */
	public int getColumn() {
		return this.col;
	}

	/**
	 * Decrement column.
	 * 
	 * @precondition none
	 * @postcondition getCol() = getCol()@prev - 1
	 */
	public void decrementCol() {
		this.col--;
	}

	/**
	 * Increment column.
	 * 
	 * @precondition none
	 * @postcondition getCol() = getCol()@prev + 1
	 */
	public void incrementCol() {
		this.col++;
	}

	@Override
	public String toString() {
		String result = "(" + this.row + ", " + this.col + ")";
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		if (this.col != other.col) {
			return false;
		}
		if (this.row != other.row) {
			return false;
		}
		return true;
	}
}
