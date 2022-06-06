
public class Main {
	
	public static AnimationFrame frame;
	private static AudioPlayer audio = new AudioPlayer();
	
	public static void main(String[] args)
	{
		ShellAnimation animation = new ShellAnimation();
		frame = new AnimationFrame((Animation)animation);
		frame.start();
		audio.play("res/sounds/quackSound.wav");
	}
}

