class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        // Try every index as a potential starting point
        for (int start = 0; start < gas.length; start++) {
            int tank = 0;
            int idx = start;
            int stationsVisited = 0;
            // Simulate driving around the circuit
            while (stationsVisited < gas.length) {
                // Fill up at the current station
                tank += gas[idx];
                // Spend gas to travel to the next station
                tank -= cost[idx];
                // If we ever go negative, this start doesn't work
                if (tank < 0) {
                    break;
                }
                // Move to the next station (wrap around)
                idx = (idx + 1) % gas.length;
                stationsVisited++;
            }
            // If we visited every station, we completed the circuit
            if (stationsVisited == gas.length) {
                return start;
            }
        }
        return -1;
    }
}