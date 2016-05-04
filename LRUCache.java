package DesignPatterns;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Syril on 30-04-2016.
 * <p/>
 * Requirements
 * <p/>
 * Fixed size: The cache needs to have some bounds to limit memory usage.
 * Fast access: The cache insert and lookup operations need to be fast preferably O(1) time.
 * Entry replacement algorithm: When the cache is full, the less useful cache entries are purged from cache. 
 * The algorithm to replace these entries is Least Recently Used (LRU) - or the cache entries which have not been accessed recently will be replaced.
 */

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity; //Maximum capacity of the cache

    public LRUCache(int capacity) {
        super(capacity + 1, 0.75f, true); // Pass 'true' for accessOrder.
        this.capacity = capacity;
    }

    /**
     * Returns <tt>true</tt> if this map should remove its eldest entry.
     * This method is invoked by <tt>put</tt> and <tt>putAll</tt> after
     * inserting a new entry into the map.  It provides the implementor
     * with the opportunity to remove the eldest entry each time a new one
     * is added.  This is useful if the map represents a cache: it allows
     * the map to reduce memory consumption by deleting stale entries.
     * <p/>
     * <p>Sample use: this override will allow the map to grow up to 100
     * entries and then delete the eldest entry each time a new entry is
     * added, maintaining a steady state of 100 entries.
     * <pre>
     *     private static final int MAX_ENTRIES = 100;
     *
     *     protected boolean removeEldestEntry(Map.Entry eldest) {
     *        return size() > MAX_ENTRIES;
     *     }
     * </pre>
     * <p/>
     * <p>This method typically does not modify the map in any way,
     * instead allowing the map to modify itself as directed by its
     * return value.  It <i>is</i> permitted for this method to modify
     * the map directly, but if it does so, it <i>must</i> return
     * <tt>false</tt> (indicating that the map should not attempt any
     * further modification).  The effects of returning <tt>true</tt>
     * after modifying the map from within this method are unspecified.
     * <p/>
     * <p>This implementation merely returns <tt>false</tt> (so that this
     * map acts like a normal map - the eldest element is never removed).
     *
     * @param eldest The least recently inserted entry in the map, or if
     *               this is an access-ordered map, the least recently accessed
     *               entry.  This is the entry that will be removed it this
     *               method returns <tt>true</tt>.  If the map was empty prior
     *               to the <tt>put</tt> or <tt>putAll</tt> invocation resulting
     *               in this invocation, this will be the entry that was just
     *               inserted; in other words, if the map contains a single
     *               entry, the eldest entry is also the newest.
     * @return <tt>true</tt> if the eldest entry should be removed
     * from the map; <tt>false</tt> if it should be retained.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.capacity;
    }
}
