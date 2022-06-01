public class Node 
{
	protected Node north;
	protected Node south;
	protected Node west;
	protected Node east;
	protected int xCoordinate;
	protected int yCoordinate;
	protected boolean explored;

	public Node(Node north, Node south, Node west, Node east, boolean explored, int x, int y)
	{
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
		this.explored = explored;
		this.xCoordinate = x;
		this.yCoordinate = y;
	}
	
	public Node getNorth() {
		return north;
	}
	
	public Node getEast() {
		return east;
	}
	
	public Node getSouth() {
		return south;
	}
	
	public Node getWest() {
		return west;
	}
	
	public int getX() {
		return xCoordinate;
	}
	
	public int getY() {
		return yCoordinate;
	}
	
	public Node getNode(int direction) {
		if (direction == 0) {
			return north;
		} else if (direction == 1) {
			return east;
		} else if (direction == 2) {
			return south;
		} else {
			return west;
		}
	}
	
	public boolean getExplored() {
		return explored;
	}
	
	public void setNorth(Node n) {
		north = n;
	}
	
	public void setEast(Node n) {
		east = n;
	}
	
	public void setSouth(Node n) {
		south = n;
	}
	
	public void setWest(Node n) {
		west = n;
	}
	
	public void setNode(Node n, int direction) {
		if (direction == 0) {
			north = n;
		} else if (direction == 1) {
			east = n;
		} else if (direction == 2) {
			south = n;
		} else {
			west = n;
		}
	}
	
	public void setExplored(boolean b) {
		explored = b;
	}
	
	public void setX(int x) {
		xCoordinate = x;
	}
	
	public void setY(int y) {
		yCoordinate = y;
	}
	
	
	//returns number of none null paths originating from current node
	public int getNumPaths() {
		int numPaths = 0;
		
		if (this.getNorth() != null) {
			numPaths++;
		} 
		
		if (this.getEast() != null) {
			numPaths++;
		} 
		
		if (this.getSouth() != null) {
			numPaths++;
		} 
		
		if (this.getWest() != null) {
			numPaths++;			
		}
		
		return numPaths;
	}
		
}
