package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Extending rectangle for simplicity. Better if extended ImageView.
public class Sprite extends Rectangle {
	
	public final String TYPE;
	
	Sprite(int x, int y, int width, int height, String type, Color color){
		super(width, height, color);
		TYPE = type;
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public String getType() {
		return TYPE;
	}
	
	//TO-DO destructor

}
	
