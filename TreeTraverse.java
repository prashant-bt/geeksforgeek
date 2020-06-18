package test;

class TreeNode  
{  
	int value;  
    TreeNode left; 
    TreeNode right;  
    
    TreeNode(int data) 
    { 
        this.value = data;  
        this.left = this.right = null;  
        return;  
    }
    
    TreeNode(int data, TreeNode left, TreeNode right) 
    { 
    this.value = data; 
    this.left = left; 
    this.right = right; 
    }
} 
	 
	 
class TreeTraverse 
{ 	 
	   
	void traverseLeaves(TreeNode root)  
	{  
		if (root == null)  
			return;  
 
		if (root.left == null && root.right == null)  
		{  
			System.out.print( root.value +" ");  
			return;  
		}  
   
    	traverseLeaves(root.right);   
    	traverseLeaves(root.left);  
	}  
  
public static void main(String args[]) 
{  
	TreeTraverse tree = new TreeTraverse(); 
	TreeNode n4 = new TreeNode(4);
	TreeNode n7 = new TreeNode(7); 
	TreeNode n13 = new TreeNode(13);
	TreeNode n1 = new TreeNode(1);
	TreeNode n6 = new TreeNode(6, n4, n7);
	TreeNode n14 = new TreeNode(14, n13, null);
	TreeNode n3 = new TreeNode(3, n1, n6); 
    TreeNode n10 = new TreeNode(10, null, n14);
	TreeNode root = new TreeNode(8, n3, n10);
        
    tree.traverseLeaves(root);  
} 
}
 