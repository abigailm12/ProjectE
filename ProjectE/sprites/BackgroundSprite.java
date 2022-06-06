import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundSprite implements DisplayableSprite {
	
	private Image image;
	private Image tubA;
	private Image tubB;
	private Image tubC;
	private Image pondA;
	private Image pondB;
	private Image pondC;
	private Image swampA;
	private Image swampB;
	private Image swampC;
	private Image fire;
	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	
	public BackgroundSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (tubA == null) {
			try {
				tubA = ImageIO.read(new File("res/backgrounds/*******.png"));
				tubB = ImageIO.read(new File("res/backgrounds/*******.png"));
				tubC = ImageIO.read(new File("res/backgrounds/*******.png"));
				pondA = ImageIO.read(new File("res/backgrounds/*******.png"));
				pondB = ImageIO.read(new File("res/backgrounds/*******.png"));
				pondC = ImageIO.read(new File("res/backgrounds/*******.png"));
				swampA = ImageIO.read(new File("res/backgrounds/*******.png"));
				swampB = ImageIO.read(new File("res/backgrounds/*******.png"));
				swampC = ImageIO.read(new File("res/backgrounds/*******.png"));
				fire = ImageIO.read(new File("res/backgrounds/*******.png"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Image getImage() {
		return image;
	}

	public boolean getVisible() {
		return true;
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
	}

	public double getCenterY() {
		return centerY;
	}

	public boolean getDispose() {
		return dispose;
	}

	public void setDispose(boolean dispose) {
		this.dispose = dispose;
		
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		int level = ShellUniverse.level;
		
		if (level == 1) {
			image = tubA;
		} else if (level == 2) {
			image = tubB;
		} else if (level == 3) {
			image = tubC;
		} else if (level == 4) {
			image = pondA;
		} else if (level == 5) {
			image = pondB;
		} else if (level == 6) {
			image = pondC;
		} else if (level == 7) {
			image = swampA;
		} else if (level == 8) {
			image = swampB;
		} else if (level == 9) {
			image = swampC;
		} else {
			image = fire;
		}
		
	}

	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatus(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
