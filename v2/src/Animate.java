import javafx.scene.image.Image;

public class Animate extends PawnDecorator {
	Image[] animSet;
	int animIndex = 0;
	int animSpeed=15;
	int counter=0;
	
	public Animate(Pawn decoratedPawn, Image[] animSet) {
		super(decoratedPawn);
		
		this.animSet = animSet;
	}

	@Override
	void doBehavior() {
		counter++;
		
		if(counter >= animSpeed){
			animIndex++;
			
			if(animIndex >= animSet.length)
				animIndex = 0;
			
			getImageView().setImage(animSet[animIndex]);
			counter=0;
		}
	}
}