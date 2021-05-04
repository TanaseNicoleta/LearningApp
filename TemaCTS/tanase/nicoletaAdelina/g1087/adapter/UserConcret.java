package tanase.nicoletaAdelina.g1087.adapter;

public class UserConcret implements InterfataGuest {
    UserAdapter userAdapter;
    InterfataUserAutentificat auth;
    String email, password;

    @Override
    public void readLessons(String email, String password) {
        if(!password.equals("") && !email.equals("")) {
            userAdapter = new UserAdapter(auth);
            userAdapter.readLessons(email, password);
        } else
            System.out.println("Guest");
    }
}
