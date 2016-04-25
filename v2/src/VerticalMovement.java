import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class VerticalMovement extends PawnDecorator{
	private int yAcceleration = 1, state = 0;
	public int width = 1600, height = 900;
	ImageView iv;
	
	//state determines the direction the object moves. 0 is for up and 1 is for down.
	public VerticalMovement(Pawn decoratedShape, int speed, int state)
	{
		super(decoratedShape);
		iv = super.getImageView();
		yAcceleration = speed;
		if(state == 1)
		{
			this.state = state;
			iv.setY(0);
		}
		else{
			this.state = 0;
			iv.setY(height - iv.getImage().getHeight());
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
			iv.setY(iv.getY() + yAcceleration);
		}
		if(state == 0)
		{
			iv.setY(iv.getY() - yAcceleration);
		}
		if(iv.getY() < 0 - iv.getImage().getHeight() || iv.getY() > height)
		{
			iv.setImage(null);
		}
	}
}
