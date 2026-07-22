class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // O(n) time, O(1) space
        
        List<int[]> result = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        for (int i = 0; i < intervals.length; i++) {
            // Case 1: Current interval is completely before newInterval
            if (intervals[i][1] < start) {
                result.add(intervals[i]);
            }
            // Case 2: Current interval is completely after newInterval
            else if (intervals[i][0] > end) {
                // Add the merged newInterval first
                result.add(new int[]{start, end});
                // Add all remaining intervals
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]);
                }
                return result.toArray(new int[result.size()][]);
            }
            // Case 3: Current interval overlaps with newInterval
            else {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
            }
        }
        // If we reach the end, add the merged interval
        result.add(new int[]{start, end});
        return result.toArray(new int[result.size()][]);
    }
}