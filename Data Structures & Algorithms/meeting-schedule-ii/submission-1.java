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
    public int minMeetingRooms(List<Interval> intervals) {
        
        // Return 0 if there are no meetings
        if (intervals.size() == 0) {
            return 0;
        }
        
        // Store all start and end times
        int[] starts = new int[intervals.size()];
        int[] ends = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        
        // Sort the start and end times
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        // Start with one room
        int count = 1;
        
        // Two pointers for starts and ends
        int startPointer = 1;
        int endPointer = 0;
        
        // Compare the next meeting start with the earliest meeting end
        while (startPointer < intervals.size()) {

            // If meetings overlap, we need another room
            if (starts[startPointer] < ends[endPointer]) {
                count++;
                startPointer++;
            }
            
            // If a meeting has ended, its room can be reused
            else {
                startPointer++;
                endPointer++;
            }
        }
        
        return count;
    }
}