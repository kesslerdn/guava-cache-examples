import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CacheBuilderTest {

    private int index = 0;

    LoadingCache<String, String> timeBasedCache = CacheBuilder.newBuilder().
            expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            System.out.println("Retrieving a new time based value...");
            index++;
            return "The returned index is " + index + ".";
        }

    });


    @Test
    public void testOverTenSecondsOfTime() throws Exception {
        for(int i = 0; i < 10 ; i++){
            System.out.println("\tfirst time based - " + timeBasedCache.get("first"));
            System.out.println("\tsecond time based - " + timeBasedCache.get("second"));
            Thread.sleep(2000);
        }
    }
}
