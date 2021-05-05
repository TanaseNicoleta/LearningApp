package tanase.nicoletaAdelina.g1087.proxy;

import tanase.nicoletaAdelina.g1087.builder.User;

import java.nio.charset.Charset;
import java.util.Random;

public class PoxyLogIn implements InterfataLogIn {
    InterfataLogIn intLogIn = null;
    User sessionUser;

    public PoxyLogIn(InterfataLogIn intLogIn) {
        this.intLogIn = intLogIn;
    }

    @Override
    public boolean auth(String token) {
        if(intLogIn.auth(token))
            return true;
        else
            return false;
    }

    @Override
    public String generateAuthToken(String email, String password) {
        String authToken = generateRandomString();
        return authToken;
    }

    public String generateRandomString() {
        byte[] array = new byte[12]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}
