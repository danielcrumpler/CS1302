package edu.westga.cs1302.mazesearch.game;

import edu.westga.cs1302.mazesearch.util.Position;

/**
 * The Class MazeGameData.
 * 
 * @author CS1302
 */
public class MazeGameData {
	
	private Maze maze;
	private Position curPos;
	private String state;

	/**
	 * Instantiates new maze game data.
	 * 
	 * @precondition maze != null
	 * @postcondition this maze game data represents the passed in maze where the
	 *                current position is set to the start position
	 * 
	 * @param maze the maze
	 */
	public MazeGameData(Maze maze) {
		this.maze = maze;
		this.setCurrentPosition(this.maze.getStartPosition());
		this.state = "";
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
		return this.maze.getNumberRows();
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
		return this.maze.getNumberColumns();
	}
	
	/**
	 * Gets the goal position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return this.maze.getGoalPosition();
	}
	
	/**
	 * Gets the current position.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the current position
	 */
	public Position getCurrentPosition() {
		return this.curPos;
	}
	
	/**
	 * Sets the current position to a copy of the specified position if the
	 * specified position is a valid position in the maze.
	 *
	 * @precondition none
	 * @postcondition getCurrentPosition() == pos
	 * 
	 * @param pos the new current position
	 */
	public void setCurrentPosition(Position pos) {
		if (this.maze.isValidPosition(pos)) {
			this.curPos = new Position(pos.getRow(), pos.getColumn());
		}
	}
	
	/**
	 * Gets the maze
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the maze
	 */
	public Maze getMaze() {
		return this.maze;
	}
	
	/**
	 * Gets the state of the game
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the game state
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * Sets the state of the game
	 *
	 * @precondition state != null
	 * @postcondition none
	 * 
	 * @param state the new state
	 */
	public void setState(String state) {
		if (state == null) {
			throw new IllegalArgumentException("state cannot be null");
		}
		this.state = state;
	}
	
	/**
	 * Checks if the game is over
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if the game is over
	 */
	public boolean gameOver() {
		return this.state.equals("quit") || this.state.equals("goal found");
	}
	
	/**
	 * Checks if the specified position is the goal.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param row the row
	 * @param col the column
	 * @return true, if the specified position is the current position
	 */
	public boolean isGoalPosition(int row, int col) {
		return this.maze.getGoalPosition().equals(new Position(row, col));
	}
	
	/**
	 * Move left and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getColumn() ==
	 *                getCurrentPosition().getColumn()@prev - 1
	 */
	public void moveLeft() {
		if (!this.gameOver() && this.curPos.getColumn() > 0 && !this.maze.hasLeftWallAt(this.curPos)) {
			this.curPos.decrementCol();
			this.updateState();
		}
	}

	/**
	 * Move right and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getColumn() ==
	 *                getCurrentPosition().getColumn()@prev + 1
	 */
	public void moveRight() {
		if (!this.gameOver() && this.curPos.getColumn() < this.getNumberColumns()
				&& !this.maze.hasRightWallAt(this.curPos)) {
			this.curPos.incrementCol();
			this.updateState();
		}
	}
	
	/**
	 * Move up and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getRow() ==
	 *                getCurrentPosition().getRow()@prev - 1
	 */
	public void moveUp() {
		if (!this.gameOver() && this.curPos.getRow() > 0 && !this.maze.hasUpperWallAt(this.curPos)) {
			this.curPos.decrementRow();
			this.updateState();
		}
	}

	/**
	 * Move down and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getRow() ==
	 *                getCurrentPosition().getrow()@prev + 1
	 */
	public void moveDown() {
		if (!this.gameOver() && this.curPos.getRow() < this.getNumberRows() && !this.maze.hasLowerWallAt(this.curPos)) {
			this.curPos.incrementRow();
			this.updateState();
		}
	}

	@Override
	public String toString() {
		String result = this.maze + System.lineSeparator();
		result += "current: " + this.curPos + System.lineSeparator();
		return result;
	}
	
	/**
	 * updates the state
	 */
	private void updateState() {
		if (!this.gameOver() && this.curPos.equals(this.maze.getGoalPosition())) {
			this.state = "goal found";
		}
	}
}
