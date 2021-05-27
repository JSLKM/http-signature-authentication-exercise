
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Server {
    public static void main(String[] args) {
        try {
            String bodyText = "{\"hello\": \"world\"}";
            String digest = Utility.generateDigest(bodyText);
            System.out.println(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the script");
//
//        boolean done = false;
//        while(!done && scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            System.out.println("Echo from <Your Name Here> Server: " + line);
//
//            if(line.toLowerCase().trim().equals("peace")) {
//                done = true;
//            }
//        }
    }
}