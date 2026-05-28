class Solution {
    public String encode(List<String> strs) {
        // O(m) time for each encode/decode, O(m + n) space
        // m is sum of all lengths of the strings, n is num strings
        
        StringBuilder encoded = new StringBuilder();
        for (String str : strs) {
            // Store the length first so decoding knows exactly how many chars to read
            // This avoids the delimiter problem: the string itself can contain "#", 
            // for example, if we use it as a delimiter (str can have any of 256 ASCII)
            encoded.append(str.length());
            encoded.append("#"); // needed in case str itself has integers in it
            encoded.append(str);
        }
        return encoded.toString();
    }

    public List<String> decode(String str) {
        List<String> decoded = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            // i = where does the next encoded string begin
            // j = where is the delimiter (# in this case)
            // Move j until it reaches the separator after the length
            while (str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            
            // Start of the actual string is right after "#"
            int start = j + 1;
            int end = start + length;

            // Extract exactly "length" characters
            decoded.add(str.substring(start, end));

            // Move i to the start of the next encoded string
            i = end;
        }
        return decoded;
    }
}