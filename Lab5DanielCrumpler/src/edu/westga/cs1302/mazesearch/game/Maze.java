package edu.westga.cs1302.mazesearch.game;

import edu.westga.cs1302.mazesearch.util.Position;

/**
 * The Class Maze.
 * 
 * @author CS1302
 */
public class Maze {

	private int[][] maze;
	private int numRows;
	private int numCols;
	private Position startPos;
	private Position goalPos;

	/**
	 * Instantiates a new maze.
	 * 
	 * @precondition maze != null && maze is a valid maze with at least one cell &&
	 *               startPos is a valid position in the specified maze && goalPos
	 *               is a valid position in the specified maze
	 * @postcondition this maze represents a maze with the passed in data
	 * 
	 * @param maze     the maze
	 * @param startPos start position
	 * @param goalPos  goal position
	 */
	public Maze(int[][] maze, Position startPos, Position goalPos) {
		if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
			throw new IllegalArgumentException("maze must have at least one cell");
		}
		this.maze = maze;
		this.numRows = this.maze.length;
		this.numCols = this.maze[0].length;
		if (!this.isValidPosition(startPos)) {
			throw new IllegalArgumentException("invalid start position");
		}
		if (!this.isValidPosition(goalPos)) {
			throw new IllegalArgumentException("invalid goal position");
		}
		this.startPos = startPos;
		this.goalPos = goalPos;
	}

	/**
	 * Gets the number rows.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the number rows
	 */
	public int getNumberRows() {
		return this.numRows;
	}

	/**
	 * Gets the number columns.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number columns
	 */
	public int getNumberColumns() {
		return this.numCols;
	}

	/**
	 * Gets the start position
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the start position
	 */
	public Position getStartPosition() {
		return this.startPos;
	}

	/**
	 * Gets the goal position
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return this.goalPos;
	}

	/**
	 * Checks for left wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param pos the position
	 * @return true, if successful
	 */
	public boolean hasLeftWallAt(Position pos) {
		return this.hasWallAt(pos.getRow(), pos.getColumn(), 1);
	}

	/**
	 * Checks for left wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true, if successful
	 */
	public boolean hasLeftWallAt(int row, int col) {
		return this.hasWallAt(row, col, 1);
	}

	/**
	 * Checks for right wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param pos the position
	 * @return true, if successful
	 */
	public boolean hasRightWallAt(Position pos) {
		return this.hasWallAt(pos.getRow(), pos.getColumn(), 4);
	}

	/**
	 * Checks for right wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true, if successful
	 */
	public boolean hasRightWallAt(int row, int col) {
		return this.hasWallAt(row, col, 4);
	}

	/**
	 * Checks for upper wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param pos the position
	 * @return true, if successful
	 */
	public boolean hasUpperWallAt(Position pos) {
		return this.hasWallAt(pos.getRow(), pos.getColumn(), 2);
	}

	/**
	 * Checks for upper wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true, if successful
	 */
	public boolean hasUpperWallAt(int row, int col) {
		return this.hasWallAt(row, col, 2);
	}

	/**
	 * Checks for lower wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param pos the position
	 * @return true, if successful
	 */
	public boolean hasLowerWallAt(Position pos) {
		return this.hasWallAt(pos.getRow(), pos.getColumn(), 8);
	}

	/**
	 * Checks for lower wall at specified position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true, if successful
	 */
	public boolean hasLowerWallAt(int row, int col) {
		return this.hasWallAt(row, col, 8);
	}

	/**
	 * Checks if the specified position is the goal.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true, if the specified position is the goal position
	 */
	public boolean isGoalPosition(int row, int col) {
		return this.goalPos.equals(new Position(row, col));
	}

	@Override
	public String toString() {
		String result = "";
		for (int row = 0; row < this.numRows; row++) {
			for (int col = 0; col < this.numCols; col++) {
				result += this.maze[row][col] + " ";
			}
			result += System.lineSeparator();
		}
		result += "start: " + this.startPos + System.lineSeparator();
		result += "goal: " + this.goalPos + System.lineSeparator();
		return result;
	}

	/**
	 * Checks for wall at specified position.
	 *
	 * @param row  the row
	 * @param col  the column
	 * @param wall the wall
	 * @return true, if successful
	 */
	private boolean hasWallAt(int row, int col, int wall) {
		if (!this.isValidPosition(new Position(row, col))) {
			return false;
		}
		return (this.maze[row][col] & wall) == wall;
	}

	/**
	 * Checks if the specified position is a valid position for this maze
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param pos the position to be checked
	 * @return true if the specified position is a valid position in this maze
	 */
	public boolean isValidPosition(Position pos) {
		if (pos.getRow() < 0 || pos.getRow() >= this.maze.length || pos.getColumn() < 0
				|| pos.getColumn() >= this.maze[0].length) {
			return false;
		}
		return true;
	}
}
