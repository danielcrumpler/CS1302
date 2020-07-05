package edu.westga.cs1302.mazesearch.test.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.mazesearch.datareader.MazeFileReader;
import edu.westga.cs1302.mazesearch.game.MazeGameData;
import edu.westga.cs1302.mazesearch.game.QuitCommand;

public class TestQuitCommandGetUsage {

	@Test
	public void testGetUsageOfQuitCommand() {
		MazeFileReader reader = new MazeFileReader("maze.txt");
		MazeGameData data = new MazeGameData(reader.getMaze());
		QuitCommand command = new QuitCommand(data);
		String usageMessage = "Q|q - quit (requires confirmation)";
		assertEquals(usageMessage, command.getUsage());
	}

}
