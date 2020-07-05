package edu.westga.cs1302.mazesearch.test.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.mazesearch.datareader.MazeFileReader;
import edu.westga.cs1302.mazesearch.game.HelpCommand;
import edu.westga.cs1302.mazesearch.game.MazeGameData;

public class TestHelpCommandGetUsage {

	@Test
	public void testGetUsageOfHelpCommand() {
		MazeFileReader reader = new MazeFileReader("maze.txt");
		MazeGameData data = new MazeGameData(reader.getMaze());
		HelpCommand command = new HelpCommand(data);
		String usageMessage = "E|e - help (displays possible moves)";
		assertEquals(usageMessage, command.getUsage());
	}

}
