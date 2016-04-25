import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class DeleteWhenOffscreen extends PawnDecorator {
	Pane canvas;
	ImageView iv;
	
	boolean hasBeenInScene = false;

	public DeleteWhenOffscreen(Pawn decoratedPawn, Pane canvas) {
		super(decoratedPawn);
		
		this.canvas = canvas;
		
		iv = getImageView();
	}

	@Override
	void doBehavior() {
		//check to see if the pawn has left the scene
		if(iv.getX()+iv.getFitWidth()<0){
			markForDeletion();
			//canvas.getChildren().remove(decoratedPawn.getImageView());
		}
		else if(iv.getX()>1600){
			markForDeletion();
			//canvas.getChildren().remove(decoratedPawn.getImageView());
		}
	}

}
