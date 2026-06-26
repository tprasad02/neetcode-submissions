public class Solution {
    // O(2^(t/m)) time, O(t/m) space
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(nums, target, 0, subset, res);
        return res;
    }

    private void dfs(int[] nums, int target, int i,
                     List<Integer> subset, List<List<Integer>> res) {
        // Found a valid combination
        if (target == 0) {
            res.add(new ArrayList<>(subset));
            return;
        }
        // Ran out of numbers or exceeded the target
        if (i == nums.length || target < 0) {
            return;
        }
        // Choice 1: Include nums[i]
        subset.add(nums[i]);
        // Stay at the same index so nums[i] can be used again
        dfs(nums, target - nums[i], i, subset, res);
        // Backtrack
        subset.remove(subset.size() - 1);
        // Choice 2: Skip nums[i] and move to the next number
        dfs(nums, target, i + 1, subset, res);
    }
}