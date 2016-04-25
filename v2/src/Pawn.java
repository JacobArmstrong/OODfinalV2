import javafx.scene.image.ImageView;

public interface Pawn {
	public int getX();

	public int getY();

	public void setX(int newX);

	public void setY(int newY);

	public ImageView getImageView();
	
	public void update();
	
	public boolean shouldBeDeleted();
	
	abstract void markForDeletion();
}
