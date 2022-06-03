
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PondMappedBackground implements Background {
	
	public static int TILE_WIDTH = AnimationFrame.SCREEN_WIDTH / 20;
    public static int TILE_HEIGHT = AnimationFrame.SCREEN_HEIGHT / 15;
	//public static int TILE_HEIGHT = TILE_WIDTH;
    
    private Image barrier;
    //private Image path;
    private Image path1;
    private Image path2;
    private Image path3;
    private Image path4;
    private Image path5;
    private Image path6;
    private Image path7;
    private Image path8;
    private Image path9;
    
    //barriers
    private Image grass;
    private Image oneSidedGrass;
    private Image twoSidedGrassPar;
    private Image twoSidedGrassPerp;
    private Image threeSidedGrass;
    private Image fourSidedGrassAlternate1;
    private Image fourSidedGrassAlternate2;
    private Image fourSidedGrassAlternate3;
    
    //paths
    private Image waterAlternate1;
    private Image waterAlternate2;
    private Image waterAlternate3;
    
    
 
    
    private int maxCols = 0;
    private int maxRows = 0;    
    
    private int map[][] = new int[][] {
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0},
    	{0,	0,	0,	1,	0,	0,	0,	0,	1,	1,	1,	1,	1,	0,	0,	0,	0,	0,	1,	0},
    	{0,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	1,	1,	1,	1,	0},
    	{0,	1,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0,	1,	0,	0,	0,	0},
    	{0,	1,	0,	1,	1,	1,	1,	1,	1,	0,	0,	0,	1,	0,	0,	1,	0,	0,	0,	0},
    	{0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	1,	1,	1,	0,	0},
    	{0,	1,	1,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	0,	0,	1,	0,	1,	0,	0},
    	{0,	0,	1,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0},
    	{0,	0,	1,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0,	1,	0,	0},
    	{0,	0,	1,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0},
    	{0,	0,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
    	{0,	0,	0,	0,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	0},
    	{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0},
	};;
	
	public PondMappedBackground() {
		try {
			
		    this.grass = ImageIO.read(new File("res/pondSprites/Path.png"));
		    this.oneSidedGrass = ImageIO.read(new File("res/pondSprites/1SidedPath.png"));
		    this.twoSidedGrassPar = ImageIO.read(new File("res/pondSprites/2SidedPathPar.png"));
		    this.twoSidedGrassPerp = ImageIO.read(new File("res/pondSprites/2SidedPathPerp.png"));
		    this.threeSidedGrass = ImageIO.read(new File("res/pondSprites/3SidedPath.png"));
		    this.fourSidedGrassAlternate1 = ImageIO.read(new File("res/pondSprites/4SidedPathAlternate-1.png"));
		    this.fourSidedGrassAlternate2 = ImageIO.read(new File("res/pondSprites/4SidedPathAlternate-2.png"));
		    this.fourSidedGrassAlternate3 = ImageIO.read(new File("res/pondSprites/4SidedPathAlternate-3.png"));
		    
		    this.waterAlternate1 = ImageIO.read(new File("res/pondSprites/water-alternate-1.png"));
		    this.waterAlternate2 = ImageIO.read(new File("res/pondSprites/water-alternate-2.png"));
		    this.waterAlternate3 = ImageIO.read(new File("res/pondSprites/water-alternate-3.png"));
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
			image = grass;
		} else if (map[col][row] == 1) {
			int var = (int) (Math.random()*3 + 1);
			if (var == 1) {
				image = waterAlternate1;
			} else if (var == 2) {
				image = waterAlternate2;
			} else if (var == 3) {
				image = waterAlternate3;
			}
		}
		else
		{
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
				if (map[col][row] != 1) {
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
