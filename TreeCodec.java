public class TreeCodec {

   /**
     *  Tree is one of essential data structures, and it is a special case of Graph
     *  BinaryTree, BinarySeachTree are used mostly often in Tree structure related algorithm.
     *
     *  Here, some interesting algorithm on BT or BST are collected, practice and practice!
     * 
    **/ 

  class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) {val = x; }
       }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        StringBuffer bf = new StringBuffer();
        
        helper1(root, bf);
        
        return bf.toString();
    }
    
    
    
    void helper1(TreeNode root, StringBuffer bf){
        
        if(root == null){
            bf.append("null,");
        }else{
            bf.append(""+root.val+",");
            helper1(root.left, bf);
            helper1(root.right, bf);
        }
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        
        Deque<String> list= new LinkedList<String>();
          
        list.addAll(Arrays.asList(data.split(",")));
        
        return helper2(list);
    }
    
    TreeNode helper2(Deque<String> list){
        
       // if(list.isEmpty()) return null;
        
        String str = list.remove();
        if ( str.equals("null")) 
            return null;
        else{
            TreeNode root = new TreeNode(Integer.valueOf(str));
            root.left= helper2(list);
            root.right= helper2(list);
            return root;
        }
        
    }

    // return the first or smallest node in BST
    public TreeNode getFirstNode(TreeNode root){

                if (root.left != null )
                        return getFirstNode(root.left);
                else
                        return root;

     }
    /**
      * return kth smallest in a tree if there is 
      *       or null
      *//
    int cntr=0;
    public int kthSmallest(TreeNode root, int k) {
        cntr=k;
        TreeNode node = helper(root);
        return node.val;
    }
    
    TreeNode helper(TreeNode root){
        TreeNode node=null;
        //first, get to smallest node in the tree
        if(root.left != null){
            node = helper(root.left);
        }
        // NonNull node is the kth smallest
        if(node != null)
            return node;
        if(--cntr == 0){
            return root;
        }
        if(root.right !=null){
            return helper(root.right);
        }else
            return null; // not the kth, return to the parent
    }
    
    // Invert a tree
    public TreeNode invertTree(TreeNode root){

                if(root == null ) return null;

                TreeNode node = root.left;
                root.left = root.right;
                root.right = node;

                if (root.left != null)
                        invertTree(root.left);

                if (root.right != null)
                        invertTree(root.right);

                return root;
     }
     
     /**
      * see if the tree's left and right are symtric 
      *     not change the tree
      **/
     public boolean isMirror(TreeNode root){

        if(root==null) return true;

        return compare(root.left, root.right);
     }

     public boolean compare(TreeNode left, TreeNode right){

          if(left == null && right==null) return true;

          if(left == null || right == null) return false;

          if(left.val != right.val) return false;

          boolean res=compare(left.left, right.right);

          if(!res) return false;

          return compare(left.right, right.left);

     }
}
