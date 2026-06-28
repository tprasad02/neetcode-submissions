public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // Stores all complete permutations
        List<List<Integer>> res = new ArrayList<>();
        // Tracks the current permutation being built
        List<Integer> perm = new ArrayList<>();
        // Keeps track of which numbers have already been used
        boolean[] used = new boolean[nums.length];
        dfs(nums, perm, used, res);
        return res;
    }

    private void dfs(int[] nums, List<Integer> perm,
                     boolean[] used, List<List<Integer>> res) {
        // Base case: every number has been used
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        // Try placing every unused number in the next position
        for (int i = 0; i < nums.length; i++) {
            // Skip numbers that are already in the current permutation
            if (used[i]) {
                continue;
            }
            // Choose nums[i]
            perm.add(nums[i]);
            used[i] = true;
            // Build the rest of the permutation
            dfs(nums, perm, used, res);
            // Backtrack: remove the number so another choice can be tried
            perm.remove(perm.size() - 1);
            used[i] = false;
        }
    }
}