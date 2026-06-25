class Twitter {

    // Stores all tweets in the order they were posted
    private List<Tweet> tweets;
    
    // followerId -> set of followeeIds
    private Map<Integer, Set<Integer>> followMap;
    
    // Represents a tweet
    private class Tweet {
        int userId;
        int tweetId;
        Tweet(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }
    }

    public Twitter() {
        tweets = new ArrayList<>();
        followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        // Add tweet to the global tweet list
        tweets.add(new Tweet(userId, tweetId));

    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();
        // Users whose tweets should appear in the feed
        Set<Integer> visibleUsers = new HashSet<>();
        // User always sees their own tweets
        visibleUsers.add(userId);
        // Add everyone they follow
        if (followMap.containsKey(userId)) {
            visibleUsers.addAll(followMap.get(userId));
        }
        // Traverse from newest tweet to oldest tweet
        for (int i = tweets.size() - 1; i >= 0 && feed.size() < 10; i--) {
            Tweet curr = tweets.get(i);
            // If tweet belongs to user or someone they follow
            if (visibleUsers.contains(curr.userId)) {
                feed.add(curr.tweetId);
            }
        }
        return feed;
    }

    public void follow(int followerId, int followeeId) {
        // Create follow set if it doesn't exist
        followMap.putIfAbsent(followerId, new HashSet<>());
        // Add followee
        followMap.get(followerId).add(followeeId);

    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }

    }
}