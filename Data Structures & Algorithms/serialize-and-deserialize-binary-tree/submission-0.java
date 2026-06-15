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

    // Decodes the serialized preorder string back into a tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        int[] i = {0};
        return dfsDeserialize(vals, i);
    }

    private TreeNode dfsDeserialize(String[] vals, int[] i) {
        if (vals[i[0]].equals("N")) {
            i[0]++;
            return null;
        }
        // Create the current node
        TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]]));
        // Move to the next value in the preorder traversal
        i[0]++;
        // Reconstruct the left and right subtrees.
        node.left = dfsDeserialize(vals, i);
        node.right = dfsDeserialize(vals, i);
        return node;
    }
}