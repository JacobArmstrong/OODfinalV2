import java.util.Observable;
import java.util.Observer;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerControl extends Observable  {
	KeyCode input;
	
	void update(KeyEvent e){
		input=e.getCode();
		setChanged();
		notifyObservers(input);
	}

}//end of playercontrol

/* Example Observer class
class Observers implements Observer{
	KeyCode input;
	@Override
	public void update(Observable arg0, Object e) {
		input=(KeyCode)e;
		System.out.println(input);
	}
	
}
*/

