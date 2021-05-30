import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Configuration {

    private String host;
    private String resourcePath;
    private String method;
    private String keyId;
    private String algorithm;
    private LinkedHashMap<String, String> headers = null;
    private String body = null;

    public String getKeyId() {
        return keyId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getHost() {
        return host;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getMethod() {
        return method;
    }

    public  LinkedHashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    private static JSONObject getConfiguration(String configPath) throws IOException, JSONException {
        String jsonString = Utility.readFile(configPath);
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    public Configuration(String configPath) throws JSONException, IOException {
        JSONObject jsonObject = getConfiguration(configPath);

        String[] requiredFields = {"host", "resourcePath", "method", "keyId", "body", "algorithm"};

        for (String field : requiredFields) {
            if (!jsonObject.has(field)) {
                throw new InvalidPropertiesFormatException("Missing required fields: host, resourcePath, method, keyId, body, algorithm");
            }
        }

        this.host = jsonObject.getString("host");
        this.resourcePath = jsonObject.getString("resourcePath");
        this.method = jsonObject.getString("method");
        this.keyId = jsonObject.getString("keyId");
        this.algorithm = jsonObject.getString("algorithm");
        this.body = jsonObject.getString("body");

        if (jsonObject.has("headers")) {
            JSONObject headersJSONObject = jsonObject.getJSONObject("headers");

            Iterator<String> keys = headersJSONObject.keys();
            this.headers = new LinkedHashMap<>();

            while(keys.hasNext()) {
                String key = keys.next();
                this.headers.put(key, headersJSONObject.getString(key));
            }
        }
    }


}
