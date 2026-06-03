public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];

        Deque<Integer> q = new LinkedList<>();
        int l = 0, r = 0;

        while (r < n) {
            // Remove all smaller values from the back
            // They can never become the max while nums[r] exists
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            // Add current index
            q.addLast(r);
            // Remove max candidate if it fell out of the window
            if (l > q.getFirst()) {
                q.removeFirst();
            }
            // Window has reached size k
            if ((r + 1) >= k) {
                // Front of deque is always the maximum
                output[l] = nums[q.getFirst()];
                // Slide window
                l++;
            }
            r++;
        }
        return output;
    }
}