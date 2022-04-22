public class Node 
{
	protected Node north;
	protected Node south;
	protected Node west;
	protected Node east;
	protected String value;

	public Node(String value, Node north, Node south, Node west, Node east)
	{
		this.value = value;
		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
	}
	
	public String getValue()
	{
		return this.value;
	}
		
}
