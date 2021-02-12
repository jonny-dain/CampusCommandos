package game;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Renderer {
	/*Pane is the root 'node', off which we have children which are all 'added' to 
	 * the root and subsequent nodes. This makes a tree like structure. Pane is 
	 * like a stage which basically displays our game. Adding nodes to the root pane 
	 * makes them visible in our game.
	*/
	public Pane root = new Pane();
	//Making a player who is an instance of sprite
	public Sprite player = new Sprite(100, 460, 40, 40, "player", Color.DARKGREEN);
	
	//Making the ground which is an instance of platform
	private Platform ground = new Platform (0, 500, 900, 100, Color.BROWN);
	public Parent init() {
		
		root.setPrefSize(900, 600);
		root.getChildren().add(player);
		root.getChildren().add(ground);
		level1();
		return root;
	}
	/*Can add more levels as character progresses through game. 
	 * In level1, we're just adding platforms and spikes, on top
	 * of the already existent player and ground.
	 */
	private void level1() {
		Platform lvl1 = new Platform (50, 300, 200, 10, Color.BLACK);
		Platform lvl2 = new Platform (300, 100, 300, 10, Color.BLACK);
		Platform lvl3 = new Platform (600, 200, 200, 10, Color.BLACK);
		Sprite spike1 = new Sprite(60, 270, 30, 30, "enemy", Color.DARKRED);
		root.getChildren().add(spike1);
		Sprite spike2 = new Sprite(320, 70, 30, 30, "enemy", Color.DARKRED);
		root.getChildren().add(spike2);
		Sprite spike3 = new Sprite(650, 170, 30, 30, "enemy", Color.DARKRED);
		root.getChildren().add(spike3);
		Sprite spike4 = new Sprite(800, 470, 30, 30, "enemy", Color.DARKRED);
		root.getChildren().add(spike4);
		root.getChildren().add(lvl1);
		root.getChildren().add(lvl2);
		root.getChildren().add(lvl3);
	}
}
