public class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

public class LRUCache {
    // O(1) time for each put and get, O(n) space

    // Maximum number of entries the cache can hold
    private int cap;
    // Maps each key to its corresponding node in the linked list
    private HashMap<Integer, Node> cache;

    // Dummy nodes marking the beginning and end of the list
    // left.next is the least recently used (LRU)
    // right.prev is the most recently used (MRU)
    private Node left;
    private Node right;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new HashMap<>();

        // Initialize the empty doubly linked list
        this.left = new Node(0, 0);
        this.right = new Node(0, 0);

        left.next = right;
        right.prev = left;
    }

    // Removes a node from the doubly linked list
    private void remove(Node node) {
        Node prev = node.prev;
        Node nxt = node.next;
        prev.next = nxt;
        nxt.prev = prev;
    }

    // Inserts a node just before the right dummy node,
    // making it the most recently used (MRU)
    private void insert(Node node) {
        Node prev = right.prev;
        prev.next = node;
        node.prev = prev;
        node.next = right;
        right.prev = node;
    }

    public int get(int key) {
        // Key not found
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Move the accessed node to the end of the list,
        // since it is now the most recently used
        Node node = cache.get(key);
        remove(node);
        insert(node);
        return node.val;
    }

    public void put(int key, int value) {
        // If the key already exists, remove its old node.
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        // Create the new (or updated) node
        Node newNode = new Node(key, value);

        // Store it in the map and mark it as most recently used
        cache.put(key, newNode);
        insert(newNode);

        // If we've exceeded capacity, remove the least recently used node
        if (cache.size() > cap) {
            Node lru = left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }
}