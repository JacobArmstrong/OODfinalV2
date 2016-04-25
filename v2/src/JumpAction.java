import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Random;

public class JumpAction extends PawnDecorator {

	private int yAcceleration = 3;
	public int count;
	public Random randomGenerator = new Random();
	int n = 300;
	int z = randomGenerator.nextInt(700 - 300)+n;
	public JumpAction(Pawn decoratedShape, int speed) {

		super(decoratedShape);
		this.yAcceleration = speed;
		count = 0;
	}

	public void doBehavior() {

		ImageView iv = getImageView();
		count = count + 1;
		
		//Start Jumping up
		if(count >= 0+z && count < 120+z) {
			iv.setY(iv.getY() - 5);
		}
		//Start falling
		else if(count > 120+z && count <= 240+z) {
			iv.setY(iv.getY() + 5);
		} 
		//Reset Values
		else if(count > 240+z) {
			count = 0;
			z = randomGenerator.nextInt(700 - 300)+n;
		}

	}

}
