class Solution {
    public int[][] kClosest(int[][] points, int k) {

        // Min-heap ordered by distance from the origin
        // The point with the smallest distance will always be at the top
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (point1, point2) -> {
                int x1 = point1[0];
                int y1 = point1[1];
                int distance1 = x1 * x1 + y1 * y1;

                int x2 = point2[0];
                int y2 = point2[1];
                int distance2 = x2 * x2 + y2 * y2;

                return distance1 - distance2;
            }
        );

        // Add every point to the heap
        for (int[] point : points) {
            minHeap.offer(point);
        }

        // Store the k closest points
        int[][] result = new int[k][2];

        // Remove the k points with the smallest distances
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}