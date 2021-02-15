//comment

package game;

import javafx.scene.paint.Color;

public class Character extends Sprite {

	public Character(int x, int y, int width, int height, String type, Color color) {
		super(x, y, width, height, type, color);
	}
	
	public boolean isDead = false;
	public boolean canJump = true;
	public boolean canFall = true;
	public int health = 100;
	public int speed = 10;
	
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean canJump() {
		return canJump;
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}
	
	public boolean canFall() {
		return canFall;
	}
	
	public void setCanFall(boolean canFall) {
		this.canFall = canFall;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	void moveLeftPixels(int pixels) {
		setTranslateX(getTranslateX() - pixels);
	}
	
	void moveRightPixels(int pixels) {
		setTranslateX(getTranslateX() + pixels);
	}
	
	void moveUpPixels(int pixels) {
		if (canJump()) {
			setTranslateY(getTranslateY() - pixels);
		}
	}
	
	void moveDownPixels(int pixels) {
		if(canFall()) {
			setTranslateY(getTranslateY() + pixels);
		}
	}
	
	

}
