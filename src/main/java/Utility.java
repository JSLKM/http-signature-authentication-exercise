import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utility {
    public static String generateDigest(String bodyText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bodyText.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return "SHA-256=" + Base64.getEncoder().encodeToString(digest);
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = Utility.class.getClassLoader().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        return sb.toString();
    }

    public static String removeLeadingAndTrailingOWS(String value) {
        return value.trim();
    }
}
