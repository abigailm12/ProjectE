public class Node 
{
	protected Node north;
	protected Node south;
	protected Node west;
	protected Node east;
	protected int length;

	public Node(int length, Node north, Node south, Node west, Node east)
	{
		this.length = length;
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
	}
	
	public int getLength()
	{
		return this.length;
	}
		
}
