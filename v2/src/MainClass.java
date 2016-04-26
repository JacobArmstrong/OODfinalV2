import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//This is the class used to launch the application
//DO NOT do any testing here
//If you want to test something, do it in the test class "Tset.java"
public class MainClass extends Application {
	PlayerControl input = new PlayerControl();
	
	public static void main(String[] args) {
		//Launch the application
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Creating a scene and canvas for the application
		Group root = new Group();
		Scene scene = new Scene(root);
		scene.setFill(Color.GREY);
		final Pane canvas = new Pane();
		
		//adding the canvas to the scene 
		root.getChildren().add(canvas);

		//Setting up the stage
		stage.setWidth(1600);
		stage.setHeight(900);
		//adding the scene to the stage
		stage.setScene(scene);

		//Setting up input
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				input.update(e);
			}
		});
		
		//Initialize a list of pawns
		final LinkedList<Pawn> pawnsInScene = new LinkedList<Pawn>();
		//Initialize a list of pawns that are marked for deletion
		final LinkedList<Pawn> pawnsToDelete = new LinkedList<Pawn>();
		
		//Create a spawner
		final SpawnFactory factory = SpawnFactory.getFactory(canvas, pawnsInScene, input);
		
		//TODO: Create player and add them to the canvas and the pawnsInScene list
		
		
		//showing the stage
		stage.show();
		
		
		//Create an infinite loop
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				//Call update in each of the pawns in the scene
				for(Pawn pawn : pawnsInScene){
					pawn.update();
					//if a pawn has been marked for deletion, add it to the deletion list
					if(pawn.shouldBeDeleted())
						pawnsToDelete.add(pawn);
				}
				
				//Remove every pawn that is marked for deletion from the scene
				for(Pawn doomedPawn : pawnsToDelete){
					canvas.getChildren().remove(doomedPawn.getImageView());
					pawnsInScene.remove(doomedPawn);
				}

				//Clear the list of pawns to be deleted
				pawnsToDelete.clear();
				
				//Update the spawn factory
				factory.update();
			}
		};

		gameLoop.start();
	}
}
