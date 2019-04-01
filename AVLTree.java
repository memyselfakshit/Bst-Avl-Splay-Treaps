import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AVLTree {

	TreeNode root;
	static List<Integer> inorder = new ArrayList<Integer>();
	public AVLTree()
	{
		root=null;
	}
	
	public int height(TreeNode root)
	{
		if(root==null)
			return 0;
		
		return root.height;
	}
	
	public int getBalanceFactor(TreeNode root)
	{
		if(root==null)
			return 0;
		
		return (height(root.left) - height(root.right));
	}
	
	public void Insert(int key)
	{
		root = InsertRec(root, key);
	}
	
	
	 TreeNode rightRotate(TreeNode y) { 
	        TreeNode x = y.left; 
	        TreeNode T2 = x.right; 
	        
	        x.right = y; 
	        y.left = T2; 
	  
	        y.height = Math.max(height(y.left), height(y.right)) + 1; 
	        x.height = Math.max(height(x.left), height(x.right)) + 1; 

	        return x; 
	    } 
	  
	    TreeNode leftRotate(TreeNode x) { 
	        TreeNode y = x.right; 
	        TreeNode T2 = y.left; 
	  
	        y.left = x; 
	        x.right = T2; 
	  
	        x.height = Math.max(height(x.left), height(x.right)) + 1; 
	        y.height = Math.max(height(y.left), height(y.right)) + 1; 
	  
	        return y; 
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
		else
			return root;
		
		root.height = 1 + Math.max(height(root.left), height(root.right));
		
		int balanceFactor = getBalanceFactor(root);
		
		// Left Left Case
		if (balanceFactor > 1 && key < root.left.key) 
            return rightRotate(root); 
  
        // Right Right Case 
        if (balanceFactor < -1 && key > root.right.key) 
            return leftRotate(root); 
  
        // Left Right Case 
        if (balanceFactor > 1 && key > root.left.key) { 
            root.left = leftRotate(root.left); 
            return rightRotate(root); 
        } 
  
        // Right Left Case 
        if (balanceFactor < -1 && key < root.right.key) { 
            root.right = rightRotate(root.right); 
            return leftRotate(root); 
        } 
  
        return root; 
	}
	
	public void Inorder()
	{
		InorderRec(root);
		System.out.println("");
	}
	
	public void InorderRec(TreeNode root)
	{
		if(root==null)
			return;
		
		InorderRec(root.left);
		inorder.add(root.key);
		InorderRec(root.right);
	}
	
	public boolean Search(int key)
	{
		return SearchRec(root, key)==null?false:true;
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
	
	public void Delete(int key)
	{
		root = DeleteRec(root, key);
		return;
	}
	
	public TreeNode DeleteRec(TreeNode root, int key)
	{
		 if (root == null)  
	            return root;  
	  
	        if (key < root.key)  
	            root.left = DeleteRec(root.left, key);  
	  
	        else if (key > root.key)  
	            root.right = DeleteRec(root.right, key);  
	  
	        else
	        {  
	  
	            if ((root.left == null) || (root.right == null))  
	            {  
	                TreeNode temp = null;  
	                if (temp == root.left)  
	                    temp = root.right;  
	                else
	                    temp = root.left;  
	  
	                if (temp == null)  
	                {  
	                    temp = root;  
	                    root = null;  
	                }  
	                else 
	                    root = temp; 
	            }  
	            else
	            {  
	   
	            	 root.key  = InOrderSuccessor(root.right);  
	            	 
	                root.right = DeleteRec(root.right, root.key);  
	            }  
	        }  
	   
	        if (root == null)  
	            return root;  
	  
	        root.height = Math.max(height(root.left), height(root.right)) + 1;  
	  
	        int balance = getBalanceFactor(root);  
	   
	        // Left Left Case  
	        if (balance > 1 && getBalanceFactor(root.left) >= 0)  
	            return rightRotate(root);  
	  
	        // Left Right Case  
	        if (balance > 1 && getBalanceFactor(root.left) < 0)  
	        {  
	            root.left = leftRotate(root.left);  
	            return rightRotate(root);  
	        }  
	  
	        // Right Right Case  
	        if (balance < -1 && getBalanceFactor(root.right) <= 0)  
	            return leftRotate(root);  
	  
	        // Right Left Case  
	        if (balance < -1 && getBalanceFactor(root.right) > 0)  
	        {  
	            root.right = rightRotate(root.right);  
	            return leftRotate(root);  
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
	
	
	public static void main(String[] args)
	{

		double startTime = System.currentTimeMillis();
		Random rn = new Random();
		int total = TreeNode.total;
		
		double createStartTime = System.currentTimeMillis();
		AVLTree avl = new AVLTree();
		double createEndTime = System.currentTimeMillis();
		System.out.println("Total Time For AVL Creation: " + (createEndTime - createStartTime));
              
        double insertStartTime = System.currentTimeMillis();
        int i = 0;
        for (i = 0; i < total; i++)
        	avl.Insert(rn.nextInt(total));
        double insertEndTime = System.currentTimeMillis();
		System.out.println("Total Time For AVL Insertion: " + (insertEndTime - insertStartTime));
        
		System.out.println("Inorder After Insertion");
		avl.Inorder();
		System.out.println(inorder.size());
		
		double searchStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	System.out.print(avl.Search(rn.nextInt(total)));
        System.out.println("");
        double searchEndTime = System.currentTimeMillis();
		System.out.println("Total Time For AVL Search: " + (searchEndTime - searchStartTime));
       
		double deleteStartTime = System.currentTimeMillis();
        for (i = 0; i < total; i++)
        	avl.Delete(rn.nextInt(total));
        double deleteEndTime = System.currentTimeMillis();
		System.out.println("Total Time For AVL Delete: " + (deleteEndTime - deleteStartTime));
		
		double endTime = System.currentTimeMillis();
		System.out.println("Total Time For AVL All Operation: " + (endTime - startTime));
		avl=null;
		inorder = new ArrayList<>();
	}

}