/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution {
    int preIdx = 0;
    int inIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Start building the tree with no upper bound
        return dfs(preorder, inorder, Integer.MAX_VALUE);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int limit) {
        // All preorder nodes have been used
        if (preIdx >= preorder.length) {
            return null;
        }
        // We've reached the boundary of the current subtree in the inorder array
        if (inorder[inIdx] == limit) {
            inIdx++;
            return null;
        }
        // The next preorder value is always the root of the current subtree
        TreeNode root = new TreeNode(preorder[preIdx++]);

        // Build the left subtree until we encounter the root value in inorder
        root.left = dfs(preorder, inorder, root.val);

        // Build the right subtree until we reach this subtree's boundary
        root.right = dfs(preorder, inorder, limit);

        return root;
    }
}