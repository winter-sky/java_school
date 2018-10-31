package utility;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BCryptUnilityTest {

    @Test
    public void encodeString() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345"));
        System.out.println(encoder.encode("123"));
    }
}
