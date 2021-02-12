package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle{
	
	Platform(int x, int y, int w, int h, Color color){
		super(w, h, color);
		setTranslateX(x);
		setTranslateY(y);
	}
}
