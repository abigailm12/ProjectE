public class Node 
{
	protected Node north;
	protected Node south;
	protected Node west;
	protected Node east;
	protected int distanceNorth;
	protected int distanceEast;
	protected int distanceSouth;
	protected int distanceWest;
	protected boolean explored;

	public Node(int distanceNorth, int distanceEast, int distanceSouth, int distanceWest, 
			Node north, Node south, Node west, Node east, boolean explored)
	{
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
		this.distanceNorth = distanceNorth;
		this.distanceEast = distanceEast;
		this.distanceSouth = distanceSouth;
		this.distanceWest = distanceWest;
		this.explored = explored;
	}
	
	public int getDistanceNorth() {
		return distanceNorth;
	}
	
	public int getDistanceEast() {
		return distanceEast;
	}
	
	public int getDistanceSouth() {
		return distanceSouth;
	}
	
	public int getDistanceWest() {
		return distanceWest;
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
	
	public boolean getExplored() {
		return explored;
	}
	
	public void setDistanceNorth(int i) {
		distanceNorth = i;
	}
	
	public void setDistanceEast(int i) {
		distanceEast = i;
	}
	
	public void setDistanceSouth(int i) {
		distanceSouth = i;
	}
	
	public void setDistanceWest(int i) {
		distanceWest = i;
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
	
	public void setExplored(boolean b) {
		explored = b;
	}
		
}
