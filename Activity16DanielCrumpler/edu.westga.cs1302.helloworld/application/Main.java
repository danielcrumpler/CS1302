package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


public class Main {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Gui.fxml"));
			Pane pane = loader.load();
			Label label = new Label("Enter your name:");
			TextField textfield = new TextField();
			FlowPane pane = new FlowPane(label, textfield);
			Scene scene = new Scene(pane, 200, 200);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CS1302");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
