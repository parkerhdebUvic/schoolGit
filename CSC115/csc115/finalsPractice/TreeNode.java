public class TreeNode<T> implements Node<T>{
    protected T data;
    protected TreeNode<T> left;
    protected TreeNode<T> right;

    public TreeNode(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public T getData(){
        return this.data;
    }
    public void setData(T data){
        this.data = data;
    }

    public TreeNode<T> getLeft(){
        return this.left;
    }

    public void setLeft(TreeNode<T> left){
        this.left = left;
    }

    public TreeNode<T> getRight(){
        return this.right;
    }

    public void setRight(TreeNode<T> right){
        this.right = right;
    }

    public String toString(){
        return data.toString();
    }
}

