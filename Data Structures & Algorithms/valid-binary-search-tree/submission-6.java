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

class Solution {
    public boolean isValidBST(TreeNode root) {
        // Recursively verify that every node's value falls within
        // the valid range determined by its ancestors
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValid(TreeNode node, long min, long max) {
        // An empty subtree is a valid BST
        if (node == null) {
            return true;
        }
        // Node must be strictly within its allowed range
        if (node.val <= min || node.val >= max) {
            return false;
        }
        // Left subtree: values < node.val
        // Right subtree: values > node.val
        return isValid(node.left, min, node.val)
            && isValid(node.right, node.val, max);
    }
}