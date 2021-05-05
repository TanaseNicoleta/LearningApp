package tanase.nicoletaAdelina.g1087.builder;

public class User {
    private String nume;
    private String email;
    private String parola;
    private InterfataLectii lectiiUtilizator;

    public User() {
    }

    public User(String nume, String email, String parola, InterfataLectii lectiiUtilizator) {
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.lectiiUtilizator = lectiiUtilizator;
    }

    public User(String email, String parola) {
        this.email = email;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    public static class UserBuilder {
        User user;

        public UserBuilder(String email, String parola, String nume) {
            this.user = new User();
            this.user.email = email;
            this.user.parola = parola;
            this.user.nume = nume;
        }

        public UserBuilder setLectiiUtilizator(InterfataLectii lectii) {
            this.user.lectiiUtilizator = lectii;
            return this;
        }

        public User build() {
            return this.user;
        }
    }
}
