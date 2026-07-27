/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {

        // Sort the intervals by start time
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        
        // Go through the intervals
        for (int i = 0; i < intervals.size() - 1; i++) {
            
            // If the current interval ends after the next one starts,
            // the meetings overlap
            if (intervals.get(i).end > intervals.get(i + 1).start) {
                return false;
            }
        }
        
        return true;
    }
}