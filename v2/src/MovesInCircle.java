import javafx.scene.image.ImageView;

public class MovesInCircle extends PawnDecorator {
	double radius;
	int counter;
	
	public MovesInCircle(Pawn decoratedPawn, double radius) {
		super(decoratedPawn);
		this.radius = radius;
		counter = 0;
	}

	@Override
	void doBehavior() {
		ImageView iv = getImageView();

		iv.setX(radius * Math.cos(Math.toRadians(counter)));
		iv.setY(radius * Math.sin(Math.toRadians(counter)));
		counter++;

		if (counter >= 360)
			counter = 0;
	}

}
