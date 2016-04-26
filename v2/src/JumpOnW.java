import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

class JumpOnW extends PawnDecorator implements Observer {
	KeyCode input;
	boolean move;
	private int yVelocity = 2, state = 0;;
	ImageView iv = getImageView();
	public int height = 900;
	public int count;
	
	public JumpOnW(Pawn decoratedPawn, int speed) {
		super(decoratedPawn);
		yVelocity = speed;
		
		
	}

	public void update(Observable arg0, Object arg1) {
		//Get the input
		input = (KeyCode)arg1;
		//If input is A set move to be true
		if(input.toString().compareTo("W") == 0){
			move = true;
		}
	}
	
	public void doBehavior() {
		
		if(move) {
			iv = getImageView();
			//count++;
			//Start Jumping up
			if(state == 0 && iv.getY() >= 200) {
				iv.setY(iv.getY() - yVelocity);
				if(iv.getY() == 200) {
					state = 1;
				}
			}
			//Start falling
			else if(state == 1 && iv.getY() < 750) {
				iv.setY(iv.getY() + yVelocity);
			} 
			//Reset Values
			else {
				move = false;
				state=0;
				//count = 0;
			}
		}
	}
}
