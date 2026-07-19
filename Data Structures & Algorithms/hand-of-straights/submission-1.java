class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // Time: O(nlogn)
        // Space: O(n)
        
        // The cards must be able to split evenly into groups
        if (hand.length % groupSize != 0) {
            return false;
        }
        // Sort so we always start groups from the smallest remaining card
        Arrays.sort(hand);

        // Store the frequency of each card
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int card : hand) {
            freq.put(card, freq.getOrDefault(card, 0) + 1);
        }

        // Iterate through the sorted hand
        for (int card : hand) {
            // If this card has already been used, skip it
            if (freq.get(card) == 0) {
                continue;
            }
            // Try to build a group starting at this card
            for (int i = 0; i < groupSize; i++) {
                int current = card + i;
                // If the next required card doesn't exist,
                // we cannot form a valid group
                if (!freq.containsKey(current) || freq.get(current) == 0) {
                    return false;
                }
                // Use one copy of this card
                freq.put(current, freq.get(current) - 1);
            }
        }
        // Every group was formed successfully
        return true;
    }
}