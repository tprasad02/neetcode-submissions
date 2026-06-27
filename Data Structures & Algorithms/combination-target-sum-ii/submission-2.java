public class Solution {
    private static List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res.clear();
        // Sort so duplicates are adjacent and so we can stop early when sum exceeds target
        Arrays.sort(candidates);
        // Start DFS at index 0 with an empty path and current sum of 0
        dfs(0, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    private static void dfs(int idx, List<Integer> path, int cur, 
        int[] candidates, int target) {
        // If current sum equals target, we found a valid combination
        if (cur == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // Try each candidate starting from idx
        for (int i = idx; i < candidates.length; i++) {
            // Skip duplicates at the same recursion depth
            // Example: if candidates[i] == candidates[i - 1],
            // choosing candidates[i] here would create the same combination
            // that was already created by choosing candidates[i - 1]
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // Since the array is sorted, if this candidate makes the sum too large,
            // every candidate after it will also be too large
            if (cur + candidates[i] > target) {
                break;
            }
            // Choose candidates[i]
            path.add(candidates[i]);
            // Move to i + 1 because each number can only be used once
            dfs(i + 1, path, cur + candidates[i], candidates, target);
            // Backtrack: remove the last chosen number before trying the next option
            path.remove(path.size() - 1);
        }
    }
}