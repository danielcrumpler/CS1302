package edu.westga.cs1302.mazesearch.test.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.mazesearch.datareader.MazeFileReader;
import edu.westga.cs1302.mazesearch.game.CommandType;
import edu.westga.cs1302.mazesearch.game.MazeGameData;
import edu.westga.cs1302.mazesearch.game.MoveDownCommand;

public class TestConstructorMoveDownCommand {

	@Test
	public void testValidCreation() {

		MazeFileReader reader = new MazeFileReader("maze.txt");
		MazeGameData data = new MazeGameData(reader.getMaze());
		MoveDownCommand command = new MoveDownCommand(data);
		assertAll(() -> assertEquals(command.getMazeGameData(), data),
				() -> assertEquals("J|j - move down", command.getUsage()),
				() -> assertEquals(CommandType.MOVE_DOWN, command.getCommandType()));

	}

}
