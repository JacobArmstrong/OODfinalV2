import javafx.scene.image.ImageView;

public interface Pawn {
	public ImageView getImageView();
	
	public void update();
	
	public boolean shouldBeDeleted();
	
	abstract void markForDeletion();
}
