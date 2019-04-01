import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SplayTree {

	TreeNode root;
	static List<Integer> inorder = new ArrayList<Integer>();
	public SplayTree()
	{
		root = null;
	}
	
	public TreeNode rotateRight(TreeNode root)
	{
		TreeNode x = root.left;
		root.left = x.right;
		x.right = root;
		
		return x;
	}
	
	public TreeNode rotateLeft(TreeNode root)
	{
		TreeNode x = root.right;
		root.right = x.left;
		x.left = root;
		
		return x;
	}
	
	public TreeNode Splay(TreeNode root, int key)
	{
		if(root == null)
			return root;
		
		if(root.key > key)
		{
			if(root.left == null)
				return root;
			
			if(root.left.key > key)
			{
				root.left.left = Splay(root.left.left, key);
				root = rotateRight(root);
			}
			else if(root.left.key < key)
			{
				root.left.right = Splay(root.left.right, key);
				
				if(root.left.right != null)
					root.left = rotateLeft(root.left);
			}
			
			return (root.left == null)? root: rotateRight(root);
		}
		else if(root.key < key)
		{
			if(root.right == null)
				return root;
			if(root.right.key > key)
			{
				root.right.left = Splay(root.right.left, key);
				
				if(root.right.left != null)
					root.right = rotateRight(root.right);
			}
			else if(root.right.key < key)
			{
				root.right.right = Splay(root.right.right, key);
				
				root = rotateLeft(root);
			}
			
			return (root.right == null)? root: rotateLeft(root);
		}
		else
			return root;
	}
	
	public void Insert(int key)
	{
		root = InsertRec(root, key);
	}
	
	public TreeNode InsertRec(TreeNode root, int key)
	{
		if(root == null)
		{
			root = new TreeNode(key);
			return root;
		}
		
		root = Splay(root, key);
		
		if(root.key > key)
		{
			TreeNode node = new TreeNode(key);
			node.left = root.left;
			node.right = root;
			root.left = null;
			root = node;
		}
		else if(root.key < key)
		{
			TreeNode node = new TreeNode(key);
			node.right = root.right;
			node.left = root;
			root.right = null;
			root = node;
		}
		else
			root.key = key;
			
		return root;
	}
	
	public boolean Search(int key)
	{
		root =  SearchRec(root, key);
		
		return (root.key == key)? true : false;
	}
	
	public TreeNode SearchRec(TreeNode root, int key)
	{
		return Splay(root,key);
	}
	
	public void Delete(int key)
	{
		root = DeleteRec(root, key);
	}
	
	public TreeNode DeleteRec(TreeNode root, int key)
	{
		if(root == null)
			return root;
		
		root = Splay(root, key);
		
		if(root.key == key)
		{
			if(root.left == null)
				root = root.right;
			else
			{
				TreeNode x = root.right;
				root = root.left;
				Splay(root, key);
				root.right = x;
			}
		}
		else
		{
			System.out.println("key not present in the tree to delete");
		}
		return root;
	}
	
	public void Inorder()
	{
		InorderRec(root);
	}
	
	public void InorderRec(TreeNode root)
	{
		if(root==null)
			return;
		
		InorderRec(root.left);
		inorder.add(root.key);
		InorderRec(root.right);
	}
	
	public static void main(String[] args)
	{
		double startTime = System.currentTimeMillis();
		Random rn = new Random();
		int total = TreeNode.total;
		
		double createStartTime = System.currentTimeMillis();
		SplayTree splay = new SplayTree();
		double createEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Splay Creation: " + (createEndTime - createStartTime));
              
        double insertStartTime = System.currentTimeMillis();
        int i = 0;
        for (i = 0; i < total; i++)
        	splay.Insert(rn.nextInt(total));
        double insertEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Splay Insertion: " + (insertEndTime - insertStartTime));
        
		System.out.println("Inorder After Insertion");
		splay.Inorder();
		System.out.println(inorder.size());
		
		double searchStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	System.out.print(splay.Search(rn.nextInt(total)));
        System.out.println("");
        double searchEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Splay Search: " + (searchEndTime - searchStartTime));
       
		double deleteStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	splay.Delete(rn.nextInt(total));
        double deleteEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Splay Delete: " + (deleteEndTime - deleteStartTime));
		
		double endTime = System.currentTimeMillis();
		System.out.println("Total Time For Splay All Operation: " + (endTime - startTime));
		splay=null;
		inorder = new ArrayList<>();
	}
	
}
