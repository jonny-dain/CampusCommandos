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
	

	
	Renderer r = new Renderer();
	
	private Parent gameLoop() {
		
		//Animation timer repeats 60 times every second and gives the game it's sense of time
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				//Timer calls loop which is main game logic
				loop();
			}
		};
		
		timer.start();
		return r.init();
	}
	

	

	
	
	
	/*Game loop describes processes to follow throughout the game (the game logic) 
	 * and is being called roughly 60 times per second due to animation timer.
	 */
	private void loop() {
		
	    
		//Iterating through all the children of the root node (eg: sprites, platforms)
		r.root.getChildren().forEach(c -> {
			if (c instanceof Sprite) {
				if (((Sprite) c).type== "player") {
					//Add gravity
					if (c.getTranslateY() != 460) {
						r.player.moveDown();
					}
					
					for (int i = 0; i < r.root.getChildren().size(); i++) {
                        Node f = r.root.getChildren().get(i);
						if (f instanceof Platform) {
							/*Making it such that player won't fall if on a platform. Needs fixing
							* so that its only when they're standing on top of it.
							*/
							if(r.player.getBoundsInParent().intersects(f.getBoundsInParent())) {
								r.player.canFall = false;
								break;
							} else {
								r.player.canFall = true;
							}
						}
					}
					
					
					
					
					r.root.getChildren().forEach(s -> {
						if (s instanceof Sprite) {
							if(((Sprite)s).type == "enemy") {
						        //If player touches a spike, they die
								if(r.player.getBoundsInParent().intersects(s.getBoundsInParent())) {
									r.player.isDead = true;
							    }
							}
						}
					});
					
					
					
					
						
				} if (((Sprite) c).type.equals("playerbullet")) {
					((Sprite) c).moveRight();
					r.root.getChildren().forEach(s -> {
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
		r.root.getChildren().forEach(c ->{
            if (c instanceof Sprite) {
			    if (((Sprite)c).isDead == true) {
				    r.root.getChildren().remove(c);
			    }
			}
	    });
		
	}
	
	private void shoot(Sprite who) {
		Sprite s = new Sprite((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 20, 5, who.type + "bullet", Color.BLACK);
		r.root.getChildren().add(s);
	}
	
	
	

	
	
	@Override
	public void start(Stage stage) throws Exception {
		
        Scene scene = new Scene(gameLoop());
        
        //Controls
        scene.setOnKeyPressed(e -> {
        	switch (e.getCode()) {
        	case LEFT:
        		r.player.moveLeft();
        		break;
        	case RIGHT:
        		r.player.moveRight();
        		break;
        	case UP:
        		r.player.moveUp();
        		break;
        	case DOWN:
        		r.player.moveDown();
        		break;
        	case SPACE:
        		shoot(r.player);
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
