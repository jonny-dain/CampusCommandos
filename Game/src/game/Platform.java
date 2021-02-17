package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle{
	private int destruction;
	
	
	Platform(int x, int y, int w, int h, Color color, int destruction){
		super(w, h, color);
		setTranslateX(x);
		setTranslateY(y);
		this.destruction = destruction;
	}
	
	

	public int getDestruction() {
		return this.destruction;
	}
	
	public void reduceDestruction() {
		this.destruction = this.destruction - 1 ;
	}
}
