package tanase.nicoletaAdelina.g1087.adapter;

public class UserAdapter implements InterfataGuest {
    InterfataUserAutentificat userAutentificat;
    String email, pass;

    public UserAdapter(InterfataUserAutentificat userAutentificat) {
        this.userAutentificat = new UserAutentificat();
    }

    @Override
    public void readLessons(String email, String password) {
        if(!password.equals("") && !email.equals("")) {
            userAutentificat.readLessons(email, password);
        } else
            System.out.println("Guest");
    }
}
