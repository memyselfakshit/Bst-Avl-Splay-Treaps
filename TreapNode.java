import java.util.Random;

public class TreapNode {
	
	int key;
	int priority;
	TreapNode left;
	TreapNode right;
	static int total = 1000;
	
	public TreapNode(int key)
	{
		this.key = key;
		this.left = null;
		this.right = null;
		priority = new Random().nextInt(total);
	}
}
