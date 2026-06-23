class Solution {

    // Returns the distance from the origin (0, 0)
    private int distance(int[] point) {
        int x = point[0];
        int y = point[1];
        return (x * x + y * y);
    }

    public int[][] kClosest(int[][] points, int k) {

        // Min-heap that stores points as int[] = [x, y]
        // Points are ordered by their distance from the origin,
        // so the closest point is always at the top.
        PriorityQueue<int[]> minHeap =
            new PriorityQueue<>((p1, p2) -> distance(p1) - distance(p2));

        // Add every point to the heap.
        for (int[] point : points) {
            minHeap.offer(point);
        }

        // Store the k closest points
        int[][] result = new int[k][2];

        // Remove the closest point k times
        // Since this is a min-heap, poll() always returns
        // the point with the smallest distance
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}