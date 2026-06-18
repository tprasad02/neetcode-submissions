class KthLargest {

    // O(m log k) time and O(k) space
    // m is number of adds and k is num elements in the heap

    // Min-heap that stores the k largest elements seen so far
    // The root is always the kth largest element
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        // Add every number to the heap
        for (int num : nums) {
            minHeap.offer(num);
            // If we have more than k elements,
            // remove the smallest one
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }

    public int add(int val) {
        // Add the new value to the heap
        minHeap.offer(val);
        // Keep only the k largest elements
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        // The smallest element in the heap
        // is the kth largest overall
        return minHeap.peek();
    }
}