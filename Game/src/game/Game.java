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
	
    public double mouseX;
    public double mouseY;
	
	Renderer r = new Renderer();
	
	private Parent createContent() {
		
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
			if (c instanceof Character) {
				if (((Character) c).getType()== "player") {
					//Add gravity
					if (c.getTranslateY() != 460) {
						r.player.moveDownPixels(2);
					}
					
					for (int i = 0; i < r.root.getChildren().size(); i++) {
                        Node f = r.root.getChildren().get(i);
						if (f instanceof Platform) {
							/*Making it such that player won't fall if on a platform. Needs fixing
							* so that its only when they're standing on top of it.
							*/
							if(r.player.getBoundsInParent().intersects(f.getBoundsInParent())) {
								r.player.setCanFall(false);
								break;
							} else {
								r.player.setCanFall(true);
							}
						}
					}
					
					
					
					
					r.root.getChildren().forEach(s -> {
						if (s instanceof Character) {
							if(((Character)s).getType() == "enemy") {
						        //If player touches a spike, they die
								if(r.player.getBoundsInParent().intersects(s.getBoundsInParent())) {
									r.player.setDead(true);
							    }
							}
						}
					});
					
					
					
					
						

				} if (((Character) c).getType().equals("playerbullet")) {
					((Character) c).vector();
					r.root.getChildren().forEach(s -> {
						if (s instanceof Character) {
							if (((Character) s).getType() == "enemy") {
						        //If bullet touches spike, it dies
								if (c.getBoundsInParent().intersects(s.getBoundsInParent())) {
			                        
									((Character) s).setDead(true);
								}
							}
						}
						});
                    
				}
				
			} 
		});
	    //Removing all children form the screen that have died
		r.root.getChildren().forEach(c ->{
            if (c instanceof Character) {
			    if (((Character)c).isDead() == true) {
				    r.root.getChildren().remove(c);
			    }
			}
	    });
		
	}
	

	// method to be implemented in the character class, some chars may shoot, some don't
	private void shoot(Sprite shooter) {
		Character bullet = new Character((int)shooter.getTranslateX() + 20, (int) shooter.getTranslateY(), 20, 5, shooter.getType() + "bullet", Color.BLACK);
		bullet.xVector = mouseX-(double)bullet.currentX;
		bullet.yVector = mouseY-(double)bullet.currentY;
		bullet.changeRateX = bullet.xVector;
		bullet.changeRateY = bullet.yVector;
		bullet.setRotate(Math.toDegrees(Math.atan(bullet.yVector/bullet.xVector)));
		double hypoteneuse = Math.sqrt(bullet.yVector*bullet.yVector+bullet.xVector*bullet.xVector);
		bullet.changeRateX = bullet.xVector/hypoteneuse;
		bullet.changeRateY = bullet.yVector/hypoteneuse;
		r.root.getChildren().add(bullet);
		
	}

	
	@Override
	public void start(Stage stage) throws Exception {
		
        Scene scene = new Scene(createContent());
        
        scene.setOnKeyPressed(e ->{
        	switch (e.getCode()) {
        	case A:
        		r.player.moveLeftPixels(5);
        		break;
        	case D:
        		r.player.moveRightPixels(5);
        		break;
        	case W:
        		r.player.moveUpPixels(20);
        		break;
        	case S:
        		r.player.moveDownPixels(1);
        		break;
        
        		
        	}
        });
        scene.setOnMouseClicked(e -> shoot(r.player));
        scene.setOnMouseMoved(e -> {
        	mouseX = e.getX();
        	mouseY = e.getY();
        });

        stage.setScene(scene);
		stage.setTitle("wassup");
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	

}
