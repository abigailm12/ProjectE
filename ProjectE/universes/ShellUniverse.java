import java.util.ArrayList;

public class ShellUniverse implements Universe {

	private boolean complete = false;	
	public DisplayableSprite quack = null;
	private DisplayableSprite finishLine = null;
	private Background background = null;	
	private DisplayableSprite backgroundSprite = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	public static int level = 1;

	public ShellUniverse () {
		
		updateBarrierPathSprites();
			
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
			updateBarrierPathSprites();
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 		
		
	}
	
	private void updateBarrierPathSprites() {
		
		background = new MappedBackground(level);
		sprites = new ArrayList<DisplayableSprite>();
		if (quack != null) {
			quack.setDispose(true);
			quack.setVisible(false);
			quack = null;
			backgroundSprite.setVisible(false);
		}
		
		//mapped background (purely visual)
		
		backgroundSprite = new BackgroundSprite(0, 0, level);
		sprites.add(backgroundSprite);
		
		
		
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
		quack = new QuackSprite(-345, -243);
		finishLine = new FinishLine(340, 265);
		sprites.add(finishLine);
		sprites.add(quack);
		
	}

	public String toString() {
		//return "ShellUniverse";
		return null;
	}

}
