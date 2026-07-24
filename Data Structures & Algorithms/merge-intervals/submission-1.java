class Solution {
    public int[][] merge(int[][] intervals) {
        // Time: O(nlogn)
        // Space: O(1)

        // Sort intervals by start point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Track current interval's start and end
        int start = intervals[0][0];
        int end = intervals[0][1];
        List<int[]> result = new ArrayList<>();
        
        for (int i = 1; i < intervals.length; i++) {
            // Merge if current start is inside the previous interval
            if (intervals[i][0] >= start && intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            }
            // Merge if current end is inside the previous interval
            else if (intervals[i][1] >= start && intervals[i][1] <= end) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
            }
            // No overlap, so save the current interval
            else {
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        result.add(new int[]{start, end});
        return result.toArray(new int[result.size()][]);
    }
}