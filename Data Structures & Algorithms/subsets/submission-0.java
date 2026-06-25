public class Solution {
    // O(n * 2^n) time, O(n) extra space + O(2*n) space for output list
    public List<List<Integer>> subsets(int[] nums) {
        // Stores all subsets that we generate
        List<List<Integer>> res = new ArrayList<>();
        // Tracks the current subset we are building
        List<Integer> subset = new ArrayList<>();
        // Start DFS from index 0
        dfs(nums, 0, subset, res);
        return res;
    }

    private void dfs(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        // Base case: we have considered every element
        if (i >= nums.length) {
            // Add a copy of the current subset to the results
            res.add(new ArrayList<>(subset));
            return;
        }
        // Choice 1: Include nums[i] in the subset
        subset.add(nums[i]);
        // Recurse to decide what to do with the next element
        dfs(nums, i + 1, subset, res);
        // Backtrack: remove the element we just added
        subset.remove(subset.size() - 1);
        // Choice 2: Exclude nums[i] from the subset
        dfs(nums, i + 1, subset, res);
    }
}