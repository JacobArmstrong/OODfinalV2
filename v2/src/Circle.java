import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Circle implements Pawn{
	Image img = new Image("circle.png");
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
		iv.setX(newX);
	}

	@Override
	public void setY(int newY) {
		iv.setY(newY);
	}

	@Override
	public ImageView getImageView() {
		return iv;
	}
}
