import java.util.ArrayList;

public class ShellUniverse implements Universe {

	private boolean complete = false;	
	public DisplayableSprite quack = null;
	private DisplayableSprite finishLine = null;
	private Background background = null;	
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	public static int level = 0;

	public ShellUniverse () {
		
		//tiled background
//		background = new TiledBackground();
//		backgrounds =new ArrayList<Background>();
//		backgrounds.add(background);
		
		//mapped background
		//background = new MappedBackground();
		//backgrounds =new ArrayList<Background>();
		//backgrounds.add(background);
		
		//pond background
//		background = new PondMappedBackground();
//		backgrounds.add(background);
		
		//bath background
		background = new BathMappedBackgroundA();
		backgrounds.add(background);
		
		//barrier sprites
		ArrayList<DisplayableSprite> barriers = ((BathMappedBackgroundA)background).getBarriers();
		((BathMappedBackgroundA) background).getBarriers();
		sprites.addAll(barriers);
		
		//path sprites
		ArrayList<DisplayableSprite> paths = ((BathMappedBackgroundA)background).getPaths();
		((BathMappedBackgroundA) background).getPaths();
		sprites.addAll(paths);

		this.setXCenter(0);
		this.setYCenter(0);
		quack = new QuackSprite(-345, -243);
		finishLine = new FinishLine(340, 265);
		sprites.add(finishLine);
		sprites.add(quack);
			
	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}	

	public DisplayableSprite getPlayer1() {
		return quack;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}		

	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		if (quack.getStatus()) {
			quack.setStatus(false);
			quack.list.reset();
			level++;
			updateBackground();
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		
	}
	
	private void updateBackground() {
		backgrounds = new ArrayList<Background>();
		
		//reset :
		//barrier sprites
		//ArrayList<DisplayableSprite> barriers = ((BathMappedBackgroundA)background).getBarriers();
		//((BathMappedBackgroundA) background).getBarriers();
		//sprites.addAll(barriers);
		
		//path sprites
		//ArrayList<DisplayableSprite> paths = ((BathMappedBackgroundA)background).getPaths();
		//((BathMappedBackgroundA) background).getPaths();
		//sprites.addAll(paths);
		
		//move or remake quack
		
		//update visual
		
		if (level == 1) {

		} else if (level == 2) {

		} else if (level == 3) {

		} else if (level == 4) {

		} else if (level == 5) {

		} else if (level == 6) {

		} else if (level == 7) {

		} else if (level == 8) {

		} else if (level == 9) {

		} else if (level == 10) {

		} else {
			//end sprite created?
			setComplete(true);
		}
	}

	public String toString() {
		//return "ShellUniverse";
		return null;
	}

}
