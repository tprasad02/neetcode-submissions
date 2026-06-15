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

    public int maxPathSum(TreeNode root) {
        // Stores the maximum path sum found anywhere in the tree
        int[] res = new int[]{root.val};
        dfs(root, res);
        return res[0];
    }

    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }

        // Compute the maximum contribution from the left and right subtrees
        // Ignore negative paths since they would decrease the total path sum
        int leftMax = Math.max(dfs(root.left, res), 0);
        int rightMax = Math.max(dfs(root.right, res), 0);

        // Update the global maximum using a path that passes through
        // the current node and includes both left and right children
        res[0] = Math.max(res[0], root.val + leftMax + rightMax);

        // Return the maximum contribution this node can make to its parent
        // A parent can only continue one branch of the path
        return root.val + Math.max(leftMax, rightMax);
    }
}