package tanase.nicoletaAdelina.g1087.proxy;

public interface InterfataLogIn {
    public boolean auth(String token);
    public String generateAuthToken(String email, String password);
}
