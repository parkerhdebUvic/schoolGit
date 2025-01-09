interface Node<T> {
    /*
     * Purpose: Gets the generic data
     * Parameters: None
     * Returns: T, generic data
     */
    public T getData();

    /*
     * Purpose: sets the generic data to new generic data
     * Paramenters: T, generic data
     * Returns: none
     */
    public void setData(T data);

    /*
     * Purpose: returns the left node
     * Parameters: none
     * Returns: Generic left node
     */
    public TreeNode<T> getLeft();

    /*
     * Purpose: sets the left generic tree node
     * Parameters: Left generic tree node
     * Returns: none
     */
    public void setLeft(TreeNode<T> left);

    /*
     * Purpose: returns the right tree node
     * Parameters: none
     * Returns: Right generic tree node
     */
    public TreeNode<T> getRight();

    /*
     * Purpose: sets the right generic tree node
     * Parameters: right generic tree node
     * Returns: none
     */
    public void setRight(TreeNode<T> right);

    /*
     * Purpose: returns the string version of the generic data
     * Parameters: none
     * Returns: string
     */
    public String toString();

}
