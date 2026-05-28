// Solution 1: two pointers moving inwards, stop when they cross
// O(n) time, O(n) space
// class Solution {
//     public boolean isPalindrome(String s) {
//         // [^a-zA-Z0-9] matches everything that is NOT a letter or number
//         String cleaned = s.replaceAll("[^a-zA-Z0-9]", "");
//         
//         int j = cleaned.length()-1;
//         for (int i = 0; i < j; i++){
//             String start = cleaned.substring(i, i+1);
//             String end = cleaned.substring(j, j+1);
//             if (start.equalsIgnoreCase(end)) j--;
//             else return false;
//         }
//         return true;
//     }
// }


// Solution 2: same idea of 2 pointers, but we can reduce to O(1) space
// if we skip over non alpha-numeric chars using a isAlphaNumeric helper function, 
// rather than creating a new stripped str 
public class Solution {
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            while (l < r && !alphaNum(s.charAt(l))) {
                l++;
            }
            while (r > l && !alphaNum(s.charAt(r))) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++; r--;
        }
        return true;
    }

    public boolean alphaNum(char c) {
        return (c >= 'A' && c <= 'Z' ||
                c >= 'a' && c <= 'z' ||
                c >= '0' && c <= '9');
    }
}
