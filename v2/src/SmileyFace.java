import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SmileyFace implements Pawn{
	Image img = new Image("smiley.png");
	ImageView iv = new ImageView(img);
	
	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public void setX(int newX) {
	}

	@Override
	public void setY(int newY) {
	}

	@Override
	public ImageView getImageView() {
		return iv;
	}
}