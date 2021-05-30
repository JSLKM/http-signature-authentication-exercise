import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class Request {
    private String method;
    private String resourcePath;
    private LinkedHashMap<String, String> headers;
    private String body;
    private String host;

    public Request(String host, String resourcePath, String method, LinkedHashMap<String, String> headers, String body) {
        this.host = (host == null) ? "staging.authservices.satispay.com" : host;
        this.method = method;
        this.resourcePath = resourcePath;
        this.headers = headers;
        this.headers.replaceAll((k, v) -> Utility.removeLeadingAndTrailingOWS(v));
        this.body = body;

        if (this.body != null && !this.body.trim().isEmpty()) {
            try {
                this.headers.put("Digest", Utility.generateDigest(body));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public LinkedHashMap<String, String> getHeaders() {
        return headers;
    }

    public String ConstructSignatureString() {
        String result = "(request-target): " + method.toLowerCase() + " " + resourcePath;
        for (Map.Entry<String, String> entry: headers.entrySet()) {
            result = result + "\n" + entry.getKey().toLowerCase() + ": " + entry.getValue();
        }
        System.out.println("\n-----------BEGIN: Construct Signature String-----------");
        System.out.println(result);
        System.out.println("-----------END: Construct Signature String-----------\n");
        return result;
    }

    public String CreateSignature() throws Exception {
        String input = ConstructSignatureString();
        String keyString = Utility.readFile("keys/client-rsa-private-key.pem")
                .replaceAll("-----END PRIVATE KEY-----", "")
                .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("\n", "");

        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        Signature privateSignature = Signature.getInstance("SHA256withRSA");

        privateSignature.initSign(kf.generatePrivate(spec));
        privateSignature.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] rsaBytes = privateSignature.sign();

        String result = Base64.getEncoder().encodeToString(rsaBytes);

        System.out.println("\n-----------BEGIN: Create Signature-----------");
        System.out.println(result);
        System.out.println("-----------END: Create Signature-----------\n");
        return result;
    }

    private String getAuthorizationHeaders() {
        String result = "(request-target)";
        for (Map.Entry<String, String> entry: headers.entrySet()) {
            result = result + " " + entry.getKey().toLowerCase();
        }
        return result;
    }

    public String getAuthorizationValue(String keyId, String algorithm) throws Exception {
        String signature = CreateSignature();
        String headersString = getAuthorizationHeaders();
        String result =  String.format("Signature keyId=\"%s\",algorithm=\"%s\",headers=\"%s\",signature=\"%s\"", keyId, algorithm, headersString, signature);

        System.out.println("\n-----------BEGIN: Get Authorization value-----------");
        System.out.println(result);
        System.out.println("-----------END: Get Authorization value-----------\n");
        return result;
    }

    public URL getURL() throws MalformedURLException {
        String url = String.format("https://%s%s", host, resourcePath);
        return new URL(url);
    }
}

