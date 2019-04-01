import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Treap {
	
	TreapNode root;
	static List<int[]> inorder = new ArrayList<int[]>();
	
	public Treap()
	{
		root = null;
	}
	
	public TreapNode rotateRight(TreapNode root)
	{
		TreapNode x = root.left;
		TreapNode y = root.left.right;
		
		x.right = root;
		root.left = y;
		
		return x;
	}
	
	public TreapNode rotateLeft(TreapNode root)
	{
		TreapNode x = root.right;
		TreapNode y = root.right.left;
		
		x.left = root;
		root.right = y;
		
		return x;
	}
	
	public void Insert(int key)
	{
		root = InsertRec(root, key);
	}
	
	
	public TreapNode InsertRec(TreapNode root, int key)
	{
		if(root==null)
		{
			root = new TreapNode(key);
			return root;
		}
		
		if(root.key > key)
		{
			root.left = InsertRec(root.left,key);
			if(root.left!=null && root.left.priority > root.priority)
			{
				root = rotateRight(root);
			}
		}
		else
		{
			root.right = InsertRec(root.right,key);
			if(root.right!=null && root.right.priority > root.priority)
			{
				root = rotateLeft(root);
			}
		}
		
		return root;
	}
	
	public boolean Search(int key)
	{
		return SearchRec(root, key)==null?false:true;
	}
	
	public TreapNode SearchRec(TreapNode root, int key)
	{
		if(root==null)
			return null;
		
		else if(root.key == key)
			return root;
		
		else if(root.key > key)
			return SearchRec(root.left, key);
		else
			return SearchRec(root.right, key);
		
	}
	
	public void Delete(int key)
	{
		root = DeleteRec(root, key);
		return;
	}
	
	
	public TreapNode DeleteRec(TreapNode root, int key)
	{
	        if (root == null)  return root; 
	        
	        if (key < root.key) 
	            root.left = DeleteRec(root.left, key); 
	        else if (key > root.key) 
	            root.right = DeleteRec(root.right, key); 
	        else
	        { 
	        	if(root.left == null && root.right == null)
	        		root = null;
	        	else if(root.left != null && root.right != null)
	        	{
	        		if(root.left.priority < root.right.priority)
	        		{
	        			root = rotateLeft(root);
	        			root.left = DeleteRec(root.left, key);
	        		}
	        		else
	        		{
	        			root = rotateRight(root);
	        			root.right = DeleteRec(root.right, key);
	        		}
	        	}
	        	else
	        	{
	        		root = (root.left!=null)? root.left : root.right;
	        	}
	        } 
	  
	        return root; 
	    
	}
	
	public void Inorder()
	{
		InorderRec(root);
	}
	
	public void InorderRec(TreapNode root)
	{
		if(root==null)
			return;
		
		InorderRec(root.left);
		inorder.add(new int[]{root.key,root.priority});
		InorderRec(root.right);
	}
	
	public static void main(String[] args)
	{
		double startTime = System.currentTimeMillis();
		Random rn = new Random();
		int total = TreeNode.total;
		
		double createStartTime = System.currentTimeMillis();
		Treap treap = new Treap();
		double createEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Treap Creation: " + (createEndTime - createStartTime));
              
        double insertStartTime = System.currentTimeMillis();
        int i = 0;
        for (i = 0; i < total; i++)
        	treap.Insert(rn.nextInt(total));
        double insertEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Treap Insertion: " + (insertEndTime - insertStartTime));
        
		System.out.println("Inorder After Insertion");
		treap.Inorder();
		System.out.println(inorder.size());
		
		double searchStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	System.out.print(treap.Search(rn.nextInt(total)));
        System.out.println("");
        double searchEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Treap Search: " + (searchEndTime - searchStartTime));
       
		double deleteStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	treap.Delete(rn.nextInt(total));
        double deleteEndTime = System.currentTimeMillis();
		System.out.println("Total Time For Treap Delete: " + (deleteEndTime - deleteStartTime));
		
		double endTime = System.currentTimeMillis();
		System.out.println("Total Time For Treap All Operation: " + (endTime - startTime));
		treap=null;
		inorder = new ArrayList<>();
	}
	
	
}
