/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
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
