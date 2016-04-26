import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BaseEnemy implements Pawn {
	ImageView iv = new ImageView();
	
	boolean shouldBeDeleted = false;

	public BaseEnemy(Image img, int posX, int posY) {
		iv.setImage(img);

		iv.setX(posX);
		iv.setY(posY);
		
		iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         markForDeletion();
		     }
		});

	}
	
	@Override
	public ImageView getImageView() {
		return iv;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean shouldBeDeleted() {
		return shouldBeDeleted;
	}

	@Override
	public void markForDeletion() {
		shouldBeDeleted = true;
	}

}
