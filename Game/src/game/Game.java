package game;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game extends Application {
	
	/*Pane is the root 'node', off which we have children which are all 'added' to 
	 * the root and subsequent nodes. This makes a tree like structure. Pane is 
	 * like a stage which basically displays our game. Adding nodes to the root pane 
	 * makes them visible in our game.
	*/
	private Pane root = new Pane();
	
	//Making a player who is an instance of sprite
	private Sprite player = new Sprite(100, 460, 40, 40, "player", Color.DARKGREEN);
	
	//Making the ground which is an instance of platform
	private Platform ground = new Platform (0, 500, 900, 100, Color.BROWN);
	
	private Parent createContent() {
		root.setPrefSize(900, 600);
		root.getChildren().add(player);
		root.getChildren().add(ground);
		//Animation timer repeats 60 times every second and gives the game it's sense of time
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				//Timer calls loop which is main game logic
				loop();
			}
		};
		
		timer.start();
		
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
	
	
	
	/*Game loop describes processes to follow throughout the game (the game logic) 
	 * and is being called roughly 60 times per second due to animation timer.
	 */
	private void loop() {
		
	    
		//Iterating through all the children of the root node (eg: sprites, platforms)
		root.getChildren().forEach(c -> {
			if (c instanceof Sprite) {
				if (((Sprite) c).type== "player") {
					//Add gravity
					if (c.getTranslateY() != 460) {
						player.moveDown();
					}
					
					for (int i = 0; i < root.getChildren().size(); i++) {
                        Node f = root.getChildren().get(i);
						if (f instanceof Platform) {
							/*Making it such that player won't fall if on a platform. Needs fixing
							* so that its only when they're standing on top of it.
							*/
							if(player.getBoundsInParent().intersects(f.getBoundsInParent())) {
								player.canFall = false;
								break;
							} else {
								player.canFall = true;
							}
						}
					}
					
					
					
					
					root.getChildren().forEach(s -> {
						if (s instanceof Sprite) {
							if(((Sprite)s).type == "enemy") {
						        //If player touches a spike, they die
								if(player.getBoundsInParent().intersects(s.getBoundsInParent())) {
									player.isDead = true;
							    }
							}
						}
					});
					
					
					
					
						
				} if (((Sprite) c).type.equals("playerbullet")) {
					((Sprite) c).moveRight();
					root.getChildren().forEach(s -> {
						if (s instanceof Sprite) {
							if (((Sprite) s).type== "enemy") {
						        //If bullet touches spike, it dies
								if (c.getBoundsInParent().intersects(s.getBoundsInParent())) {
			                        
									((Sprite) s).isDead = true;
								}
							}
						}
						});
                    
				}
				
			} 
		});
	    //Removing all children form the screen that have died
		root.getChildren().forEach(c ->{
            if (c instanceof Sprite) {
			    if (((Sprite)c).isDead == true) {
				    root.getChildren().remove(c);
			    }
			}
	    });
		
	}
	
	private void shoot(Sprite who) {
		Sprite s = new Sprite((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 20, 5, who.type + "bullet", Color.BLACK);
		root.getChildren().add(s);
	}
	
	
	
	//Extending rectangle for simplicity. Better if extended ImageView.
	private static class Sprite extends Rectangle{
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
	
	//Platforms for jumping on. Ground is also an instance of platform.
	private static class Platform extends Rectangle{
		
		Platform(int x, int y, int w, int h, Color color){
			super(w, h, color);
			setTranslateX(x);
			setTranslateY(y);
		}
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
        Scene scene = new Scene(createContent());
        
        //Controls
        scene.setOnKeyPressed(e -> {
        	switch (e.getCode()) {
        	case LEFT:
        		player.moveLeft();
        		break;
        	case RIGHT:
        		player.moveRight();
        		break;
        	case UP:
        		player.moveUp();
        		break;
        	case DOWN:
        		player.moveDown();
        		break;
        	case SPACE:
        		shoot(player);
        		break;
        		
        	}
        });
        stage.setScene(scene);
		stage.setTitle("wassup");
		stage.show();
		
	}
	
	//Start = entry point for program.
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	

}
