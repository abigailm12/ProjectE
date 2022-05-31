
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MappedBackground implements Background {
	
	public static int TILE_WIDTH = AnimationFrame.SCREEN_WIDTH / 20;
    public static int TILE_HEIGHT = AnimationFrame.SCREEN_HEIGHT / 15;
	//public static int TILE_HEIGHT = TILE_WIDTH;
    
    private Image barrier;
    private Image path;
    private Image path1;
    private Image path2;
    private Image path3;
    private Image path4;
    private Image path5;
    private Image path6;
    private Image path7;
    private Image path8;
    private Image path9;
 
    
    private int maxCols = 0;
    private int maxRows = 0;    
    
    private int map[][] = new int[][] {
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	1,	2,	3,	4,	0,	5,	6,	7,	8,	9,	1,	2,	3,	4,	0,	0,	0,	0,	0},
    	{0,	0,	0,	0,	5,	0,	0,	0,	0,	0,	0,	0,	0,	0,	5,	0,	0,	0,	0,	0},
    	{0,	0,	0,	0,	6,	0,	3,	0,	0,	6,	7,	8,	9,	7,	6,	7,	8,	9,	0,	0},
    	{0,	7,	0,	0,	7,	0,	2,	0,	0,	5,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0},
    	{0,	6,	0,	0,	8,	9,	1,	2,	3,	4,	3,	2,	1,	2,	3,	4,	0,	2,	0,	0},
    	{0,	5,	0,	0,	0,	0,	0,	3,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	4,	0,	0,	0,	0,	0,	4,	0,	0,	1,	9,	8,	7,	6,	5,	4,	3,	1,	0},
    	{0,	3,	2,	1,	0,	0,	0,	5,	0,	0,	2,	0,	0,	0,	0,	0,	3,	0,	0,	0},
    	{0,	0,	0,	2,	0,	0,	0,	6,	5,	4,	3,	0,	0,	0,	0,	0,	2,	0,	0,	0},
    	{0,	0,	0,	3,	0,	0,	0,	7,	0,	0,	7,	0,	0,	0,	0,	0,	1,	0,	0,	0},
    	{0,	2,	1,	4,	5,	6,	7,	8,	0,	0,	6,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	0,	0,	0,	0,	0,	0,	9,	0,	0,	1,	9,	8,	7,	6,	5,	4,	3,	2,	0},
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0},
	};;
	
	public MappedBackground() {
		try {
			this.barrier = ImageIO.read(new File("res/8fc992.png"));
			//this.path = ImageIO.read(new File("res/ba69c7.png"));
			this.path1 = ImageIO.read(new File("res/path1.png"));
			this.path2 = ImageIO.read(new File("res/path2.png"));
			this.path3 = ImageIO.read(new File("res/path3.png"));
			this.path4 = ImageIO.read(new File("res/path4.png"));
			this.path5 = ImageIO.read(new File("res/path5.png"));
			this.path6 = ImageIO.read(new File("res/path6.png"));
			this.path7 = ImageIO.read(new File("res/path7.png"));
			this.path8 = ImageIO.read(new File("res/path8.png"));
			this.path9 = ImageIO.read(new File("res/path9.png"));
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		
		maxRows = map.length - 1;
		maxCols = map[0].length - 1;
	}

	@Override
	public Tile getTile(int col, int row) {
		Image image = null;
		
		if (row < 0 || row > maxRows || col < 0 || col > maxCols) {
			image = null;
		} else if (map[row][col] == 0) {
			image = barrier;
		}

		else if(map[row][col] == 1)
		{
			image = path1;
		} else if(map[row][col] == 2) {
			image = path2;
			
		} else if(map[row][col] == 3) {
			image = path3;
			
		} else if(map[row][col] == 4) {
			image = path4;
			
		} else if(map[row][col] == 5) {
			image = path5;
			
		} else if(map[row][col] == 6) {
			image = path6;
			
		} else if(map[row][col] == 7) {
			image = path7;
			
		} else if(map[row][col] == 8) {
			image = path8;
			
		} else if(map[row][col] == 9) {
			image = path9;
			
		}
		else
		{
			image = null;
		}
		
		int x = (col * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2);
		int y = (row * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2);
		
		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);
		
		return newTile;
	}

	public int getHorizontal(int x) {
		//which tile is x sitting at?
		return 0;
	}
	
	@Override
	public int getCol(double x) {
		//which col is x sitting at?
		int col = 0;
		if (TILE_WIDTH != 0) {
			col = (int) (x / TILE_WIDTH);
			if (x < 0) {
				return col - 1;
			}
			else {
				return col;
			}
		}
		else {
			return 0;
		}
	}

	@Override
	public int getRow(double y) {
		//which row is y sitting at?
		int row = 0;
		
		if (TILE_HEIGHT != 0) {
			row = (int) (y / TILE_HEIGHT);
			if (y < 0) {
				return row - 1;
			}
			else {
				return row;
			}
		}
		else {
			return 0;
		}
	}
	
	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		for (int row = 0; row < map[0].length; row++) {
			for (int col = 0; col < map.length; col++) {
				if (map[col][row] == 0) {
					barriers.add(new BarrierSprite((row * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), (col * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), ((row + 1) * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), ((col + 1) * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2)));
				}
			}
		}
		return barriers;
	}
	
	public ArrayList<DisplayableSprite> getPaths() {
		ArrayList<DisplayableSprite> paths = new ArrayList<DisplayableSprite>();
		for (int row = 0; row < map[0].length; row++) {
			for (int col = 0; col < map.length; col++) {
				if (map[col][row] != 0) {
					paths.add(new PathSprite((row * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), (col * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), ((row + 1) * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), ((col + 1) * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), map[col][row]));
				}
			}
		}
		return paths;
	}
	

	@Override
	public double getShiftX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShiftY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setShiftX(double shiftX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShiftY(double shiftY) {
		// TODO Auto-generated method stub
		
	}
}
