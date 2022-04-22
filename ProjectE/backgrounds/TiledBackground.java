import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TiledBackground implements Background {

    private Image green;
    private Image pink;
    private int backgroundWidth = 0;
    private int backgroundHeight = 0;

    public TiledBackground() {
    	try {
    		this.green = ImageIO.read(new File("res/8fc992.png"));
    		this.pink = ImageIO.read(new File("res/ba69c7.png"));
    		backgroundWidth = green.getWidth(null);
    		backgroundHeight = pink.getHeight(null);
    		
    	}
    	catch (IOException e) {
    		//System.out.println(e.toString());
    	}		
    }
	
	public Tile getTile(int col, int row) {
		//row is an index of tiles, with 0 being the at the origin
		//col is an index of tiles, with 0 being the at the origin
		int x = (col * backgroundWidth);
		int y = (row * backgroundHeight);
		Tile newTile = null;
		
		if (((col + row) % 2) == 0 ) {
			newTile = new Tile(green, x, y, backgroundWidth, backgroundHeight, false);
		} else {
			newTile = new Tile(pink, x, y, backgroundWidth, backgroundHeight, false);
		}
			
		
		
		return newTile;
	}
	
	public int getCol(double x) {
		//which col is x sitting at?
		int col = 0;
		if (backgroundWidth != 0) {
			col = (int) (x / backgroundWidth);
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
	
	public int getRow(double y) {
		//which row is y sitting at?
		int row = 0;
		
		if (backgroundHeight != 0) {
			row = (int) (y / backgroundHeight);
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


