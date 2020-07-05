package edu.westga.cs1302.mazesearch.game;

import edu.westga.cs1302.mazesearch.resources.ExceptionMessages;
import edu.westga.cs1302.mazesearch.util.Position;

/**
 * The Class MazeGameData.
 * 
 * @author CS1302
 */
public class MazeGameData {

	private Maze maze;
	private Position curPos;
	private State state;

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
		this.state = State.PLAY;
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
	public State getStateType() {
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
	public void setState(State state) {
		if (state == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_STATE);
		}
		if (state == State.RESTART) {
			this.setCurrentPosition(this.getMaze().getStartPosition());
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
		return this.state == State.GAME_OVER;
	}

	/**
	 * The game is in a state that displays hints.
	 * 
	 * @precondition none
	 * @postcondition getState() == State.HELP
	 */
	public void displayHelp() {
		this.setState(State.HELP);
	}

	/**
	 * F Move left and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getColumn() ==
	 *                getCurrentPosition().getColumn()@prev - 1
	 */
	public void moveLeft() {
		if (this.canMoveLeft()) {
			this.curPos.decrementCol();
			this.updateState();
		}
	}

	private boolean canMoveLeft() {
		return !this.gameOver() && this.curPos.getColumn() > 0 && !this.maze.hasLeftWallAt(this.curPos);
	}

	/**
	 * Move right and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getColumn() ==
	 *                getCurrentPosition().getColumn()@prev + 1
	 */
	public void moveRight() {
		if (this.canMoveRight()) {
			this.curPos.incrementCol();
			this.updateState();
		}
	}

	private boolean canMoveRight() {
		return !this.gameOver() && this.curPos.getColumn() < this.getNumberColumns()
				&& !this.maze.hasRightWallAt(this.curPos);
	}

	/**
	 * Move up and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getRow() ==
	 *                getCurrentPosition().getRow()@prev - 1
	 */
	public void moveUp() {
		if (this.canMoveUp()) {
			this.curPos.decrementRow();
			this.updateState();
		}
	}

	private boolean canMoveUp() {
		return !this.gameOver() && this.curPos.getRow() > 0 && !this.maze.hasUpperWallAt(this.curPos);
	}

	/**
	 * Move down and update the state if goal is reached.
	 * 
	 * @precondition none
	 * @postcondition getCurrentPosition().getRow() ==
	 *                getCurrentPosition().getrow()@prev + 1
	 */
	public void moveDown() {
		if (this.canMoveDown()) {
			this.curPos.incrementRow();
			this.updateState();
		}
	}

	private boolean canMoveDown() {
		return !this.gameOver() && this.curPos.getRow() < this.getNumberRows()
				&& !this.maze.hasLowerWallAt(this.curPos);
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
			this.state = State.GOAL_FOUND;
		}
	}

	/**
	 * Checks if the user is still playing the game.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return true if the game is in play mode
	 */
	public boolean play() {
		if (this.state != State.GAME_OVER && this.state != State.GOAL_FOUND) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the state of the maze game data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Creates a String with the possible moves from the current location.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the string of moves
	 */
	public String getPossibleMoves() {
		String possibleMoves = "Hint - possible moves: ";
		if (this.canMoveDown()) {
			possibleMoves += "down ";
		}
		if (this.canMoveUp()) {
			possibleMoves += "up ";
		}
		if (this.canMoveLeft()) {
			possibleMoves += "left ";
		}
		if (this.canMoveRight()) {
			possibleMoves += "right ";
		}
		return possibleMoves;
	}

}
