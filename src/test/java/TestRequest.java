import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

public class TestRequest {

    Request requestGet;
    Request requestPost;

    @Before
    public void setUp() {
        System.out.println("Start Test");

        LinkedHashMap<String, String> headersGet = new LinkedHashMap<>();
        headersGet.put("Host", "   example.org    ");
        headersGet.put("Date", "   Tue, 07 Jun 2014 20:51:35 GMT ");
        headersGet.put("X-Example", "Example header with some whitespace.");
        requestGet = new Request(
                null,
                "/foo",
                "GET" ,
                headersGet,
                null
        );

        LinkedHashMap<String, String> headersPost = new LinkedHashMap<>();
        headersPost.put("Date", "Tue, 07 Jun 2014 20:51:35 GMT");
        requestPost = new Request(
                null,
                "/wally-services/protocol/tests/signature",
                "POST",
                headersPost,
                null
        );
    }

    @Test
    public void testConstructSignatureString() {
        String actual = requestGet.ConstructSignatureString();
        String expected = "(request-target): get /foo\nhost: example.org\ndate: Tue, 07 Jun 2014 20:51:35 GMT\nx-example: Example header with some whitespace.";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetURL() {
        try {
            URL actual = requestGet.getURL();
            String expected = "https://staging.authservices.satispay.com/foo";
            Assert.assertEquals(expected, actual.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateSignature() {
        try {
            String actual = requestPost.CreateSignature();
            String expected = "SqfxsyDZWSuxxZyuJnuHBNB3nWUqmBLH43gEwGzuVnFEw/D5Tu5nkETvL9/iGJR+7gTED2lUujhUiCp7ScQ9LeT6kTorkJgicLuFgCvFwjyu3M8731lVMcNoDpJabSiFai+i8JumDSd7Qsl9F1JoQhuTw5Dnj/ocxZuP29aK0/xpLKyITj8FLSVudgFv/kZ3cGWRTF0Bzxh39iooN2Njp6JQq4v9xPe4ZIhXEk3Mcdl2LLPIKuFqCJmz0UvvuXksRJKrIDNQ6MLz4sfc69sY0h7Yi6/ffpenVKTtdSqQmd8BCEWXFgmgZCVc1m1i098znfXXSN72QbuGQryFB4lPBQ==";
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAuthorizationHeader() {
        try {
            String actual = requestPost.getAuthorizationValue("testKey", "testAlgorithm");
            String expected = "Signature keyId=\"testKey\",algorithm=\"testAlgorithm\",headers=\"(request-target) date\",signature=\"SqfxsyDZWSuxxZyuJnuHBNB3nWUqmBLH43gEwGzuVnFEw/D5Tu5nkETvL9/iGJR+7gTED2lUujhUiCp7ScQ9LeT6kTorkJgicLuFgCvFwjyu3M8731lVMcNoDpJabSiFai+i8JumDSd7Qsl9F1JoQhuTw5Dnj/ocxZuP29aK0/xpLKyITj8FLSVudgFv/kZ3cGWRTF0Bzxh39iooN2Njp6JQq4v9xPe4ZIhXEk3Mcdl2LLPIKuFqCJmz0UvvuXksRJKrIDNQ6MLz4sfc69sY0h7Yi6/ffpenVKTtdSqQmd8BCEWXFgmgZCVc1m1i098znfXXSN72QbuGQryFB4lPBQ==\"";
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostWithBody() {
        Request requestPostWithBody;
        LinkedHashMap<String, String> headersPost = new LinkedHashMap<>();
        headersPost.put("Date", "Tue, 07 Jun 2014 20:51:35 GMT");
        requestPostWithBody = new Request(
                null,
                "/wally-services/protocol/tests/signature",
                "POST",
                headersPost,
                "{\"hello\": \"world\"}"
        );

        String actualSignatureString = requestPostWithBody.ConstructSignatureString();
        String expectedSignatureString =
                "(request-target): post /wally-services/protocol/tests/signature\n" +
                "date: Tue, 07 Jun 2014 20:51:35 GMT\n" +
                "digest: SHA-256=X48E9qOokqqrvdts8nOJRJN3OWDUoyWxBf7kbu9DBPE=";
        Assert.assertEquals(expectedSignatureString, actualSignatureString);

        try {
            String actualSignature = requestPostWithBody.CreateSignature();
            String expectedSignature = "GKi040o7kdsuoeSVB8KWKsGeIEFqjqmFfWi9g6dNJ5fyJMzHB4PkUtQzNsZGa2TW58mfaM17xvKtjxJGYJy30tWlgkyq4I5/seu81+hu37ygQOjZ7dr08qdv5DhMCuS3W34LqQrHLcTJ2YuLBah8kGulnOuXFNE1m2rE7otyUtwyonw0rBPNtsEwiltXQEH6jqGQhfKQEtBoVq5chEaDEQt3V+Afg2KqpMjrTZqjr/OeTqgQGs8P6sI3htYQ9Wvd3K5z9zjv0e0Q3m4aTlUv9JHs5h/mqlZVl4m9hx6guwVwIPJAmPQfpXnJPAJUkTPmE5rPQZ0QzJkveWA0mwoCLQ==";
            Assert.assertEquals(expectedSignature, actualSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        System.out.println("End Test");
        requestGet = null;
        requestPost = null;
    }
}
