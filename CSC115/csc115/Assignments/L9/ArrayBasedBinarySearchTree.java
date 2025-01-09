public class ArrayBasedBinarySearchTree extends ArrayBasedBinaryTree{

    public void insert(Integer value){
       insert(root, value);
    }

    private void insert(Integer index, Integer val){
        if (data[index] == null){
            data[index] = val;
        } else {
            if (data[index] > val){
                insert(getLeft(index), val);
            } else {
                insert(getRight(index), val);
            }
        }
    }
    
    public static void main(String[] args) {
        ArrayBasedBinarySearchTree emptyTree = new ArrayBasedBinarySearchTree();
        
        ArrayBasedBinarySearchTree myTree = new ArrayBasedBinarySearchTree();
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
    }

}
