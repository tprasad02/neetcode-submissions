class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // O(n log n + m log m) time, O(n + m) space

        // Sort intervals by start point
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Min-heap: [interval length, end point]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Store answer for each query
        Map<Integer, Integer> res = new HashMap<>();
        int i = 0;

        // Process queries in sorted order
        for (int q : Arrays.stream(queries).sorted().toArray()) {

            // Add intervals that could contain q
            while (i < intervals.length && intervals[i][0] <= q) {
                int l = intervals[i][0];
                int r = intervals[i][1];
                minHeap.offer(new int[]{r - l + 1, r});
                i++;
            }

            // Remove intervals that end before q
            while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                minHeap.poll();
            }

            // Get smallest valid interval, or -1 if none
            if (minHeap.isEmpty()) {
                res.put(q, -1);
            } else {
                res.put(q, minHeap.peek()[0]);
            }
        }

        int[] result = new int[queries.length];

        // Restore original query order
        for (int j = 0; j < queries.length; j++) {
            result[j] = res.get(queries[j]);
        }

        return result;
    }
}