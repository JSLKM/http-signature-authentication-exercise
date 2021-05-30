import org.json.JSONException;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) {

        try {
            Configuration config = new Configuration("config.json");
            Request requestGet = new Request(
                    config.getHost(),
                    config.getResourcePath(),
                    config.getMethod(),
                    config.getHeaders(),
                    config.getBody()
            );
            HttpClient httpClient = new HttpClient(requestGet, config.getKeyId(), config.getAlgorithm());
            httpClient.sendRequest();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}