import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class DiagonalMovement extends PawnDecorator{
	private int xAcceleration = 1, yAcceleration = 1;
	public int width = 1600, height = 900, state = 0;
	
	public DiagonalMovement(Pawn decoratedShape, int xAcceleration, int yAcceleration)
	{
		super(decoratedShape);
		this.xAcceleration = xAcceleration;
		this.yAcceleration = yAcceleration;
	}
	
	@Override
	public void doBehavior()
	{
		ImageView iv = super.getImageView();
		Image img = iv.getImage();
		if(iv.getY() >= height - img.getHeight() || iv.getX() >= width - img.getWidth())
		{
			state = 1;
		}
		if(iv.getY() <= 0 || iv.getX() <= 0)
		{
			state = 0;
		}
		if(state == 1)
		{
			iv.setX(iv.getX() - xAcceleration);
			iv.setY(iv.getY() - yAcceleration);
		}
		if(state == 0)
		{
			iv.setX(iv.getX() + xAcceleration);
			iv.setY(iv.getY() + yAcceleration);
		}
		
	}
}
