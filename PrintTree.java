package test;

class Node  
{  
	int value;  
	Node left; 
	Node right;  
    
	Node(int data) 
    { 
        this.value = data;  
        this.left = this.right = null;  
        return;  
    }
    
	Node(int data, Node left, Node right) 
    { 
    this.value = data; 
    this.left = left; 
    this.right = right; 
    }
} 
  
class PrintTree 
{ 
    Node root; 
  
    public PrintTree() 
    { 
        root = null; 
    } 
  
    void orderTraversal() 
    { 
        int h = height(root); 
        int i; 
        for (i=1; i<=h; i++) 
        orderLevel(root, i); 
    } 
  
    int height(Node root) 
    { 
        if (root == null) 
           return 0; 
        else
        { 
            
            int leftHeight = height(root.left); 
            int rightHeight = height(root.right); 
              
            if (leftHeight > rightHeight) 
                return(leftHeight+1); 
            else return(rightHeight+1);  
        } 
    } 
  
    void orderLevel (Node root ,int level) 
    { 
        if (root == null) 
            return; 
        if (level == 1) 
            System.out.print(root.value + " "); 
        else if (level > 1) 
        { 
        	orderLevel(root.left, level-1); 
        	orderLevel(root.right, level-1); 
        } 
    } 
      
   
    public static void main(String args[]) 
    { 
       PrintTree tree = new PrintTree(); 
       tree.root= new Node(1); 
       tree.root.right= new Node(2); 
       tree.root.right.right= new Node(5);
       tree.root.right.right.left= new Node(3);
       tree.root.right.right.right= new Node(6);
       tree.root.right.right.left.right= new Node(4);
         
       tree.orderTraversal(); 
    } 
} 
