class Solution {
    public int lastStoneWeight(int[] stones) {
        // O(n log n) time, O(n) space for heap

        // We always need to remove the two heaviest stones,
        // so we use a max-heap
        // Java's PriorityQueue is a min-heap by default
        // so we store negative values to simulate a max-heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Convert all stone weights to negative values
        // and add them to the heap
        for (int stone : stones) {
            heap.offer(-stone);
        }

        // Continue while at least two stones remain
        while (heap.size() > 1) {
            // Remove the two heaviest stones
            int stone1 = -heap.poll();
            int stone2 = -heap.poll();
            // If their weights are different,
            // the remaining weight goes back into the heap
            if (stone1 != stone2) {
                heap.offer(-(stone1 - stone2));
            }
            // If equal, both stones are destroyed
        }
        if (heap.isEmpty()) {
            return 0;
        }
        // Otherwise return the weight of the last stone
        return -heap.peek();
    }
}