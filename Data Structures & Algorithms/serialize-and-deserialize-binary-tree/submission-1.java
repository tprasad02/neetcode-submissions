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

public class Codec {
    
    // Encodes a tree into a preorder traversal
    // Null nodes are represented by "N"
    public String serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfsSerialize(root, res);
        // Example:
        //      1
        //     / \
        //    2   3
        //
        // becomes:
        // "1,2,N,N,3,N,N"
        return String.join(",", res);
    }

    private void dfsSerialize(TreeNode node, List<String> res) {
        // Mark null children so the tree can be reconstructed exactly
        if (node == null) {
            res.add("N");
            return;
        }
        // Preorder traversal: Root -> Left -> Right
        res.add(String.valueOf(node.val));
        dfsSerialize(node.left, res);
        dfsSerialize(node.right, res);
    }

    // Current position in the serialized preorder traversal.
    private int index = 0;

    public TreeNode deserialize(String data) {
        // Start reading from the beginning of the preorder traversal
        index = 0;
        return buildTree(data.split(","));
    }

    private TreeNode buildTree(String[] preorder) {
        // Read the next value in the preorder traversal and advance
        String value = preorder[index++];
        if (value.equals("N")) {
            return null;
        }
        // Create the root of the current subtree
        TreeNode root = new TreeNode(Integer.parseInt(value));

        // Reconstruct the left subtree first, then the right subtree
        root.left = buildTree(preorder);
        root.right = buildTree(preorder);
        return root;
    }
}