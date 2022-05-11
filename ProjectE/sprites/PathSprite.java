import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PathSprite implements DisplayableSprite {

	private static Image image;
	private boolean visible = true;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	
	public PathSprite(double minX, double minY, double maxX, double maxY, int colour) {
		
		if (image == null && visible) {
			if(colour == 1){
				try {
					image = ImageIO.read(new File("res/path1.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(colour == 2){
				try {
					image = ImageIO.read(new File("res/path2.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(colour == 3){
				try {
					image = ImageIO.read(new File("res/path3.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}else if(colour == 4){
				try {
					image = ImageIO.read(new File("res/path4.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}else if(colour == 5){
				try {
					image = ImageIO.read(new File("res/path5.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}else if(colour == 6){
				try {
					image = ImageIO.read(new File("res/path6.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}else if(colour == 7){
				try {
					image = ImageIO.read(new File("res/path7.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}else if(colour == 8){
				try {
					image = ImageIO.read(new File("res/path8.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}else if(colour == 9){
				try {
					image = ImageIO.read(new File("res/path9.PNG"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		this.centerX = (minX + maxX) / 2;
		this.centerY = (minY + maxY) / 2;
		this.width = maxX - minX;
		this.height = maxY - minY;
		
	}
	
	public void solve() {}
	

	public Image getImage() {
		return image;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return this.visible;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return dispose;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
			
	}


	@Override
	public void setDispose(boolean dispose) {
		// TODO Auto-generated method stub
		
	}

}
