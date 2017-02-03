package controller;
	
import Model.SoundDetails;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

	private static SoundDetails soundDetails;
	
	public static void main(String[] args) {
		
		soundDetails = new SoundDetails();
		Thread soundDetailsThread = new Thread(soundDetails);
		soundDetailsThread.start();
		StreamManager streamManager = new StreamManager(soundDetails,"LRMonoPhase4.wav");
		streamManager.openStreamsAndStartAudioPlayer();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Label label = new Label();
			StackPane root = new StackPane();
			root.getChildren().add(label);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
