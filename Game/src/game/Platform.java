package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform extends ImageView{
	private int destruction;
	
	
	Platform(int x, int y, int c, int r, int w, int h, Image path, int destruction){
		super(path);
		setTranslateX(x);
		setTranslateY(y);
		this.destruction = destruction;
		Rectangle2D viewportRect = new Rectangle2D(c,r,w,h);
		this.setViewport(viewportRect);
	}
	
	

	public int getDestruction() {
		return this.destruction;
	}
	
	public void reduceDestruction() {
		this.destruction = this.destruction - 1 ;
	}
}
