package game;

import javafx.scene.Parent;
import javafx.scene.image.Image;
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
	public Character player = new Character(100, 400, 40, 40, "player", Color.DARKGREEN);

	
	public Parent init() {
		
		root.setPrefSize(900, 600);
		root.getChildren().add(player);
		level1();
		return root;
	}
	/*Can add more levels as character progresses through game. 
	 * In level1, we're just adding platforms and spikes, on top
	 * of the already existent player and ground.
	 */
	private void level1() {
		int[][] level1 = 
			{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			 {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			 {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			 {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			};
		
		String imagePath = "Game/gameTiles.png";
    	Image image = new Image(imagePath);
		
        for (int i = 0; i < level1[0].length; i++) { 
            for (int j = 0; j < level1.length; j++) { 
                if (level1[j][i] == 1) {
                	Platform platform = new Platform (i * 40, j * 40, 0, 0, 40, 40, image, 2);
                	root.getChildren().add(platform);
                } else if (level1[j][i] == 3) {
                	Platform platform = new Platform (i * 40, j * 40, 0, 10, 40, 40, image, 2);
                	root.getChildren().add(platform);
                } else if (level1[j][i] == 2) {
                	Character enemy = new Character (i * 40, j * 40, 40, 40, "enemy", Color.DARKRED);
                	root.getChildren().add(enemy);
                }
            } 
        }
	}
}
