
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class BathMappedBackgroundC implements Background {
	
	public static int TILE_WIDTH = AnimationFrame.SCREEN_WIDTH / 20;
    public static int TILE_HEIGHT = AnimationFrame.SCREEN_HEIGHT / 15;
	//public static int TILE_HEIGHT = TILE_WIDTH;
    
    private Image barrier;
    //private Image path;
    
    //barriers 
    private Image bubbles1;
    private Image bubbles2;
    private Image bubbles3;
    
    
    //paths
    private Image water1;
    private Image water2; 
    private Image water3;
    private Image water4;
    
    private int maxCols = 0;
    private int maxRows = 0;    
    
    private int map[][] = new int[][] {
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	1,	1,	0,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0,	1,	1,	1,	0,	0},
    	{0,	0,	1,	1,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0},
    	{0,	0,	0,	1,	1,	1,	1,	1,	1,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	0},
    	{0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	1,	1,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	0},
    	{0,	1,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0},
    	{0,	1,	0,	1,	1,	1,	1,	0,	1,	0,	0,	0,	1,	0,	0,	0,	0,	0,	1,	0},
    	{0,	1,	0,	0,	1,	0,	1,	0,	1,	0,	0,	0,	1,	1,	1,	1,	0,	0,	1,	0},
    	{0,	1,	0,	0,	1,	0,	1,	0,	1,	0,	0,	0,	1,	0,	0,	1,	0,	0,	1,	0},
    	{0,	1,	0,	0,	1,	0,	1,	0,	1,	1,	1,	0,	1,	0,	0,	1,	0,	0,	1,	0},
    	{0,	1,	1,	1,	1,	0,	1,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0,	0,	1,	0},
    	{0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	0,	0,	1,	1,	0,	1,	0},
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0},
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
	};;
	
	public BathMappedBackgroundC() {
		try {
		    
		    this.water1 = ImageIO.read(new File("res/bathSprites/water1.png"));
		    this.water2 = ImageIO.read(new File("res/bathSprites/water2.png"));
		    this.water3 = ImageIO.read(new File("res/bathSprites/water3.png"));
		    this.water4 = ImageIO.read(new File("res/bathSprites/water4.png"));
		    this.bubbles1 = ImageIO.read(new File("res/bathSprites/bubbles1.png"));		    
		    this.bubbles2 = ImageIO.read(new File("res/bathSprites/bubbles2.png"));
		    this.bubbles3 = ImageIO.read(new File("res/bathSprites/bubbles3.png"));

		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		
		maxRows = map[0].length - 1;
		maxCols = map.length - 1;
	}
	
	public Image getImage(int col, int row) {
		Image image = null;
		
		if (row < 0 || row > maxRows || col < 0 || col > maxCols) {
			image = null;
		} else if (map[col][row] == 0) {
//			image = bubbles1;

			
			int var = (int) (Math.random() * 3 + 1);
			
			System.out.println(var);
			if (var == 1) {
				image = bubbles1;
				//image = null;
			} else if (var == 2) {
				image = bubbles2;
				//image = null;
			} else if (var == 3) {
				image = bubbles3;
				//image = null;
			}
		} else if (map[col][row] == 1) {
//			image = water;
			
			int var = (int) (Math.random() * 4 + 1);
			if (var == 1) {
				image = water1;
			} else if (var == 2) {
				image = water2;
			} else if (var == 3) {
				image = water3;
			} else if (var == 4) {
				image = water4;
			}
		} else {
			try {
				image = ImageIO.read(new File("res/simple-sprite.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		return image;
	}

	@Override
	public Tile getTile(int col, int row) {
		Image image = getImage(col, row);
		
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
				if (map[col][row] == 0 || map[col][row] == 10) {
					barriers.add(new BarrierSprite((row * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), (col * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), ((row + 1) * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), ((col + 1) * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), getImage(col, row)));
				}
			}
		}
		return barriers;
	}
	
	public ArrayList<DisplayableSprite> getPaths() {
		ArrayList<DisplayableSprite> paths = new ArrayList<DisplayableSprite>();
		for (int row = 0; row < map[0].length; row++) {
			for (int col = 0; col < map.length; col++) {
				if (map[col][row] == 1) {
					paths.add(new PathSprite((row * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), (col * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), ((row + 1) * TILE_WIDTH) - (AnimationFrame.SCREEN_WIDTH / 2), ((col + 1) * TILE_HEIGHT) - (AnimationFrame.SCREEN_HEIGHT / 2), getImage(col, row)));
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
