import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FaucetSprite implements DisplayableSprite {

	private static final int FRAMES = 6;
	private static int framesPerSecond = 25;
	private static int millisecondsPerFrame = 4000 / framesPerSecond;
	private Image image;
	private Image[] images;
	private boolean visible = true;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 75;
	private double height = 75;
	private boolean dispose = false;	
	private long elapsedTime = 0;
	
	public FaucetSprite(double centerX, double centerY) {
		
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (images == null) {
			try {				
				images = new Image[FRAMES];
				for (int i = 0; i <= FRAMES; i++) {
					String path = String.format("res/faucetSprites/faucet-%d.png", i);
					images[i] = ImageIO.read(new File(path));
				}
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}	
		}
		
	}
	
	public boolean getStatus() {
		return false;
	}
	
	public void solve() {}
	

	public Image getImage() {
		long frame = elapsedTime / millisecondsPerFrame;
		int index = (int) frame % FRAMES;
		image = images[index];
		return image;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//DISPLAYABLE
	
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
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return dispose;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		elapsedTime += actual_delta_time;
	}


	@Override
	public void setDispose(boolean dispose) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
