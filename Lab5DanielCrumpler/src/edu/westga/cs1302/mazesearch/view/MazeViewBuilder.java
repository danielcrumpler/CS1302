package edu.westga.cs1302.mazesearch.view;

import edu.westga.cs1302.mazesearch.game.Maze;
import edu.westga.cs1302.mazesearch.util.Position;

/**
 * The Class Maze View Builder.
 * 
 * @author CS1302
 */
public class MazeViewBuilder {
	private static final String HORIZONTAL_WALL = "--";
	private static final String VERTICAL_WALL = "|";
	private static final String CORNER_WALL = "+";
	private static final String HORIZONTAL_CORNER_WALL = "-";
	private static final String NO_WALL = " ";
	private static final String EMPTY_CELL = "  ";

	private static final String NULL_MAZE = "maze cannot be null";
	private static final String NULL_POSITION = "current position cannot be null";

	private String[] mazeDisplay;
	private Position curPos;

	/**
	 * Instantiates a new MazeViewBuilder
	 * 
	 * @precondition maze != null && curPos != null && maze represents a valid maze
	 *               && curPos is a valid position in the maze
	 * @postcondition getMazeDisplay() returns a string array for display
	 *                representing the passed in maze and start position
	 * 
	 * @param maze   the maze whose display is created
	 * @param curPos the current position
	 */
	public MazeViewBuilder(Maze maze, Position curPos) {
		if (maze == null) {
			throw new IllegalArgumentException(NULL_MAZE);
		}
		if (curPos == null) {
			throw new IllegalArgumentException(NULL_POSITION);
		}
		this.curPos = new Position(curPos.getRow(), curPos.getColumn());
		this.createMazeDisplay(maze);
	}

	/**
	 * Sets the current position
	 * 
	 * @precondition curPos != null && curPos is a valid position in the maze
	 * @postcondition getMazeDisplay() returns a string array for display
	 *                representing the original maze and the passed in start
	 *                position
	 * 
	 * @param curPos the current position
	 */
	public void updateCurrentPosition(Position curPos) {
		if (curPos == null) {
			throw new IllegalArgumentException(NULL_POSITION);
		}
		if (!this.curPos.equals(curPos)) {
			this.removeCurrentPosition();
			this.curPos = new Position(curPos.getRow(), curPos.getColumn());
			this.markCurrentPosition();
		}
	}

	/**
	 * Gets the maze display.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the string array for display representing the maze and start position
	 */
	public String[] getMazeDisplay() {
		return this.mazeDisplay;
	}

	/**
	 * Creates the maze display.
	 * 
	 * @param maze the maze whose display is created
	 */
	private void createMazeDisplay(Maze maze) {
		int numRows = maze.getNumberRows();
		int numCols = maze.getNumberColumns();
		this.mazeDisplay = new String[numRows * 2 + 1];

		for (int row = 0; row < numRows; row++) {
			this.mazeDisplay[2 * row] = "";
			this.mazeDisplay[2 * row + 1] = "";
			for (int col = 0; col < numCols; col++) {
				this.mazeDisplay[2 * row] += this.createUpperLeftCorner(maze, row, col);
				this.mazeDisplay[2 * row] += this.createUpperWall(maze, row, col);
				this.mazeDisplay[2 * row + 1] += this.createLeftWall(maze, row, col) + EMPTY_CELL;
			}
			this.mazeDisplay[2 * row] += this.createUpperRightCorner(maze, row, numCols - 1);
			this.mazeDisplay[2 * row + 1] += this.createRightWall(maze, row, numCols - 1);
		}

		this.mazeDisplay[2 * numRows] = "";
		for (int col = 0; col < numCols; col++) {
			this.mazeDisplay[2 * numRows] += this.createLowerLeftCorner(maze, numRows - 1, col);
			this.mazeDisplay[2 * numRows] += this.createLowerWall(maze, numRows - 1, col);
		}
		this.mazeDisplay[2 * numRows] += this.createLowerRightCorner(maze, numRows - 1, numCols - 1);

		this.markGoalPosition(maze);
		this.markCurrentPosition();
	}

	/**
	 * Creates the upper left corner of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the upper left corner of the specified cell
	 */
	private String createUpperLeftCorner(Maze maze, int row, int col) {
		if ((maze.hasLeftWallAt(row, col) && maze.hasUpperWallAt(row, col))
				|| (maze.hasLeftWallAt(row, col) && maze.hasUpperWallAt(row, col - 1))
				|| (maze.hasLeftWallAt(row - 1, col) && maze.hasUpperWallAt(row, col))
				|| (maze.hasLeftWallAt(row - 1, col) && maze.hasUpperWallAt(row, col - 1))) {
			return CORNER_WALL;
		} else if (maze.hasUpperWallAt(row, col - 1) || maze.hasUpperWallAt(row, col)) {
			return HORIZONTAL_CORNER_WALL;
		} else if (maze.hasLeftWallAt(row - 1, col) || maze.hasLeftWallAt(row, col)) {
			return VERTICAL_WALL;
		} else {
			return NO_WALL;
		}
	}

	/**
	 * Creates the upper right corner of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the upper right corner of the specified cell
	 */
	private String createUpperRightCorner(Maze maze, int row, int col) {
		if ((maze.hasUpperWallAt(row, col) && maze.hasRightWallAt(row, col))
				|| (maze.hasUpperWallAt(row, col) && maze.hasRightWallAt(row - 1, col))
				|| (maze.hasUpperWallAt(row, col + 1) && maze.hasRightWallAt(row, col))
				|| (maze.hasUpperWallAt(row, col + 1) && maze.hasRightWallAt(row - 1, col))) {
			return CORNER_WALL;
		} else if (maze.hasRightWallAt(row - 1, col) || maze.hasRightWallAt(row, col)) {
			return VERTICAL_WALL;
		} else {
			return NO_WALL;
		}
	}

	/**
	 * Creates the lower left corner of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the lower left corner of the specified cell
	 */
	private String createLowerLeftCorner(Maze maze, int row, int col) {
		if (maze.hasLeftWallAt(row, col) && (maze.hasLowerWallAt(row, col - 1) || maze.hasLowerWallAt(row, col))) {
			return CORNER_WALL;
		} else if (maze.hasLowerWallAt(row, col - 1) && maze.hasLowerWallAt(row, col)) {
			return HORIZONTAL_CORNER_WALL;
		} else if (maze.hasLeftWallAt(row, col)) {
			return VERTICAL_WALL;
		} else {
			return NO_WALL;
		}
	}

	/**
	 * Creates the lower right corner of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the lower right corner of the specified cell
	 */
	private String createLowerRightCorner(Maze maze, int row, int col) {
		if ((maze.hasLowerWallAt(row, col) && maze.hasRightWallAt(row, col))
				|| (maze.hasLowerWallAt(row, col) && maze.hasRightWallAt(row + 1, col))
				|| (maze.hasLowerWallAt(row, col + 1) && maze.hasRightWallAt(row, col))
				|| (maze.hasLowerWallAt(row, col + 1) && maze.hasRightWallAt(row + 1, col))) {
			return CORNER_WALL;
		} else if (maze.hasLowerWallAt(row, col) || maze.hasLowerWallAt(row, col + 1)) {
			return HORIZONTAL_CORNER_WALL;
		} else if (maze.hasRightWallAt(row, col) || maze.hasRightWallAt(row + 1, col)) {
			return VERTICAL_WALL;
		} else {
			return NO_WALL;
		}
	}

	/**
	 * Creates the upper wall of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the upper wall of the specified cell
	 */
	private String createUpperWall(Maze maze, int row, int col) {
		if (maze.hasUpperWallAt(row, col)) {
			return HORIZONTAL_WALL;
		} else {
			return EMPTY_CELL;
		}
	}

	/**
	 * Creates the lower wall of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the lower wall of the specified cell
	 */
	private String createLowerWall(Maze maze, int row, int col) {
		if (maze.hasLowerWallAt(row, col)) {
			return HORIZONTAL_WALL;
		} else {
			return EMPTY_CELL;
		}
	}

	/**
	 * Creates the left wall of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the left wall of the specified cell
	 */
	private String createLeftWall(Maze maze, int row, int col) {
		if (maze.hasLeftWallAt(row, col)) {
			return VERTICAL_WALL;
		} else {
			return NO_WALL;
		}
	}

	/**
	 * Creates the right wall of the specified maze cell.
	 *
	 * @param maze the maze
	 * @param row  the row
	 * @param col  the column
	 * @return the string representing the right wall of the specified cell
	 */
	private String createRightWall(Maze maze, int row, int col) {
		if (maze.hasRightWallAt(row, col)) {
			return VERTICAL_WALL;
		} else {
			return NO_WALL;
		}
	}

	/**
	 * Marks the goal position in the display
	 */
	private void markGoalPosition(Maze maze) {
		Position goal = maze.getGoalPosition();
		int rowIndex = 2 * goal.getRow() + 1;
		int colIndex = 3 * goal.getColumn() + 1;
		this.mazeDisplay[rowIndex] = this.mazeDisplay[rowIndex].substring(0, colIndex) + "**"
				+ this.mazeDisplay[rowIndex].substring(colIndex + 2);
	}

	/**
	 * Marks the current position in the display
	 */
	private void markCurrentPosition() {
		int rowIndex = 2 * this.curPos.getRow() + 1;
		int colIndex = 3 * this.curPos.getColumn() + 1;
		this.mazeDisplay[rowIndex] = this.mazeDisplay[rowIndex].substring(0, colIndex) + "<>"
				+ this.mazeDisplay[rowIndex].substring(colIndex + 2);
	}

	/**
	 * Removes the current position in the display
	 */
	private void removeCurrentPosition() {
		int rowIndex = 2 * this.curPos.getRow() + 1;
		int colIndex = 3 * this.curPos.getColumn() + 1;
		this.mazeDisplay[rowIndex] = this.mazeDisplay[rowIndex].substring(0, colIndex) + EMPTY_CELL
				+ this.mazeDisplay[rowIndex].substring(colIndex + 2);
	}
}
