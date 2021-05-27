import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class TestUtility {
    @Test
    public void testGenerateDigest() {
        String bodyText = "{\"hello\": \"world\"}";
        try {
            String actual = Utility.generateDigest(bodyText);
            String expected = "SHA-256=X48E9qOokqqrvdts8nOJRJN3OWDUoyWxBf7kbu9DBPE=";
            Assert.assertEquals(expected, actual);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
