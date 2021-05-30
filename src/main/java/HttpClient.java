import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;


public class HttpClient {

    private Request request;
    private URL url;
    private LinkedHashMap<String, String> headers;
    private String body;
    private String method;
    private String keyId;
    private String algorithm;

    public HttpClient(Request request, String keyId, String algorithm) {
        try {
            this.request = request;
            this.url = request.getURL();
            this.method = request.getMethod();
            this.headers = request.getHeaders();
            this.body = request.getBody();
            this.keyId = keyId;
            this.algorithm = algorithm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest() {
        HttpURLConnection connection = null;
        if (!headers.containsKey("Date")) {
            Instant instant = Instant.now();
            String date = DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneOffset.UTC).format(instant);
            headers.put("Date", date);
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            for (Map.Entry<String, String> entry: headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            connection.setRequestProperty("Authorization", request.getAuthorizationValue(keyId, algorithm));

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (body != null && !body.trim().isEmpty()) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-type", "application/json");
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                osw.write(body);
                osw.flush();
                osw.close();
                os.close();
            }

            int status = connection.getResponseCode();
            System.out.println("STATUS CODE " + status);

            BufferedReader reader;
            String line;
            StringBuffer responseContent = new StringBuffer();

            reader = new BufferedReader(
                    status > 299
                            ? new InputStreamReader(connection.getErrorStream())
                            : new InputStreamReader(connection.getInputStream())
            );

            while((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            reader.close();
            JSONObject json = new JSONObject(responseContent.toString());
            System.out.println(json.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
}
