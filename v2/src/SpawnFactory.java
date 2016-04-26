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
		
		//Create a player and add them to the scene
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
	
	//Implementation of the singleton pattern
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
			
			/*decide which side of the screen to spawn the enemy
				0 = left
				1 = right
			*/
			int side = rand.nextInt(2);
			
			//Initialize an enemy
			Pawn enemy = null;
			//use a switch statement to decide what enemy to spawn
			switch(enemyToSpawn){
				case 0: enemy = spawnWalker(side);
					break;
				case 1: enemy = spawnJumper(side);
					break;
			}
			
			//Add the newly created enemy to the scene
			canvas.getChildren().add(enemy.getImageView());
			pawnsInScene.add(enemy);
			
			//reset the count
			count = timeToSpawn;
		}
	}
	
	//Spawning a walker type enemy
	private Pawn spawnWalker(int side){
		//Randomly select the color of the new walker
		String monType = null;
		switch(rand.nextInt(3)){
		case 0: monType = "Green";
			break;
		case 1: monType = "Gross";
			break;
		case 2: monType = "Purple";
			break;
		}
		
		//Create images used for the walker's animation
		Image walk1 = new Image("sprites/Walking_1_"+monType+".png");
		Image walk2 = new Image("sprites/Walking_2_"+monType+".png");
		Image[] walkAnimation = {walk1, walk2};
		
		//Initialize the new walker
		Pawn walker;
		
		//if spawning on left side
		if(side == 0){
			walker = new BaseEnemy(walk1, -200, 750);
		}
		//otherwise we spawn on right side
		else{
			walker = new BaseEnemy(walk1, 1800, 750);
			walker.getImageView().setScaleX(-1);
		}
			
		//add decorators to the walker
		walker = new HorizontalMovement(walker, 3, side);
		walker = new Animate(walker, walkAnimation);
		walker = new DeleteWhenOffscreen(walker, canvas);
		
		return walker;
	}
	
	//Spawning a jumper type enemy
	private Pawn spawnJumper(int side){
		//Randomly select the color of the new jumper
		String monType = null;
		switch(rand.nextInt(3)){
		case 0: monType = "Green";
			break;
		case 1: monType = "Gross";
			break;
		case 2: monType = "Purple";
			break;
		}
		
		//Create images used for the jumper's animation
		Image onGround = new Image("sprites/Slime_Squashed_"+monType+".png");
		Image jumpingUp = new Image("sprites/Slime_Up_"+monType+".png");
		Image fallingDown = new Image("sprites/Slime_Down_"+monType+".png");
		Image[] animation = {onGround, jumpingUp, fallingDown};
		
		Pawn jumper;
		
		//Create images used for the jumper's animation
		if(side == 0)
			jumper = new BaseEnemy(onGround, -200, 750);
		//otherwise we spawn on right side
		else{
			jumper = new BaseEnemy(onGround, 1800, 750);
			jumper.getImageView().setScaleX(-1);
		}
		
		
		//add decorators to the jumper	
		jumper = new Bounce(jumper, animation, 2, side);
		jumper = new DeleteWhenOffscreen(jumper, canvas);
		
		return jumper;
	}
}