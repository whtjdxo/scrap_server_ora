package sales.api.common;
import OwraEncrypt.Encrypt;
import java.lang.reflect.Method;

public class EncryptUtilOwra {
    private static Encrypt enc = new Encrypt();

    public static String decrypt(String encryptedData, String subDomain) {
        try {
            Method desJarMethod = enc.getClass().getDeclaredMethod("DesJar", String.class, String.class);
            desJarMethod.setAccessible(true);
            return (String) desJarMethod.invoke(enc, encryptedData, subDomain);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String data, String subDomain) {
        try {
            Method encJarMethod = enc.getClass().getDeclaredMethod("EncJar", String.class, String.class);
            encJarMethod.setAccessible(true);
            return (String) encJarMethod.invoke(enc, data, subDomain);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
