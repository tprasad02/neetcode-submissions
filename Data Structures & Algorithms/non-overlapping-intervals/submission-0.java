class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // Time: O(nlogn)
        // Space: O(1)

        // Sort intervals by start point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Track current interval's start and end
        int start = intervals[0][0];
        int end = intervals[0][1];
        int count = 0;
        
        for (int i = 1; i < intervals.length; i++) {
            // Intervals overlap
            if (intervals[i][0] < end) {
                // Remove the interval with the larger endpoint
                if (intervals[i][1] < end) {
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
                count++;
            }
            
            // No overlap, so move to the next interval
            else {
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        
        return count;
    }
}