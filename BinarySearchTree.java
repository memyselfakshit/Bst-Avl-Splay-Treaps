import java.util.*;

public class BinarySearchTree {
	
	TreeNode root;
	static List<Integer> inorder = new ArrayList<Integer>();
	public BinarySearchTree()
	{
		root = null;
	}
	
	public void Insert(int key)
	{
		root = InsertRec(root, key);
	}
	
	public void Inorder()
	{
		InorderRec(root);
	}
	
	public boolean Search(int key)
	{
		return SearchRec(root, key)==null?false:true;
	}
	
	public void Delete(int key)
	{
		root = DeleteRec(root, key);
		return;
	}
	
	public TreeNode DeleteRec(TreeNode root, int key)
	{
	        if (root == null)  return root; 
	        
	        if (key < root.key) 
	            root.left = DeleteRec(root.left, key); 
	        else if (key > root.key) 
	            root.right = DeleteRec(root.right, key); 
	        else
	        { 
	            if (root.left == null) 
	                return root.right; 
	            else if (root.right == null) 
	                return root.left; 
	  
	            root.key = InOrderSuccessor(root.right); 
	            
	            root.right = DeleteRec(root.right, root.key); 
	        } 
	  
	        return root; 
	    
	}
	
	public int InOrderSuccessor(TreeNode root)
	{
		int min = root.key;
		while(root.left != null)
		{
			min = root.left.key;
			root = root.left;
		}
		
		return min;
	}
	
	public TreeNode SearchRec(TreeNode root, int key)
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
	
	public TreeNode InsertRec(TreeNode root, int key)
	{
		if(root==null)
		{
			root = new TreeNode(key);
			return root;
		}
		
		if(root.key > key)
			root.left = InsertRec(root.left, key);
		else if(root.key <  key)
			root.right = InsertRec(root.right, key);
		
		return root;
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
		BinarySearchTree bst = new BinarySearchTree();
		double createEndTime = System.currentTimeMillis();
		System.out.println("Total Time For BST Creation: " + (createEndTime - createStartTime));
              
        double insertStartTime = System.currentTimeMillis();
        int i = 0;
        for (i = 0; i < total; i++)
        	bst.Insert(rn.nextInt(total));
        double insertEndTime = System.currentTimeMillis();
		System.out.println("Total Time For BST Insertion: " + (insertEndTime - insertStartTime));
        
		System.out.println("Inorder After Insertion");
		bst.Inorder();
		System.out.println(inorder.size());
		
		double searchStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	System.out.print(bst.Search(rn.nextInt(total)));
        System.out.println("");
        double searchEndTime = System.currentTimeMillis();
		System.out.println("Total Time For BST Search: " + (searchEndTime - searchStartTime));
       
		double deleteStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	bst.Delete(rn.nextInt(total));
        double deleteEndTime = System.currentTimeMillis();
		System.out.println("Total Time For BST Delete: " + (deleteEndTime - deleteStartTime));
		
		double endTime = System.currentTimeMillis();
		System.out.println("Total Time For BST All Operation: " + (endTime - startTime));
		bst=null;
		inorder = new ArrayList<>();
	}

}
