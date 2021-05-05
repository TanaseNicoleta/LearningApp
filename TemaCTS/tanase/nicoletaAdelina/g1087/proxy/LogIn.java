package tanase.nicoletaAdelina.g1087.proxy;

import tanase.nicoletaAdelina.g1087.builder.User;

import java.nio.charset.Charset;
import java.util.Random;

public class LogIn implements InterfataLogIn {
    String server;
    User sessionUser;

    public LogIn(String server) {
        this.server = server;
    }


    @Override
    public boolean auth(String token) {
        if(token.isEmpty())
            return false;
        else
            return true;
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
