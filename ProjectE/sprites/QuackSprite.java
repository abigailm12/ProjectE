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
	private double width = 25;
	private double height = 25;
	private boolean dispose = false;	
	private int direction = 2;//0:North 1:East 2:South 3:West
	//Direction direction = WEST;
	public LinkedList list = new LinkedList();
	private int margin = 15;
	int steps = 0;
	

	private final double VELOCITY = 0.0025;

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
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
		System.out.println("Step 0");
		System.out.println("Number of nodes : " + list);
		System.out.println("Previous direction : " + list.getPreviousDirection());
		System.out.print("numPaths : ");
		System.out.println("" + list.current.getNumPaths());
		System.out.println("");
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
		
		if (list.backtracking) {
			direction = list.backtrack();
		} else {
			direction = lookAround(universe);
		}
		
		if (direction == -1) {
			direction = list.backtrack();
		}
	
		//direction = list.nextStep();
		
		double deltaX = 0;
		double deltaY = 0;
		double velocityX = 0;
		double velocityY = 0;
		double marginX = 0;
		double marginY = 0;
		
		DisplayableSprite currentPath = null;
		
		// finds the current path sprite quack is in
		for (DisplayableSprite sprite : universe.getSprites()) {

			if (sprite instanceof PathSprite) {
				if (CollisionDetection.inside(this, sprite, 0, 0)) {
					currentPath = sprite;
					break;
				}
			}
		}
		
		//MOVE EAST
		if (direction == 1) {
			velocityX = VELOCITY;
			//marginX = -5;
		}
		//MOVE SOUTH
		if (direction == 2) {
			velocityY = VELOCITY;
			//marginY = -5;
		}
		//MOVE NORTH
		if (direction == 0) {
			velocityY = -VELOCITY;
			//marginY = 5;
		}
		//MOVE WEST
		if (direction == 3) {
			velocityX = -VELOCITY;
			//marginX = 5;
		}
		
		// moves quack in given direction until he is no longer overlapping with currentPath
		if (currentPath != null) {
			while (CollisionDetection.overlaps((DisplayableSprite) this, currentPath, marginX, marginY)) {
				
				deltaX = MappedBackground.TILE_WIDTH * velocityX * 0.005;
				deltaY = MappedBackground.TILE_HEIGHT * velocityY * 0.005;

				if (checkBarrierCollision(universe, deltaX, 0) == false) {
					centerX += deltaX;
				}

				if (checkBarrierCollision(universe, 0, deltaY) == false) {
					centerY += deltaY;
				}
		    
			}
		}
		
		steps++;
		System.out.println("Step " + steps);
		System.out.println("Number of nodes : " + list.getSize());
		System.out.println("Previous direction : " + list.getPreviousDirection());
		System.out.println("current node hash : " + list.current.toString());
		System.out.println("");
		
		if (checkFinishLineCollision(universe)) {
			System.out.println("Finished!");
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
	
	public boolean checkFinishLineCollision(Universe sprites) {
		boolean colliding = false;

		for (DisplayableSprite sprite : sprites.getSprites()) {

			if (sprite instanceof FinishLine) {
				if (CollisionDetection.overlaps(this.getMinX(), this.getMinY(), 
						this.getMaxX(), this.getMaxY(), 
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

	public int lookAround(Universe universe) {

		//this method is intended to find paths in the four directions around
		//the cell Quack is currently on

		int numPaths = 0;
		boolean north = false;
		boolean east = false;
		boolean south = false;
		boolean west = false;
		
		if (list.current.getExplored() && list.getSize() > 1) {
			//find unexplored path
			list.finalStep = false;
			return list.exploredIntersection();
		}

		//south
		if (checkBarrierCollision(universe, 0, margin) == false) {
			south = true;
			numPaths++;
		}

		//east
		if (checkBarrierCollision(universe, margin, 0) == false) {
			east = true;
			numPaths++;
		}

		//north
		if (checkBarrierCollision(universe, 0, -margin) == false) {
			north = true;
			numPaths++;
		}

		//west
		if (checkBarrierCollision(universe, -margin, 0) == false) {
			west = true;
			numPaths++;
		}
		
		int direction = 0;
		int previousDirection = list.getPreviousDirection();
		
		if (numPaths < 3) {
			if (south && previousDirection != 2) {
				list.add(0);
				System.out.println("made south node");
			}
	
			if (north && previousDirection != 0) {
				list.add(2);
				System.out.println("made north node");
			}
	
			if (west && previousDirection != 3) {
				list.add(1);
				System.out.println("made west node");
			}
	
			if (east && previousDirection != 1) {
				list.add(3);
				System.out.println("made east node");
			}
			
			direction = list.nextStep();
			
		} else {
			direction = list.add(north, south, west, east);
		}
		
		return direction;

	}

}
