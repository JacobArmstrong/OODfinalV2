import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Tset extends Application  {
	PlayerControl PlayerController=new PlayerControl();
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Image img = new Image("smiley.png");
		
		//PlayerController.addObserver(new Observers());
		
		
		

		// final ImageView iv = new ImageView();
		// iv.setImage(img);
		// iv.setX(0);
		// iv.setY(0);

		Group root = new Group();
		Scene scene = new Scene(root);
		scene.setFill(Color.BLACK);
		Pane canvas = new Pane();
		
		//Get Key pressed, send to Observable
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent e) {
				PlayerController.update(e);
				//System.out.print(e.getCode());
				
			}
			
		});

		// canvas.getChildren().add(iv);
		root.getChildren().add(canvas);

		stage.setWidth(1600);
		stage.setHeight(900);
		stage.setScene(scene);

		stage.show();

		ConcreteTestPawn myTestPawn = new ConcreteTestPawn(img, 800, 450);
		canvas.getChildren().add(myTestPawn.getImageView());
		
		JumpOnSpace move = new JumpOnSpace(myTestPawn, 5);
		
		PlayerController.addObserver(move);

		AnimationTimer gameLoop = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				move.update();
			}

		};

		gameLoop.start();
	}

	
}