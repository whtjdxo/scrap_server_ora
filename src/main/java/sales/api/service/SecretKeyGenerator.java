package sales.api.service;
import java.util.Base64;
public class SecretKeyGenerator {
    public static void main(String[] args) {
        byte[] key = new byte[32]; // 256 bit
        new java.security.SecureRandom().nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("Base64 Encoded Key: " + encodedKey);
    }
}
