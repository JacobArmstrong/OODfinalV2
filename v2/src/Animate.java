import javafx.scene.image.Image;

public class Animate extends PawnDecorator {
	//Holds the images used in an animation
	Image[] animSet;
	//The index of the current animation inside animSet
	int animIndex = 0;
	//The time it takes for the next image
	int animSpeed=15;
	//A counter used to keep track of time
	int counter=0;
	
	public Animate(Pawn decoratedPawn, Image[] animSet) {
		super(decoratedPawn);
		
		//Initialize the animation set
		this.animSet = animSet;
	}

	@Override
	void doBehavior() {
		//Increment the counter
		counter++;
		
		//if the counter as reached animSpeed...
		if(counter >= animSpeed){
			//Increment the animation index
			animIndex++;
			
			//Have the animation index reach around if need be
			if(animIndex >= animSet.length)
				animIndex = 0;
			
			//Update the imageview
			getImageView().setImage(animSet[animIndex]);
			
			//Reset the counter
			counter=0;
		}
	}
}