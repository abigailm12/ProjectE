
import java.util.ListIterator;

public class LinkedList {

	private int size = 1;
	Node current = new Node(null, null, null, null, true, -345, -243);
	public boolean backtracking = false;
	public boolean finalStep = false;
	//previous direction variable represents the direction from which the tail node came
	//
	//			  tail
	//				|		tail direction = north aka 0
	//				|
	//			newNode
	//
	private int previousDirection = 3; //0:North 1:East 2:South 3:West
	
	private AudioPlayer audio = new AudioPlayer();

	public void add(int previousDirection) {

		int quackX = (int) Main.frame.universe.getPlayer1().getCenterX();
		int quackY = (int) Main.frame.universe.getPlayer1().getCenterY();

		//public Node(Node north, Node south, Node west, Node east, boolean explored)
		Node newNode;

		if (size == 0) {
			newNode = new Node(null, null, null, null, false,-345, -243);
		} else {
			if (previousDirection == 0) {
				newNode = new Node(current, null, null, null, false, quackX, quackY - 40);
				current.setSouth(newNode);
			} else if (previousDirection == 1) {
				newNode = new Node(null, null, null, current, false, quackX - 40, quackY);
				current.setWest(newNode);
			} else if (previousDirection == 2) {
				newNode = new Node(null, current, null, null, false, quackX, quackY + 40);
				current.setNorth(newNode);
			} else {
				newNode = new Node(null, null, current, null, false, quackX + 40, quackY);
				current.setEast(newNode);
			}
		}
		current.setExplored(true);

		size++;

	}

	public int add(boolean north, boolean south, boolean west, boolean east) {

		Node intersection = current;
		Node newNorth = null;
		Node newSouth = null;
		Node newWest = null;
		Node newEast = null;
		
		int quackX = (int) Main.frame.universe.getPlayer1().getCenterX();
		int quackY = (int) Main.frame.universe.getPlayer1().getCenterY();

		if (north && previousDirection != 0) {
			newNorth = new Node(null, current, null, null, false, quackX, quackY - 40);
			intersection.setNorth(newNorth);
			size++;
		} 

		if (south && previousDirection != 2) {
			newSouth = new Node(current, null, null, null, false, quackX - 40, quackY);
			intersection.setSouth(newSouth);
			size++;
		}

		if (west && previousDirection != 3) {
			newWest = new Node(null, null, null, current, false, quackX, quackY + 40);
			intersection.setWest(newWest);
			size++;
		}

		if (east && previousDirection != 1) {
			newEast = new Node(null, null, current, null, false, quackX + 40, quackY);
			intersection.setEast(newEast);
			size++;
		}

		current.setExplored(true);
		int direction = nextStep();

		if (direction == 0) {
			current = newNorth;
		} else if (direction == 2) {
			current = newSouth;
		} else if (direction == 3) {
			current = newWest;
		} else {
			current = newEast;
		}

		return direction;

	}

	public int getOppositeDirection(int d) {
		if (d == 0) {
			return 2;
		} else if (d == 1) {
			return 3;
		} else if (d == 2) {
			return 0;
		} else if (d == 3) {
			return 1;
		}

		return -1;
	}

	public int backtrack() {
		//goes in the only direction he can while following nodes UNTIL he reaches a node that has 3 paths
		//iterate through the list starting at tail checking the nullness of each link and going to the not null one

		int direction = previousDirection;

		if (current.getNode(direction) == null) {
			System.out.println("found a turn");
			if (current.getNorth() != null && direction != 2) {
				direction = 0;
				previousDirection = 0;
				current = current.getNorth();
			} else if (current.getEast() != null && direction != 3) {
				direction = 1;
				previousDirection = 1;
				current = current.getEast();
			} else if (current.getSouth() != null && direction != 0) {
				direction = 2;
				previousDirection = 2;
				current = current.getSouth();
			} else if (current.getWest() != null && direction != 1) {
				direction = 3;
				previousDirection = 3;
				current = current.getWest();
			}

			return direction;
		}

		int oppositeDirection = getOppositeDirection(direction);
		if (current.getNode(direction).getNumPaths() > 2) {
			backtracking = false;
			finalStep = true;
			current.getNode(direction).setNode(null, oppositeDirection);
			System.out.println("done backtracking");
			current = current.getNode(direction);
			return direction;
		}
		current = current.getNode(direction);		
		return direction;

	}

	public int exploredIntersection() {

		int direction = 0;
		System.out.println("explored intersection");
		if (current.getNorth() != null && !current.getNorth().getExplored()) {
			direction = 0;
			current = current.getNorth();
			this.previousDirection = 2;
			
		} else if (current.getEast() != null && !current.getEast().getExplored()) {
			direction = 1;
			current = current.getEast();
			this.previousDirection = 3;
			
		} else if (current.getSouth() != null && !current.getSouth().getExplored()) {
			direction = 2;
			current = current.getSouth();
			this.previousDirection = 0;
			
		} else if (current.getWest() != null && !current.getWest().getExplored()) {
			direction = 3;
			current = current.getWest();
			this.previousDirection = 1;
		}

		return direction;

	}

	public int nextStep() {
		//Quack calls this method to determine which direction to go next
		int direction = 0;

		if (current.getEast() != null && current.getEast().getExplored() == false) {
			direction = 1;
			current = current.getEast();
			this.previousDirection = 3;
			
		} else if (current.getSouth() != null && current.getSouth().getExplored() == false) {
			direction = 2;
			current = current.getSouth();
			this.previousDirection = 0;
			
		} else if (current.getWest() != null && current.getWest().getExplored() == false) {
			direction = 3;
			current = current.getWest();
			this.previousDirection = 1;
			
		} else if (current.getNorth() != null && current.getNorth().getExplored() == false) {
			direction = 0;
			current = current.getNorth();
			this.previousDirection = 2;
			
		} else {
			audio.play("res/sounds/bounceSound.wav");
			backtracking = true;
			System.out.println("backtracking");
			direction = -1;
			
		}

		return direction;
	}

	public int getPreviousDirection() {
		return previousDirection;
	}

	public int getSize() {
		return size;
	}
	
	public void reset() {
		size = 1;
		current = new Node(null, null, null, null, true, -345, -243);
		backtracking = false;
		finalStep = false;
		previousDirection = 3;
	}

}
