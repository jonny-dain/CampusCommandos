package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Extending rectangle for simplicity. Better if extended ImageView.
public class Sprite extends Rectangle{
		boolean isDead = false;
		boolean canJump = true;
		boolean canFall = true;
		
		//Type. Eg: player, enemy etc.
		final String type;
		
		//Constructor
		Sprite(int x, int y, int w, int h, String type, Color color){
			super(w, h, color);
			
			this.type=type;
			setTranslateX(x);
			setTranslateY(y);
			
		}
		
		void moveLeft() {
			setTranslateX(getTranslateX() - 5);
		}
		
		void moveRight() {
			setTranslateX(getTranslateX() + 5);
			
		}
		
		void moveUp() {
			if (canJump == true) {
			    setTranslateY(getTranslateY() - 20);
			}
		}
		
		void moveDown() {
			if(canFall == true) {
			    setTranslateY(getTranslateY() + 1);
			}
		}

	}
	
