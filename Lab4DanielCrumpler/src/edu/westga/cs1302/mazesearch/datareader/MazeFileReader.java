package edu.westga.cs1302.mazesearch.datareader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.westga.cs1302.mazesearch.game.Maze;
import edu.westga.cs1302.mazesearch.resources.ExceptionMessages;
import edu.westga.cs1302.mazesearch.util.Position;

/**
 * The Class MazeFileReader.
 * 
 * @author CS1302
 */
public class MazeFileReader {
	
	private String filename;
	private int[][] mazeCells;
	private Position start;
	private Position goal;

	/**
	 * Instantiates a new maze file reader.
	 * 
	 * The passed in file with the maze data must have the following format: <number
	 * rows r> <number columns c> <row of start position> <column of start position>
	 * <row of goal position> <column of goal position> <cell(0, 0)> <cell(0, 0)>
	 * ... <cell(0, c-1)> <cell(1, 0)> <cell(1, 0)> ... <cell(1, c-1)> ...
	 * <cell(r-1, 0)> <cell(r-1, 0)> ... <cell(r-1, c-1)>
	 * 
	 * Rows are numbered from 0,..., r-1 Columns are numbered from 0,..., c-1
	 * cell(i,j) is a 4-bit representation of the 4 walls of the cell 
	 *           in row i and column j:
	 *    Bit representing 1 is on: cell has left wall
	 *    Bit representing 2 is on: cell has upper wall
	 *    Bit representing 4 is on: cell has right wall
	 *    Bit representing 8 is on: cell has lower wall
	 * 
	 * @precondition filename != null postcondition
	 * @postcondition mazeCell, start, goal contain the maze data read from file
	 * 
	 * @param filename the name of the file containing the maze
	 */
	public MazeFileReader(String filename) {
		this.setFieldsToNull();
		if (filename == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_FILENAME);
		}
		this.filename = filename;
		this.readMaze();
	}
	
	private void setFieldsToNull() {
		this.mazeCells = null;
		this.start = null;
		this.goal = null;
	}
	
	/**
	 * Returns the maze read from file
	 * 
	 * @precondition none 
	 * @postcondition none
	 * 
	 * @return the maze read from file, null if the maze could not be read from file
	 */
	public Maze getMaze() {
		if (this.mazeCells == null) {
			return null;
		}
		return new Maze(this.mazeCells, this.start, this.goal);
	}
	
	/**
	 * Reads the maze from the file.
	 */
	private void readMaze() {
		File mazeFile = new File(this.filename);
		try (Scanner mazeScanner = new Scanner(mazeFile)) {
			this.readDimension(mazeScanner);
			this.readStartPosition(mazeScanner);
			this.readGoalPosition(mazeScanner);
			this.readCells(mazeScanner);
		} catch (IOException e) {
			this.setFieldsToNull();
			System.err.println("Cannot open maze file " + this.filename);
		} catch (Exception e) {
			this.setFieldsToNull();
			System.err.println("Invalid maze file data in " + this.filename);
		} 
	}
	
	/**
	 * Reads the maze from the file.
	 */
	private void readDimension(Scanner mazeScanner) {
		String line = mazeScanner.nextLine();
		int numRows = 0;
		int numCols = 0;
		try (Scanner lineScanner = new Scanner(line)) {
			numRows = lineScanner.nextInt();
			numCols = lineScanner.nextInt();
		}
		this.mazeCells = new int[numRows][numCols];
	}
	
	/**
	 * Reads the start position from the file.
	 */
	private void readStartPosition(Scanner mazeScanner) {
		String line = mazeScanner.nextLine();
		int startPosRow = 0;
		int startPosCol = 0;
		try (Scanner lineScanner = new Scanner(line)) {
			startPosRow = lineScanner.nextInt();
			startPosCol = lineScanner.nextInt();
		}
		this.start = new Position(startPosRow, startPosCol);
	}
	
	/**
	 * Reads the goal position from the file.
	 */
	private void readGoalPosition(Scanner mazeScanner) {
		String line = mazeScanner.nextLine();
		int goalPosRow = 0;
		int goalPosCol = 0;
		try (Scanner lineScanner = new Scanner(line)) {
			goalPosRow = lineScanner.nextInt();
			goalPosCol = lineScanner.nextInt();
		}
		this.goal = new Position(goalPosRow, goalPosCol);
	}
	
	/**
	 * Reads the cells of the maze from the file.
	 */
	private void readCells(Scanner mazeScanner) {
		int row = 0;
		int numRows = this.mazeCells.length;
		int numCols = this.mazeCells[0].length;
		while (row < numRows) {
			String rowData = mazeScanner.nextLine();
			try (Scanner rowScanner = new Scanner(rowData)) {
				int col = 0;
				while (col < numCols) {
					int cell = rowScanner.nextInt();
					this.mazeCells[row][col] += cell;
					col++;
				}
			}
			row++;
		}
	}
}
