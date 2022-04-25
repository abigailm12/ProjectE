
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MappedBackground implements Background {
	
	protected static int TILE_WIDTH = AnimationFrame.SCREEN_WIDTH / 26;
    protected static int TILE_HEIGHT = AnimationFrame.SCREEN_HEIGHT / 32;
    
    private Image barrier;
    private Image path;
    
    private int maxCols = 0;
    private int maxRows = 0;    
	
	private int map[][] = new int[][] {
		{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,0,0},
		{0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0},
		{0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,1,0,0,0,0,1,0,0},
		{0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,0,1,1,1,0,0},
		{0,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0},
		{0,0,0,1,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,1,0},
		{0,1,0,1,1,1,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,0},
		{0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,1,1,1,0,1,0,0},
		{0,1,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0},
		{0,1,0,1,0,0,1,0,1,0,0,1,1,0,1,0,0,0,0,1,0,0,1,1,1,0},
		{0,1,1,1,1,0,1,0,1,0,0,1,0,0,1,1,1,1,1,1,1,1,1,0,1,0},
		{0,0,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
		{0,0,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
		{0,1,1,1,1,0,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
		{0,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
		{0,1,1,1,1,0,1,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,1,0},
		{0,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,0},
		{0,1,1,1,1,0,1,0,1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,1,0},
		{0,1,0,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,0,0},
		{0,1,1,1,1,0,1,0,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,1,0},
		{0,1,0,0,1,0,1,0,1,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0},
		{0,1,0,0,1,0,1,0,1,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0},
		{0,1,1,1,1,0,1,0,1,1,1,1,1,0,0,1,1,1,1,1,0,1,0,0,0,0},
		{0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0},
		{0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,0},
		{0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,1,1,1,0,0},
		{0,0,1,0,1,0,0,0,0,0,0,1,1,1,1,0,0,1,0,1,0,0,0,0,0,0},
		{0,0,1,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,0},
		{0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,1,1,0,0,0,1,0,0,1,0},
		{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0}
	};

	@Override
	public Tile getTile(int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCol(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRow(double y) {
		// TODO Auto-generated method stub
		return 0;
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
