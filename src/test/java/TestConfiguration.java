import org.junit.Assert;
import org.junit.Test;

import java.util.InvalidPropertiesFormatException;

public class TestConfiguration {
    @Test
    public void testConfiguration() {
        try {
            Configuration config = new Configuration("config.test.json");
            Assert.assertEquals("host.com", config.getHost());
            Assert.assertEquals("/foo", config.getResourcePath());
            Assert.assertEquals("GET", config.getMethod());
            Assert.assertEquals("something", config.getBody());
            Assert.assertEquals("key-id", config.getKeyId());
            Assert.assertEquals("alg-id", config.getAlgorithm());
            Assert.assertEquals("test-headers", config.getHeaders().get("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThrowsException() {
        Assert.assertThrows(InvalidPropertiesFormatException.class, () -> {new Configuration("config.missing.required.test.json");});
    }
}
