
public class Main {
	
	public static AnimationFrame frame;
	
	public static void main(String[] args)
	{
		ShellAnimation animation = new ShellAnimation();
		frame = new AnimationFrame((Animation)animation);
		frame.start();
	}
}

