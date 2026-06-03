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
    public boolean isBalanced(TreeNode root) {
        int[] result = dfs(root);
        // result[0] = balanced flag
        return result[0] == 1;
    }

    private int[] dfs(TreeNode root) {
        // Return:
        // [balancedFlag, height]

        if (root == null) {
            // Empty tree is balanced
            // Height of empty tree = 0
            return new int[]{1, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        // Both subtrees must already be balanced
        boolean subtreesBalanced =
                left[0] == 1 &&
                right[0] == 1;
        // Current node is balanced only if heights differ by at most 1
        boolean heightsBalanced =
                Math.abs(left[1] - right[1]) <= 1;
        boolean balanced =
                subtreesBalanced &&
                heightsBalanced;
        // Height that parent needs from us
        int height =
                1 + Math.max(left[1], right[1]);

        int balancedFlag;
        if (balanced) {
            balancedFlag = 1;
        } else {
            balancedFlag = 0;
        }
        return new int[]{balancedFlag, height};
    }
}
