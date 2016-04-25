import javafx.scene.image.ImageView;

public class FullCircle extends PawnDecorator{
	private double radius = 200;
	private int degree = 0, x = 0, y = 0;
	public int width = 1600, height = 900, state = 0, counter = 0;
	
	
	public FullCircle(Pawn decoratedShape, int x, int y)
	{
		super(decoratedShape);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void doBehavior()
	{
		ImageView iv = super.getImageView();
		iv.setX(x + (radius * Math.cos(Math.toRadians(degree))));
		iv.setY(y + (radius * Math.sin(Math.toRadians(degree))));
		degree++;
		
	}
}
