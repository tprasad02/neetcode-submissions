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

    public int goodNodes(TreeNode root) {
        // DFS while tracking the maximum value seen on the path
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxVal) {
        if (node == null) {
            return 0;
        }
        // Count this node if it is at least as large as every node
        // seen previously on the current root-to-node path
        int res = 0;
        if (node.val >= maxVal) {
            res = 1;
        }
        // Update the maximum value for the remainder of this path
        maxVal = Math.max(maxVal, node.val);
        // Count good nodes in the left and right subtrees
        res += dfs(node.left, maxVal);
        res += dfs(node.right, maxVal);
        return res;
    }
}
