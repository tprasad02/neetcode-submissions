public class Solution {
    // O(n * 2^n) time, O(n) recursion stack
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Stores all subsets that we generate
        List<List<Integer>> res = new ArrayList<>();
        // Tracks the current subset we are building
        List<Integer> subset = new ArrayList<>();
        // Sort so duplicate values are adjacent
        Arrays.sort(nums);
        // Start DFS from index 0
        dfs(nums, 0, subset, res);
        return res;
    }

    private void dfs(int[] nums, int i, List<Integer> subset,
                     List<List<Integer>> res) {
        // Base case: we have considered every element
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        // Choice 1: Include nums[i]
        subset.add(nums[i]);
        dfs(nums, i + 1, subset, res);
        
        // Backtrack
        subset.remove(subset.size() - 1);
        // We only skip duplicates when exploring the "exclude" branch.
        // If we included nums[i], we still want the option to include its duplicates
        // (e.g. [2,2]). Skipping here avoids generating the same subset twice.
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }

        // Choice 2: Exclude nums[i] (and all of its duplicates)
        dfs(nums, i + 1, subset, res);
    }
}