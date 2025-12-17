package com.example.cyberencryptor;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Arrays;
import java.security.MessageDigest;

public class AESUtils {

    private static SecretKeySpec secretKey;

    // This helper function turns a user's password (like "cats") into a valid 16-byte AES key
    public static void setKey(String myKey) {
        try {
            byte[] key = myKey.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // Force it to be 16 bytes (128-bit security)
            secretKey = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ENCRYPT FUNCTION
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret); // Prepare the key
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Convert string to bytes, encrypt, then convert to Base64 string so we can read it
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
    }

    // DECRYPT FUNCTION
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret); // Prepare the key
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decode Base64, decrypt, then convert back to normal string
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            return "Error: Wrong Password or Invalid Data";
        }
    }
}
