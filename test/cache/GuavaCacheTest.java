package cache;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

/**
 * Test class for GuavaCache
 *
 * @author Ernani
 *
 */
public class GuavaCacheTest {

    private static final String AMAZON_COMPANY = "Amazon";
    private static final String GOOGLE_COMPANY = "Google";
    private static final long REFRESH_CACHE_SECONDS = 2;

    @Test
    public void getInstance_shouldReturnGuavaCacheInstance() {
        assertTrue(GuavaCache.getInstance(REFRESH_CACHE_SECONDS) instanceof GuavaCache);
    }

    @Test
    public void getEntry_withNewElement_shouldAddElementToCache() throws ExecutionException {
        GuavaCache guavaInstance = GuavaCache.getInstance(REFRESH_CACHE_SECONDS);
        guavaInstance.getEntry(AMAZON_COMPANY);

        assertTrue(guavaInstance.isPresent(AMAZON_COMPANY));
    }

    @Test
    public void getAllEntries_withNewElements_shouldAddElementsToCache() throws ExecutionException {
        GuavaCache guavaInstance = GuavaCache.getInstance(REFRESH_CACHE_SECONDS);
        guavaInstance.getEntry(AMAZON_COMPANY);
        guavaInstance.getEntry(GOOGLE_COMPANY);

        assertTrue(guavaInstance.getAllEntries().contains(AMAZON_COMPANY));
        assertTrue(guavaInstance.getAllEntries().contains(GOOGLE_COMPANY));
    }

    @Test
    public void removeEntry_withExistingElement_shouldRemoveElementFromCache() throws ExecutionException {
        GuavaCache guavaInstance = GuavaCache.getInstance(REFRESH_CACHE_SECONDS);
        guavaInstance.getEntry(AMAZON_COMPANY);
        assertTrue(guavaInstance.isPresent(AMAZON_COMPANY));

        guavaInstance.removeEntry(AMAZON_COMPANY);
        assertFalse(guavaInstance.isPresent(AMAZON_COMPANY));
    }

    @Test
    public void removeAllEntries_withExistingElements_shouldRemoveElementsFromCache() throws ExecutionException {
        GuavaCache guavaInstance = GuavaCache.getInstance(REFRESH_CACHE_SECONDS);
        guavaInstance.getEntry(AMAZON_COMPANY);
        guavaInstance.getEntry(GOOGLE_COMPANY);

        assertTrue(guavaInstance.getAllEntries().contains(AMAZON_COMPANY));
        assertTrue(guavaInstance.getAllEntries().contains(GOOGLE_COMPANY));

        guavaInstance.removeAllEntries();
        assertFalse(guavaInstance.isPresent(AMAZON_COMPANY));
        assertFalse(guavaInstance.isPresent(GOOGLE_COMPANY));
    }

    @Test
    public void isPresent_withNonExistingElementInCache_shouldReturnFalse() throws ExecutionException {
        GuavaCache guavaInstance = GuavaCache.getInstance(REFRESH_CACHE_SECONDS);

        assertFalse(guavaInstance.isPresent(AMAZON_COMPANY));
    }

    @Test
    public void isPresent_afterCacheIsRefreshed_shouldReturnFalse() throws ExecutionException, InterruptedException {
        GuavaCache guavaInstance = GuavaCache.getInstance(REFRESH_CACHE_SECONDS);
        guavaInstance.getEntry(AMAZON_COMPANY);
        Thread.sleep(2100);
        assertFalse(guavaInstance.isPresent(AMAZON_COMPANY));
    }
}
