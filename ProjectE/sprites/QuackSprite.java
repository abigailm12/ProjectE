import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class QuackSprite implements DisplayableSprite {

	private static Image northImage;
	private static Image eastImage;
	private static Image westImage;
	private static Image southImage;
	private long elapsedTime = 0;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 13;
	private double height = 13;
	private boolean dispose = false;	
	private int direction = 0; //0:North 1:East 2:South 3:West

	private final double VELOCITY = 50;

	public QuackSprite(double centerX, double centerY, double height, double width) {
		this(centerX, centerY);
		
		this.height = height;
		this.width = width;
	}

	
	public QuackSprite(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		
		if (northImage == null) {
			try {
				northImage = ImageIO.read(new File("res/quack1.0NORTH.png"));
				eastImage = ImageIO.read(new File("res/quack1.0EAST.png"));
				southImage = ImageIO.read(new File("res/quack1.0SOUTH.png"));
				westImage = ImageIO.read(new File("res/quack1.0WEST.png"));
			//	this.height = this.northImage.getHeight(null) / 2;
			//	this.width = this.northImage.getWidth(null) / 2;
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}		
	}

	public Image getImage() {
		if (direction == 0) {
			return northImage;
		} else if (direction == 1) {
			return eastImage;
		} else if (direction == 2) {
			return southImage;
		} else {
			return westImage;
		}
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return true;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return dispose;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		elapsedTime += actual_delta_time;
		double velocityX = 0;
		double velocityY = 0;
		
		// WEST	
		if (keyboard.keyDown(37)) {
			velocityX = -VELOCITY;
			direction = 3;
		}
		//NORTH
		if (keyboard.keyDown(38)) {
			velocityY = -VELOCITY;	
			direction = 0;
		}
		// EAST
		if (keyboard.keyDown(39)) {
			velocityX += VELOCITY;
			direction = 1;
		}
		// SOUTH
		if (keyboard.keyDown(40)) {
			velocityY += VELOCITY;	
			direction = 2;
		}
		
		double deltaX = actual_delta_time * 0.001 * velocityX;
		double deltaY = actual_delta_time * 0.001 * velocityY;
		if (checkBarrierCollision(universe, deltaX, 0) == false) {
			centerX += deltaX;
		}
		
		if (checkBarrierCollision(universe, 0, deltaY) == false) {
			centerY += deltaY;
		}
	}
		



	public boolean checkBarrierCollision(Universe sprites, double deltaX, double deltaY) {
		
		boolean colliding = false;
		
		for (DisplayableSprite sprite : sprites.getSprites()) {
			
			if (sprite instanceof BarrierSprite) {
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					colliding = true;
					break;
				}
			}
		}
		return colliding;		
	}


	@Override
	public void setDispose(boolean dispose) {
		this.dispose = true;
	}

}
