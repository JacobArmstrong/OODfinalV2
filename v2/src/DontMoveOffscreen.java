import javafx.scene.image.ImageView;

public class DontMoveOffscreen extends PawnDecorator{
	
	ImageView iv = getImageView();
	int width = 1600;
	boolean move;
	
	public DontMoveOffscreen(Pawn decoratedPawn) {
		super(decoratedPawn);
	}

	@Override
	void doBehavior() {
		if(iv.getX() < 0 - iv.getImage().getWidth()){
			iv.setX(0);
		}
		else if(iv.getX() > width - iv.getImage().getWidth()){
			iv.setX(width - iv.getImage().getWidth());
		}
	}
}