import javafx.scene.image.ImageView;

public class PartialCircle extends PawnDecorator{
	private double radius = 200;
	private int rotation = 180, degree = 270;
	public int width = 1600, height = 900, state = 0, counter = 0;
	
	
	public PartialCircle(Pawn decoratedShape, int rotation, int degree)
	{
		super(decoratedShape);
		this.rotation = rotation;
		this.degree = degree;
	}
	
	@Override
	public void doBehavior()
	{
		ImageView iv = super.getImageView();
		if(state == 0)
		{
			iv.setX(800 + (radius * Math.cos(Math.toRadians(degree))));
			iv.setY(200 + (radius * Math.sin(Math.toRadians(degree))));
			counter += 2;
			degree += 2;
		}
		if(state == 1)
		{
			iv.setX(800 + (radius * Math.cos(Math.toRadians(degree))));
			iv.setY(200 + (radius * Math.sin(Math.toRadians(degree))));
			counter -= 2;
			degree -= 2;
		}
		if(counter >= rotation)
		{
			state = 1;
		}
		if(counter <= 0)
		{
			state = 0;
		}
		
	}
}
