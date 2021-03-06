import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PlayerPawn implements Pawn {
	ImageView iv = new ImageView();
	boolean shouldBeDeleted = false;
	LinkedList<Pawn> enemiesInScene;
	
	public PlayerPawn(Image img, int posX, int posY, LinkedList<Pawn> enemiesInScene) {
		iv.setImage(img);

		iv.setX(posX);
		iv.setY(posY);
		
		this.enemiesInScene = enemiesInScene;
	}

	@Override
	public ImageView getImageView() {
		return iv;
	}

	@Override
	public void update() {
		for(Pawn enemy : enemiesInScene){
			if(!enemy.getImageView().equals(iv))
				if(iv.getBoundsInParent().intersects(enemy.getImageView().getBoundsInParent())){
					markForDeletion();
				}
		}
		
		
	}

	@Override
	public boolean shouldBeDeleted() {
		// TODO Auto-generated method stub
		return shouldBeDeleted;
	}

	@Override
	public void markForDeletion() {
		shouldBeDeleted = true;
	}

}
