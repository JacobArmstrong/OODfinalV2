import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

class MoveLeftOnA extends PawnDecorator implements Observer {
//A keycode taken from the observable class for user input
	KeyCode input;
	//Should the image move on doBehavior()?
	boolean move;
	
	public MoveLeftOnA(Pawn decoratedPawn) {
		super(decoratedPawn);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//Get the input
		input = (KeyCode)arg1;
		
		//If input is A set move to be true
		if(input.toString().compareTo("A") == 0){
			move = true;
		}
	}
	
	void doBehavior() {
		//When move is true, move the image some amount to the right
		if(move){
			ImageView iv = getImageView();
			iv.setX(iv.getX() - 8);
			move = false;
			
			if(iv.getScaleX()>0)
				iv.setScaleX(-1);
		}
	}
}