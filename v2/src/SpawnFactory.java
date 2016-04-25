import java.util.LinkedList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpawnFactory {
	private static SpawnFactory factory;
	//Time to spawn is the amount of time between spawns
	private int timeToSpawn = 150, count = timeToSpawn;
	
	//Random number generator for choosing what and where to spawn
	private Random rand = new Random();
	
	//The game's pane
	Pane canvas;
	
	//The game's list of pawns
	LinkedList<Pawn> pawnsInScene;
	
	private SpawnFactory(Pane canvas, LinkedList<Pawn> pawnsInScene, PlayerControl input){
		this.canvas = canvas;
		this.pawnsInScene = pawnsInScene;
		
		Image basePlayerImage = new Image("sprites/Base_Player.png");
		Pawn player = new PlayerPawn(basePlayerImage, 800, 750, pawnsInScene);
		player = new DontMoveOffscreen(player);
		player = new MoveLeftOnA(player);
		input.addObserver((MoveLeftOnA)player);
		player = new MoveRightOnD(player);
		input.addObserver((MoveRightOnD)player);
		player = new JumpOnSpace(player, 5);
		input.addObserver((JumpOnSpace)player);
		
		canvas.getChildren().add(player.getImageView());
		pawnsInScene.add(player);
	}
	
	public static SpawnFactory getFactory(Pane canvas, LinkedList<Pawn> pawnsInScene, PlayerControl input){
		if(factory == null)
			factory = new SpawnFactory(canvas, pawnsInScene, input);
	
		return factory;
	}
	
	public void update(){
		//each update, decrement count
		count--;
		
		//if the count has ran out, spawn in a new enemy
		if(count <= 0){
			//Decide the enemy to spawn
			/*-!!- the number passed into rand.nextInt is the number of different types of enemies -!!-*/
			int enemyToSpawn = rand.nextInt(2);
			
			/*decide which side of the sceen to spawn the enemy
				0 = left
				1 = right
			*/
			int side = rand.nextInt(2);
			
			Pawn enemy = null;
			//use a switch statement to decide what enemy to spawn
			switch(enemyToSpawn){
				case 0: enemy = spawnWalker(side);
					break;
				case 1: enemy = spawnJumper(side);
					break;
			}
			
			canvas.getChildren().add(enemy.getImageView());
			pawnsInScene.add(enemy);
			
			count = timeToSpawn;
		}
	}
	
	private Pawn spawnWalker(int side){
		String monType = null;
		switch(rand.nextInt(3)){
		case 0: monType = "Green";
			break;
		case 1: monType = "Gross";
			break;
		case 2: monType = "Purple";
			break;
		}
		
		Image walk1 = new Image("sprites/Walking_1_"+monType+".png");
		Image walk2 = new Image("sprites/Walking_2_"+monType+".png");
		Image[] walkAnimation = {walk1, walk2};
		Pawn walker;
		
		//if spawning on left side
		if(side == 0){
			walker = new ConcreteTestPawn(walk1, -200, 750);
			//walker.getImageView().setScaleX(-1);
		}
		//otherwise we spawn on right side
		else{
			walker = new ConcreteTestPawn(walk1, 1800, 750);
			walker.getImageView().setScaleX(-1);
		}
			
		
		//add decorators
		walker = new HorizontalMovement(walker, 3, side);
		walker = new Animate(walker, walkAnimation);
		walker = new DeleteWhenOffscreen(walker, canvas);
		
		return walker;
	}
	
	private Pawn spawnJumper(int side){
		String monType = null;
		switch(rand.nextInt(3)){
		case 0: monType = "Green";
			break;
		case 1: monType = "Gross";
			break;
		case 2: monType = "Purple";
			break;
		}
		
		Image onGround = new Image("sprites/Slime_Squashed_"+monType+".png");
		Image jumpingUp = new Image("sprites/Slime_Up_"+monType+".png");
		Image fallingDown = new Image("sprites/Slime_Down_"+monType+".png");
		Image[] animation = {onGround, jumpingUp, fallingDown};
		
		Pawn jumper;
		
		if(side == 0)
			jumper = new ConcreteTestPawn(onGround, -200, 750);
		else{
			jumper = new ConcreteTestPawn(onGround, 1800, 750);
			jumper.getImageView().setScaleX(-1);
		}
			
		

		//jumper = new HorizontalMovement(jumper, 2, side);
		//jumper = new JumpAction(jumper, 5);
		jumper = new Bounce(jumper, animation, 2, side);
		jumper = new DeleteWhenOffscreen(jumper, canvas);
		
		return jumper;
	}
}