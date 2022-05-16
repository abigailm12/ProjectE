
import java.util.ListIterator;

public class LinkedList implements ListIterator {

	private int size = 1;
	Node tail = new Node(null, null, null, null, true);
	Node current = tail;
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
	
	public void breakOff(Node node) {
		
		//get rid of all nodes after a given index
		
		//set the previous direction to null
		//set tail to be node(index)
		
		//size = index - 1	
			
	
	}
	
	public void backtrack() {
		Node currentNode = tail;
		int tempPreviousDirection = previousDirection;
		while (tail.getNumPaths() < 3) {
			//goes in previous direction
			
			
		}
		
		breakOff(currentNode);		
		
	}
	
	public int nextStep() {
		//Quack calls this method to determine which direction to go next
		int direction = 0;
		
		if (current.getEast() != null) {
		//if (tail.getEast() != null && tail.getEast().getExplored() == false) {
			direction = 1;
			current = current.getEast();
			this.previousDirection = 3;
		//} else if (tail.getSouth() != null && tail.getSouth().getExplored() == false) {
		} else if (current.getSouth() != null ) {
			direction = 2;
			current = current.getSouth();
			this.previousDirection = 0;
		//} else if (tail.getWest() != null && tail.getWest().getExplored() == false) {
		} else if (current.getWest() != null ) {
			direction = 3;
			current = current.getWest();
			this.previousDirection = 1;
		//} else if (tail.getNorth() != null && tail.getNorth().getExplored() == false) {
		} else if (current.getNorth() != null ) {
			direction = 0;
			current = current.getNorth();
			this.previousDirection = 2;
		} else {
			direction = previousDirection;
		}
		
		return direction;
	}
	
	public String toString() {
		String str = "" + size;//"tail : " + tail.getExplored() + " | East : " + tail.getEast().getExplored();
		return str;
	}
	
	public int getPreviousDirection() {
		return previousDirection;
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
