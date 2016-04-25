import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class HorizontalMovement extends PawnDecorator{
	private int xAcceleration = 1, counter, state = 0;
	public int width = 1600, height = 900;
	ImageView iv;
	
	//state determines the direction the object moves. 0 is for left and 1 is for right.
	public HorizontalMovement(Pawn decoratedShape, int speed, int state)
	{
		super(decoratedShape);
		iv = super.getImageView();
		xAcceleration = speed;
		if(state == 1)
		{
			this.state = state;
			iv.setX(0);
		}
		else{
			this.state = 0;
			iv.setX(width - iv.getImage().getWidth());
		}
	}
	
	@Override
	public void doBehavior()
	{
		if(iv.getImage() == null)
		{
			return;
		}
		if(state == 1)
		{
			iv.setX(iv.getX() + xAcceleration);
		}
		if(state == 0)
		{
			iv.setX(iv.getX() - xAcceleration);
		}
		if(iv.getX() < 0 - iv.getImage().getWidth() || iv.getX() > width)
		{
			iv.setImage(null);
		}
	}
}