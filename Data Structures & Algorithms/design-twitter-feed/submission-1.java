public class Twitter {
    // Monotonically decreasing timestamp
    // Smaller value = newer tweet
    private int count;

    // Maps each user -> list of {timestamp, tweetId}
    // Each user's tweets are already stored in chronological order
    private Map<Integer, List<int[]>> tweetMap;

    // Maps each user -> users they follow
    private Map<Integer, Set<Integer>> followMap;

    public Twitter() {
        this.count = 0;
        this.tweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        // Create the user's tweet list if necessary
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>())
                // Store {timestamp, tweetId}
                .add(new int[]{count, tweetId});
        
        // Only keep the 10 most recent tweets for each user
        // Older tweets can never appear in a news feed
        if (tweetMap.get(userId).size() > 10) {
            tweetMap.get(userId).remove(0);
        }
        
        // Decrement timestamp so newer tweets always have
        // smaller values (higher priority in the min-heap)
        count--;
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        
        // Heap ordered by timestamp
        // Each element stores:
        // {timestamp, tweetId, userId, nextOlderTweetIndex}
        PriorityQueue<int[]> minHeap =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Users always follow themselves
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        
        // Add the newest tweet from every followed user
        for (int followeeId : followMap.get(userId)) {
            if (!tweetMap.containsKey(followeeId)) {
                continue;
            }
            List<int[]> tweets = tweetMap.get(followeeId);
            int newestIndex = tweets.size() - 1;
            int[] newestTweet = tweets.get(newestIndex);
            minHeap.offer(new int[]{
                newestTweet[0],          // timestamp
                newestTweet[1],          // tweetId
                followeeId,              // owner
                newestIndex - 1          // next older tweet
            });
        }

        // Merge the sorted tweet lists
        while (!minHeap.isEmpty() && res.size() < 10) {
            int[] top = minHeap.poll();
            // Add the newest available tweet
            res.add(top[1]);
            int nextIndex = top[3];
            
            // If this user has an older tweet,
            // insert it into the heap
            if (nextIndex >= 0) {
                List<int[]> tweets = tweetMap.get(top[2]);
                int[] nextTweet = tweets.get(nextIndex);
                minHeap.offer(new int[]{
                    nextTweet[0],
                    nextTweet[1],
                    top[2],
                    nextIndex - 1
                });
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>())
                 .add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}