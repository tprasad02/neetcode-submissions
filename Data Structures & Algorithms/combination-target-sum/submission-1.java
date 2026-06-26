// // Solution 1 Backtracking: O(2^(t/m)) time, O(t/m) space
// public class Solution {
//     public List<List<Integer>> combinationSum(int[] nums, int target) {
//         List<List<Integer>> res = new ArrayList<>();
//         List<Integer> subset = new ArrayList<>();
//         dfs(nums, target, 0, subset, res);
//         return res;
//     }

//     private void dfs(int[] nums, int target, int i,
//                      List<Integer> subset, List<List<Integer>> res) {
//         // Found a valid combination
//         if (target == 0) {
//             res.add(new ArrayList<>(subset));
//             return;
//         }
//         // Ran out of numbers or exceeded the target
//         if (i == nums.length || target < 0) {
//             return;
//         }
//         // Choice 1: Include nums[i]
//         subset.add(nums[i]);
//         // Stay at the same index so nums[i] can be used again
//         dfs(nums, target - nums[i], i, subset, res);
//         // Backtrack
//         subset.remove(subset.size() - 1);
//         // Choice 2: Skip nums[i] and move to the next number
//         dfs(nums, target, i + 1, subset, res);
//     }
// }

// Solution 2 Backtracking (optimal): same time & space complexity
public class Solution {
    // Stores all valid combinations
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        res = new ArrayList<>();
        // Sort so we can stop exploring once a number exceeds the target
        Arrays.sort(nums);
        // Start DFS from index 0 with an empty combination and sum of 0
        dfs(0, new ArrayList<>(), 0, nums, target);
        return res;
    }

    private void dfs(int i, List<Integer> cur, int total, int[] nums, int target) {
        // Found a valid combination
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // Try every candidate starting from index i
        for (int j = i; j < nums.length; j++) {
            // Since nums is sorted, all later numbers will also be too large
            // so we can prune the remaining search
            if (total + nums[j] > target) {
                return;
            }
            // Choose the current number
            cur.add(nums[j]);
            // Stay at j so the same number can be chosen again
            dfs(j, cur, total + nums[j], nums, target);
            // Backtrack and try the next candidate
            cur.remove(cur.size() - 1);
            
            // No second DFS call is needed; the for-loop automatically
            // advances to the next index after backtracking
        }
    }
}
