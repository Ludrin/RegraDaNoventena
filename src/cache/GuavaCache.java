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
    private static GuavaCache guavaCache = new GuavaCache();

    /**
     * Get an instance of the cache to save data in.
     *
     * @return an instance of the GuavaCache class
     */
    public static GuavaCache getInstance() {
        return guavaCache;
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
        System.out.println("cache size:" + cache.size());
        return cache.get(cacheEntry);
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

    private GuavaCache() {
        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String cacheKey) throws Exception {
                        return cacheKey.toUpperCase();
                    }

                });
    }

    public static void main(String[] args) {
        GuavaCache guavaInstance = GuavaCache.getInstance();
        try {
            System.out.println(guavaInstance.getEntry("Suvendu"));
            if (guavaInstance.isPresent("Suvendu")) {
                System.out.println("present");
            } else {
                System.out.println("not present");
            }

            Thread.sleep(3000);
            if (guavaInstance.isPresent("Suvendu")) {
                System.out.println("present");
            } else {
                System.out.println("not present");
            }

            System.out.println(guavaInstance.getEntry("Suvendu"));
            if (guavaInstance.isPresent("Suvendu")) {
                System.out.println("present");
            } else {
                System.out.println("not present");
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}