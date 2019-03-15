package cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Contains methods to manipulate and search the cache.
 *
 * @author Ernani
 *
 */
public class GuavaCache {

    private LoadingCache<String, String> cache;

    /**
     * Get an instance of the cache to save data in.
     *
     * @return an instance of the GuavaCache class
     */
    public static GuavaCache getInstance(long refreshCacheSeconds) {
        return new GuavaCache(refreshCacheSeconds);
    }

    /**
     * Get an entry stored in the cache based on its key.
     * By Guava internal implementation this method will also add the entry in case it's not present in the cache.
     *
     * @param cacheEntry a String representing the entry to be searched
     * @return the located entry
     * @throws ExecutionException thrown internally by Guava in case of errors manipulating cache
     */
    public String getEntry(String cacheEntry) throws ExecutionException {
        return cache.get(cacheEntry);
    }

    /**
     * Get all entries in the cache as a String.
     *
     * @return the String containing all elements of the cache
     * @throws ExecutionException thrown internally by Guava in case of errors manipulating cache
     */
    public String getAllEntries() throws ExecutionException {
        return cache.asMap().toString();
    }

    /**
     * Remove a specific entry from the cache.
     *
     * @param entryKey the key of the entry to be removed
     */
    public void removeEntry(String entryKey) {
        cache.invalidate(entryKey);
    }

    /**
     * Remove all entries from the cache.
     */
    public void removeAllEntries() {
        cache.invalidateAll();
    }

    /**
     * Checks whether the specified key exists in the cache.
     *
     * @param cacheKey the String to be checked if is present in the cache.
     * @return true if the key is present, false if it isn't
     */
    public Boolean isPresent(String cacheKey) {
        return cache.asMap().containsKey(cacheKey);
    }

    private GuavaCache(long refreshCacheSeconds) {
        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(refreshCacheSeconds, TimeUnit.SECONDS)
                .expireAfterWrite(refreshCacheSeconds, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String cacheKey) throws Exception {
                        return cacheKey.toUpperCase();
                    }

                });
    }

}