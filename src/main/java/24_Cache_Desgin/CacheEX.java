import java.util.*;


// ============================================================
// NODE
// ============================================================

class Node {

    int key;
    String value;

    Node prev;
    Node next;
}


// ============================================================
// EVICTION POLICY - STRATEGY
// ============================================================

interface EvictionPolicy {

    Node evict();
}


// ============================================================
// LRU STRATEGY
// ============================================================

class LRU implements EvictionPolicy {

    private Node tail;

    LRU(Node tail) {
        this.tail = tail;
    }

    @Override
    public Node evict() {

        // Node before TAIL = LRU
        return tail.prev;
    }
}


// ============================================================
// CACHE
// ============================================================

class Cache {

    // KEY -> NODE
    private HashMap<Integer, Node> map = new HashMap<>();

    // Dummy nodes
    private Node head;
    private Node tail;

    // Maximum number of items
    private int capacity;

    // Strategy
    private EvictionPolicy evictionPolicy;


    // ========================================================
    // CONSTRUCTOR
    // ========================================================

    Cache(int capacity) {

        this.capacity = capacity;

        // Dummy HEAD and TAIL
        head = new Node();
        tail = new Node();

        // Empty DLL
        head.next = tail;
        tail.prev = head;

        // Use LRU strategy
        evictionPolicy = new LRU(tail);
    }


    // ========================================================
    // ADD NODE AT HEAD
    // ========================================================

    private void addToHead(Node node) {

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }


    // ========================================================
    // REMOVE NODE
    // ========================================================

    private void removeNode(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    // ========================================================
    // PUT
    // ========================================================

    public void put(int key, String value) {

        // Check if key already exists
        Node node = map.get(key);


        // ----------------------------------------------------
        // EXISTING KEY
        // ----------------------------------------------------

        if (node != null) {

            // Update value
            node.value = value;

            // Move to HEAD because recently used
            removeNode(node);
            addToHead(node);

            return;
        }


        // ----------------------------------------------------
        // NEW KEY
        // ----------------------------------------------------

        // Cache full
        if (map.size() == capacity) {

            // Ask strategy which node to remove
            Node victim = evictionPolicy.evict();

            // Remove from DLL
            removeNode(victim);

            // Remove from HashMap
            map.remove(victim.key);
        }


        // Create new node
        node = new Node();

        node.key = key;
        node.value = value;

        // Store in HashMap
        map.put(key, node);

        // New node = MRU
        addToHead(node);
    }


    // ========================================================
    // GET
    // ========================================================

    public String get(int key) {

        Node node = map.get(key);

        // Key not found
        if (node == null) {
            return null;
        }

        // Recently used → move to HEAD
        removeNode(node);
        addToHead(node);

        return node.value;
    }
}


// ============================================================
// MAIN
// ============================================================

public class CacheEX {

    public static void main(String[] args) {

        Cache cache = new Cache(3);

        cache.put(1, "shivam");
        cache.put(200, "sakshi");
        cache.put(4, "malaika");

        System.out.println(cache.get(4));

        cache.put(10, "microsoft");

        System.out.println(cache.get(1));
    }
}