import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class KlucGen {
    private SecretKey kluc;

    public KlucGen() {
        this.kluc = genKluc();
    }

    public Key getKluc() {
        return kluc;
    }



    public static SecretKey genKluc(){
        KeyGenerator klucGenerovany = null;
        try {
            klucGenerovany = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecureRandom secureRandom = new SecureRandom();
        klucGenerovany.init(128, secureRandom);
        SecretKey tajnyKluc = klucGenerovany.generateKey();
        return tajnyKluc;
    }

    public String klucDoStringu() {

        byte encoded[] = this.kluc.getEncoded();

        String encodedKey = Base64.getEncoder().encodeToString(encoded);

        return encodedKey;
    }

    public static SecretKey stringDoKluca(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);


        SecretKey secretKey = new SecretKeySpec(decodedKey, 0,
                decodedKey.length, "AES");

        return secretKey;
    }

}
