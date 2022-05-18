
import java.util.ListIterator;

public class LinkedList implements ListIterator {

	private int size = 1;
	Node tail = new Node(null, null, null, null, true);
	Node current = tail;
	public boolean backtracking = false;
	//previous direction variable represents the direction from which the tail node came
	//
	//			  tail
	//				|		tail direction = north aka 0
	//				|
	//			newNode
	//
	private int previousDirection = 3; //0:North 1:East 2:South 3:West
	
	public ListIterator listIterator() {
//		this.pointer = 0;
		return this;
	}
	
	public void add(int previousDirection) {
		
		//public Node(Node north, Node south, Node west, Node east, boolean explored)
		Node newNode;
		
		if (size == 0) {
			newNode = new Node(null, null, null, null, false);
		} else {
			if (previousDirection == 0) {
				newNode = new Node(tail, null, null, null, false);
				tail.setSouth(newNode);
			} else if (previousDirection == 1) {
				newNode = new Node(null, null, null, tail, false);
				tail.setWest(newNode);
			} else if (previousDirection == 2) {
				newNode = new Node(null, tail, null, null, false);
				tail.setNorth(newNode);
			} else {
				newNode = new Node(null, null, tail, null, false);
				tail.setEast(newNode);
			}
		}
		tail.setExplored(true);
		//System.out.println("numpaths : " + tail.getNumPaths());
		tail = newNode;

		size++;

	}
	
	public int add(boolean north, boolean south, boolean west, boolean east) {
		
		Node intersection = tail;
		Node newNorth = null;
		Node newSouth = null;
		Node newWest = null;
		Node newEast = null;
		
		if (north) {
			newNorth = new Node(null, tail, null, null, false);
			intersection.setNorth(newNorth);
			size++;
		} 
		
		if (south) {
			newSouth = new Node(tail, null, null, null, false);
			intersection.setSouth(newSouth);
			size++;
		}
		
		if (west) {
			newWest = new Node(null, null, null, tail, false);
			intersection.setWest(newWest);
			size++;
		}
		
		if (east) {
			newEast = new Node(null, null, tail, null, false);
			intersection.setEast(newEast);
			size++;
		}
		
		tail.setExplored(true);
		int direction = nextStep();
		
		if (direction == 0) {
			tail = newNorth;
		} else if (direction == 2) {
			tail = newSouth;
		} else if (direction == 3) {
			tail = newWest;
		} else {
			tail = newEast;
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
	
	public void breakOff(Node node) {
		
		//get rid of all nodes after a given index
		
		//set the previous direction to null
		//set tail to be node(index)
		
		//size = index - 1	
			
	
	}
	
	public int backtrack() {
		int direction = 0;
		//goes in the only direction he can while following nodes UNTIL he reaches a node that has 3 paths
		//iterate through the list starting at tail checking the nullness of each link and going to the not null one
		
		direction = previousDirection;

		if (current.getNode(direction) == null) {
			System.out.println("found a turn");
			if (current.getNorth() != null && direction != 2) {
				direction = 0;
				current = current.getNorth();
			} else if (current.getEast() != null && direction != 3) {
				direction = 1;
				current = current.getEast();
			} else if (current.getSouth() != null && direction != 0) {
				direction = 2;
				current = current.getSouth();
			} else if (current.getWest() != null && direction != 1) {
				direction = 3;
				current = current.getWest();
			}
			
			return direction;
		}
		
		int oppositeDirection = getOppositeDirection(direction);
		if (current.getNode(direction).getNumPaths() > 2) {
			backtracking = false;
			current.getNode(direction).getNode(oppositeDirection).setNode(null, direction);
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
		} else if (current.getEast() != null && !current.getEast().getExplored()) {
			direction = 1;
		} else if (current.getSouth() != null && !current.getSouth().getExplored()) {
			direction = 2;
		} else if (current.getWest() != null && !current.getWest().getExplored()) {
			direction = 3;
		}
 		
		return direction;

	}
	
	public int nextStep() {
		//Quack calls this method to determine which direction to go next
		int direction = 0;
		
		//if (current.getEast() != null) {
		if (current.getEast() != null && current.getEast().getExplored() == false) {
			direction = 1;
			current = current.getEast();
			this.previousDirection = 3;
		} else if (current.getSouth() != null && current.getSouth().getExplored() == false) {
		//} else if (current.getSouth() != null ) {
			direction = 2;
			current = current.getSouth();
			this.previousDirection = 0;
		} else if (current.getWest() != null && current.getWest().getExplored() == false) {
		//} else if (current.getWest() != null ) {
			direction = 3;
			current = current.getWest();
			this.previousDirection = 1;
		} else if (current.getNorth() != null && current.getNorth().getExplored() == false) {
		//} else if (current.getNorth() != null ) {
			direction = 0;
			current = current.getNorth();
			this.previousDirection = 2;
		} else {
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
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(Object e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Object e) {
		// TODO Auto-generated method stub
		
	}
	
}
