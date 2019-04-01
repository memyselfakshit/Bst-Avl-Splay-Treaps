
public class TreeNode {
	TreeNode left;
	TreeNode right;
	int key;
	int height;
	static int total = 1000;
	int color;
	int size;
	
	public TreeNode(int key)
	{
		this.key = key;
		left = right = null;
		height = 1;
	}
	
	public TreeNode()
	{
		left = null;
		right= null;
	}

}
