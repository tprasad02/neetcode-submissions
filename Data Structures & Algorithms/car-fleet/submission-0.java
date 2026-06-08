public class Solution {
    public int carFleet(int target, int[] position, int[] speed) {

        // Pair each car's position with its speed so we can sort
        // while keeping the corresponding speed
        int[][] pair = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        // Process cars from closest to the target to farthest away
        // A car can only ever catch the car directly in front of it,
        // never one behind it
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));

        // Stack stores the time each fleet takes to reach the target
        // Each element represents one fleet
        Stack<Double> stack = new Stack<>();

        for (int[] car : pair) {
            double time = (double) (target - car[0]) / car[1];
            stack.push(time);
            // If this car reaches the target sooner than (or at the same
            // time as) the fleet directly ahead, it catches that fleet
            // before reaching the target
            // Therefore, it does NOT form a new fleet, so remove its time
            if (stack.size() >= 2 &&
                stack.peek() <= stack.get(stack.size() - 2)) {
                stack.pop();
            }
        }
        // Each remaining time on the stack represents one fleet
        return stack.size();
    }
}