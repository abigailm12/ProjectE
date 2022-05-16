import java.util.ArrayList;

public class ShellUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private Background background = null;	
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();

	public ShellUniverse () {
		
		//tiled background
//		background = new TiledBackground();
//		backgrounds =new ArrayList<Background>();
//		backgrounds.add(background);
		
		//mapped background
		background = new MappedBackground();
		backgrounds =new ArrayList<Background>();
		backgrounds.add(background);
		
		//barrier sprites
		ArrayList<DisplayableSprite> barriers = ((MappedBackground)background).getBarriers();
		((MappedBackground) background).getBarriers();
		sprites.addAll(barriers);
		
		//path sprites
		ArrayList<DisplayableSprite> paths = ((MappedBackground)background).getPaths();
		((MappedBackground) background).getPaths();
		sprites.addAll(paths);

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new QuackSprite(-345, -243);
		sprites.add(player1);
			
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
		return player1;
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
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		
	}

	public String toString() {
		//return "ShellUniverse";
		return null;
	}

}
