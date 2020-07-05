package edu.westga.cs1302.casino;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * CasinoApplication extends the JavaFX Application class to build the GUI and
 * start program execution.
 * 
 * @author CS 1302
 */
public class CasinoApplication extends Application {

	private static final String GUI_FXML = "view/Gui.fxml";
	private static final String TITLE = "Lab 6 by Daniel Crumpler";

	/**
	 * Constructs a new Application object for the CasinoApplication program.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public CasinoApplication() {
		super();
	}

	/**
	 * Starts the application and sets the main stage.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param primaryStage The main stage to set for the application.
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = this.loadGui();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(TITLE);
			primaryStage.setAlwaysOnTop(true);

			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}

	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(GUI_FXML));
		return (Pane) loader.load();
	}

	/**
	 * Launches the application.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}