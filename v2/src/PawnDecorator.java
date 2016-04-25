import javafx.scene.image.ImageView;

public abstract class PawnDecorator implements Pawn {
	Pawn decoratedPawn;

	public PawnDecorator(Pawn decoratedPawn) {
		this.decoratedPawn = decoratedPawn;
	}

	@Override
	public int getX() {
		return decoratedPawn.getX();
	}

	@Override
	public int getY() {
		return decoratedPawn.getY();
	}

	@Override
	public void setX(int newX) {
		decoratedPawn.setX(newX);
	}

	@Override
	public void setY(int newY) {
		decoratedPawn.setY(newY);
	}

	@Override
	public ImageView getImageView() {
		return decoratedPawn.getImageView();
	}
	
	public boolean shouldBeDeleted(){
		return decoratedPawn.shouldBeDeleted();
	}
	
	public void markForDeletion(){
		decoratedPawn.markForDeletion();
	}

	public void update() {
		doBehavior();

		decoratedPawn.update();
	}

	abstract void doBehavior();
}
