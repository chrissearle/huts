import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import org.apache.commons.codec.binary.Hex

class RandomService {

    boolean transactional = true

    def getRandomKey(int length) {
        try {
            Random rand = SecureRandom.getInstance("SHA1PRNG");

            byte[] password = new byte[length];

            for (int x = 0; x < length; x++) {
                int randDecimalAsciiVal = rand.nextInt(93) + 33;
                password[x] = (byte) randDecimalAsciiVal;
            }

            return String.valueOf(Hex.encodeHex(password));
        } catch (NoSuchAlgorithmException e) {
            return ""
        }
    }

}
