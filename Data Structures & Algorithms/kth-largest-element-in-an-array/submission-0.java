class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-heap to keep track of the k largest elements seen so far
        // The smallest element in the heap is at the root
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            // If the heap grows larger than k,
            // remove the smallest element
            // This ensures we only keep the k largest numbers
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}