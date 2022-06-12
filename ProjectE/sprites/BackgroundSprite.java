import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundSprite implements DisplayableSprite {
	
	private Image start;
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
	private Image end;
	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 800;
	private double height = 600;
	private boolean dispose = false;
	private boolean visible = true;
	private int level = 0;
	
	public BackgroundSprite(double centerX, double centerY, int level) {
		System.out.println("made bg at level " + level);
		this.centerX = centerX;
		this.centerY = centerY;
		this.level = level;	
		
		if (tubA == null) {
			try {
				start = ImageIO.read(new File("res/backgrounds/start1.png"));
				tubA = ImageIO.read(new File("res/backgrounds/bath1.png"));
				tubB = ImageIO.read(new File("res/backgrounds/bath2.png"));
				tubC = ImageIO.read(new File("res/backgrounds/bath3.png"));
				pondA = ImageIO.read(new File("res/backgrounds/pondA.png"));
				pondB = ImageIO.read(new File("res/backgrounds/pondB.png"));
				pondC = ImageIO.read(new File("res/backgrounds/pondC.png"));
				swampA = ImageIO.read(new File("res/backgrounds/swamp1.png"));
				swampB = ImageIO.read(new File("res/backgrounds/swamp2.png"));
				swampC = ImageIO.read(new File("res/backgrounds/swamp3.png"));
				fire = ImageIO.read(new File("res/backgrounds/fire.png"));
				end = ImageIO.read(new File("res/backgrounds/end1.png"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Image getImage() {
		if (level == 0) {
			image = start;
		} else if (level == 1) {
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
		} else if (level == 10) {
			image = fire;
		} else {
			image = end;
			System.out.println("level was below 1 or above 10:/");
		}
		return image;
	}

	public boolean getVisible() {
		return visible;
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

	@Override
	public void setVisible(boolean b) {
		this.visible = b;
		
	}

}
