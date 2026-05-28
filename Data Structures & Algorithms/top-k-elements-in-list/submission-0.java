class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Create a frequency list to track how often each element occurs
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums){
            counts.put(num, (counts.getOrDefault(num, 0)) + 1);
            //not putIfAbsent because we want to update the count each time
        }            
        // Sorting would take O(nlog(n)) time; we can find a faster O(n) solution
        // Bucket sort: put each element at the index that corresponds to its frequency
        // Index = frequency, value = list of numbers with that frequency
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int num : counts.keySet()) {
            int freq = counts.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        // Note that copying the last k elements won't work
        // The largest frequencies are not guaranteed to occupy the last indices contiguously
        // Loop to obtain top K
        int[] ans = new int[k];
        int index = 0;
        for (int j = buckets.length - 1; j >= 0 && index < k; j--) {
            if (buckets[j] == null) continue;
            for (int n : buckets[j]) { // within list of numbers with same frequency
                ans[index] = n;
                index++;
                if (index == k) break;
            }
        }
        return ans;
    }
}
