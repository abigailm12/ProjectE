import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class QuackSprite implements DisplayableSprite {

	private static final int FRAMES = 7;
	private static int framesPerSecond = 25;
	private static int millisecondsPerFrame = 2000 / framesPerSecond;
	private static Image[] northImage = null;
	private static Image[] eastImage = null;
	private static Image[] westImage = null;
	private static Image[] southImage = null;
	private long elapsedTime = 0;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 30;
	private double height = 30;
	private boolean dispose = false;
	private boolean done = false;
	private int direction = 2;//0:North 1:East 2:South 3:West
	//Direction direction = WEST;
	public LinkedList list = new LinkedList();
	private int margin = 15;
	int steps = 0;
	private boolean moving = false;
	private DisplayableSprite currentPath = null;
	

	private final double VELOCITY = 1.5;

	public QuackSprite(double centerX, double centerY, double height, double width) {
		this(centerX, centerY);

		this.height = height;
		this.width = width;
	}


	public QuackSprite(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		
		if (northImage == null) {
			System.out.println("populated image arrays");
			try {				
				northImage = new Image[FRAMES];
				for (int i = 0; i < FRAMES; i++) {
					String path = String.format("res/quackNorth/north-%d.png", i);
					northImage[i] = ImageIO.read(new File(path));
				}
				eastImage = new Image[FRAMES];
				for (int i = 0; i < FRAMES; i++) {
					String path = String.format("res/quackEast/east-%d.png", i);
					eastImage[i] = ImageIO.read(new File(path));
				}
				southImage = new Image[FRAMES];
				for (int i = 0; i < FRAMES; i++) {
					String path = String.format("res/quackSouth/south-%d.png", i);
					southImage[i] = ImageIO.read(new File(path));
				}
				westImage = new Image[FRAMES];
				for (int i = 0; i < FRAMES; i++) {
					String path = String.format("res/quackWest/west-%d.png", i);
					westImage[i] = ImageIO.read(new File(path));
				}
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
		
		Image output = null;
		long frame = elapsedTime / millisecondsPerFrame;
		int index = (int) frame % FRAMES;
		
		if (direction == 0) {
			output = northImage[index];
		} else if (direction == 1) {
			output = eastImage[index];
		} else if (direction == 2) {
			output = southImage[index];
		} else {
			output = westImage[index];
		}

		return output;
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
		if (!done) {
		
			if (moving) {
				move(direction, currentPath, universe);
			} else {
				direction = getDirection(universe);
				currentPath = getCurrentPath(universe);
				System.out.println(direction + " " + currentPath);
				steps++;
				System.out.println("Step " + steps);
				System.out.println("Number of nodes : " + list.getSize());
				System.out.println("Previous direction : " + list.getPreviousDirection());
				System.out.println("current node hash : " + list.current.toString());
				System.out.println("current node coordinates : (" + list.current.getX() + ", " + list.current.getY() + ")");
				System.out.println("");
			}
				
				if (checkFinishLineCollision(universe)) {
					System.out.println("Finished!");
					done = true;
				}
		}
	}
	
	public DisplayableSprite getCurrentPath(Universe universe) {
		
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
		
		return currentPath;
	}
	
	public int getDirection(Universe universe) {
		if (list.backtracking) {
			direction = list.backtrack();
		} else {
			direction = lookAround(universe);
		}
		
		if (direction == -1) {
			direction = list.backtrack();
		}
		
		moving = true;
		return direction;
	}
	
	public void move(int direction, DisplayableSprite currentPath, Universe universe) {
		double deltaX = 0;
		double deltaY = 0;
		double velocityX = 0;
		double velocityY = 0;
		double marginX = 0;
		double marginY = 0;
		
		//MOVE EAST
		if (direction == 1) {
			velocityX = VELOCITY;
		}
		//MOVE SOUTH
		if (direction == 2) {
			velocityY = VELOCITY;
		}
		//MOVE NORTH
		if (direction == 0) {
			velocityY = -VELOCITY;
		}
		//MOVE WEST
		if (direction == 3) {
			velocityX = -VELOCITY;
		}
		
		// moves quack in given direction until he is no longer overlapping with currentPath
		if (currentPath != null) {
			if (CollisionDetection.overlaps((DisplayableSprite) this, currentPath, marginX, marginY)) {
				
				deltaX = velocityX;
				deltaY = velocityY;	

				if (checkBarrierCollision(universe, deltaX, 0) == false) {
					centerX += deltaX;
				}

				if (checkBarrierCollision(universe, 0, deltaY) == false) {
					centerY += deltaY;
				}
			    
			} else {
				moving = false;
			}
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
