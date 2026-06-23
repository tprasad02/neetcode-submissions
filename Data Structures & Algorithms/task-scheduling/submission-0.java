public class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Count frequency of each task (A-Z)
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        // Max-heap storing remaining counts
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int cnt : count) {
            if (cnt > 0) {
                maxHeap.add(cnt);
            }
        }

        // Current time unit
        int time = 0;
        // Queue: {remainingCount, availableTime}
        Queue<int[]> q = new LinkedList<>();

        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;
            // If no available tasks, jump to next available time
            if (maxHeap.isEmpty()) {
                time = q.peek()[1];
            }
            // If we have a task available, execute one instance
            if (!maxHeap.isEmpty()) {
                int cnt = maxHeap.poll() - 1;
                // If still remaining, push into cooldown
                if (cnt > 0) {
                    q.add(new int[]{cnt, time + n});
                }
            }
            // Move tasks back from cooldown when ready
            if (!q.isEmpty() && q.peek()[1] == time) {
                maxHeap.add(q.poll()[0]);
            }
        }
        return time;
    }
}