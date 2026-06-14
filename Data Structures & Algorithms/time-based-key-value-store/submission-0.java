public class TimeMap {
    // O(1) time for set() and O(log n) for get()
    // O(m*n) space
    
    // Map each key to its list of timestamps and corresponding values
    private Map<String, List<Integer>> timestamps;
    private Map<String, List<String>> values;

    public TimeMap() {
        timestamps = new HashMap<>();
        values = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        // Create lists for this key if they don't already exist
        timestamps.computeIfAbsent(key, t -> new ArrayList<>());
        values.computeIfAbsent(key, v -> new ArrayList<>());
        // Append the new timestamp and value
        timestamps.get(key).add(timestamp);
        values.get(key).add(value);
    }

    public String get(String key, int timestamp) {
        if (!timestamps.containsKey(key)) {
            return "";
        }

        List<Integer> timeList = timestamps.get(key);
        List<String> valueList = values.get(key);

        int left = 0;
        int right = timeList.size() - 1;
        String result = "";

        // Binary search for the largest timestamp <= target timestamp
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (timeList.get(mid) <= timestamp) {
                result = valueList.get(mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}