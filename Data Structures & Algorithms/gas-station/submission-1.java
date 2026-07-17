public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // If the total gas is less than the total cost,
        // completing the circuit is impossible
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1;
        }
        // total = current gas in tank while simulating the trip
        // res = current candidate starting station
        int total = 0;
        int res = 0;
        for (int i = 0; i < gas.length; i++) {
            // Gain gas at station i and spend gas to reach the next station
            total += (gas[i] - cost[i]);
            // If we run out of gas, then none of the stations
            // from res to i can be valid starting points
            // Start over from the next station
            if (total < 0) {
                total = 0;
                res = i + 1;
            }
        }
        return res;
    }
}