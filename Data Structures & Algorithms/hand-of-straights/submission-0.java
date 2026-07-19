class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // If the cards cannot be evenly divided into groups,
        // forming valid groups is impossible
        if (hand.length % groupSize != 0) {
            return false;
        }
        // Sort the cards
        Arrays.sort(hand);
        boolean[] used = new boolean[hand.length];

        // Try to build groups until every card has been used
        for (int i = 0; i < hand.length; i++) {
            // Skip cards that have already been placed in a group
            if (used[i]) {
                continue;
            }
            int prev = hand[i];
            used[i] = true;
            int size = 1;
            // Keep looking for the next consecutive card
            for (int j = i + 1; j < hand.length && size < groupSize; j++) {
                if (!used[j] && hand[j] == prev + 1) {
                    used[j] = true;
                    prev = hand[j];
                    size++;
                }
            }
            // Could not build a full group
            if (size != groupSize) {
                return false;
            }
        }
        return true;
    }
}