import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bounce extends PawnDecorator {
	Image[] animationSet;
	//animation set should be as follows:
	// 0 = sitting on the ground
	// 1 = jumping up
	// 2 = falling
	
	HorizontalMovement horizontalAction;
	
	ImageView iv;
	
	int counter = 0;
	
	public Bounce(Pawn decoratedPawn, Image[] animation, int speed, int state) {
		super(decoratedPawn);
		animationSet = animation;
		horizontalAction = new HorizontalMovement(decoratedPawn, speed, state);
		
		iv = getImageView();
	}

	@Override
	void doBehavior() {
		counter++;
		if(counter <= 100){
			//Just wait on the ground for a while
		}
		else if(counter <= 150){
			//Jump up and move horizontally
			iv.setY(iv.getY() - 5);
			horizontalAction.doBehavior();
			iv.setImage(animationSet[1]);
		}
		else if(counter <= 200){
			iv.setY(iv.getY() + 5);
			horizontalAction.doBehavior();
			iv.setImage(animationSet[2]);
		}
		else{
			counter = 0;
			iv.setImage(animationSet[0]);
		}
	}
}
