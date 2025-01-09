import java.lang.Math;
/*
 * RefBasedBinarySearchTree.java
 * 
 * A ref-based BinaryTree meant to store values of type Integer
 */
public class RefBasedBinarySearchTree extends RefBasedBinaryTree {


    public void insert(Integer val){
        if (root == null) {
            root = new TreeNode(val);
        } else {
            insert(null, root, val);
        }
        
    }

    private void insert(TreeNode parent, TreeNode t, Integer value){
        if (t==null) {
            if(parent.getValue() > value) {
                parent.setLeft(new TreeNode(value));
			} else {
                parent.setRight(new TreeNode(value));
			}            
        }  else {
            // int valLeft = parent.getLeft().getValue();
            // int valRight = parent.getRight().getValue();
            // if (valLeft > value) {
            //     insert(t, t.getRight(), value);
			// } else {
            //     insert(t, t.getLeft(), value);
			// }

            if (t.getValue() > value){
                insert(t, t.getLeft(), value);
            } else {
                insert(t, t.getRight(), value);
            }
        }
    }

    /* 
     * Method name: find 
     * Purpose: determines whether val is in this BinaryTree 
     * Parameters: int val 
     * Returns: boolean – true if val is found, false otherwise 
     */
    
    public boolean find(int val){
        return find(root, val);
    }

    public boolean find(TreeNode t, int n){
        if (t==null){
            return false;
        } else {
            if (t.getValue()==n){
                return true;
            } else{
                if (t.getValue() > n){
                    return find(t.getLeft(), n);
                } else{
                    return find(t.getRight(), n);
                }
            }
        }
    }


     /* 
     * Method name: getMax 
     * Purpose: gets and returns the largest value in this BinaryTree 
     * Parameters: none 
     * Throws: TreeEmptyException if called on an empty tree 
     * Returns: int – the largest value 
     */
    public int getMax(){
        return getMax(root, 0);
    }

    public int getMax(TreeNode t, int max){
        if (t==null){
            return max;
        } else {
            if (t.getValue() > max){
                max = t.getValue();
            } 
            int rMax = getMax(t.getRight(), max);
            if(rMax > max){
                max = rMax;
            }
            return max;
        }
    }
 
  


    public static void main(String[] args) {
        // use these trees to test the methods you will implement
        RefBasedBinarySearchTree emptyTree = new RefBasedBinarySearchTree();
        RefBasedBinarySearchTree myTree = new RefBasedBinarySearchTree();
        myTree.insert(2);
        myTree.insert(1);
        myTree.insert(5);
        myTree.insert(7);
        myTree.insert(0);
        myTree.insert(4);
        myTree.insert(6);
        
        System.out.println("in");
        myTree.inOrder();
        System.out.println("pre");
        myTree.preOrder();
        System.out.println("post");
        myTree.postOrder();
        
        System.out.println("toString\n" + myTree);

        System.out.println(myTree.getMax());

        myTree.insert(60);
        System.out.println(myTree.getMax());

        myTree.insert(61);
        System.out.println(myTree.getMax());

        myTree.insert(600);
        System.out.println(myTree.getMax());

        System.out.println(emptyTree.getMax());
        
    }
}
