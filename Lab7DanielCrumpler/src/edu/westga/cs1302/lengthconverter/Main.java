package edu.westga.cs1302.lengthconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Entry point for the application.
 * 
 * @author CS 1302
 */
public class Main extends Application {

	private static final String WINDOW_TITLE = "Lab 7 by Daniel Crumpler";

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/Gui.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
		} catch (Exception anException) {
			anException.printStackTrace();
		}
	}

	/**
	 * Launches the application.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
