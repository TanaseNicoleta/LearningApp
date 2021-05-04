package tanase.nicoletaAdelina.g1087;

import tanase.nicoletaAdelina.g1087.builder.User;
import tanase.nicoletaAdelina.g1087.adapter.UserConcret;
import tanase.nicoletaAdelina.g1087.decorator.InterataTest;
import tanase.nicoletaAdelina.g1087.decorator.TestContabilitate;
import tanase.nicoletaAdelina.g1087.decorator.TestEconomie;
import tanase.nicoletaAdelina.g1087.decorator.TestFinante;
import tanase.nicoletaAdelina.g1087.factory.AbstractLectie;
import tanase.nicoletaAdelina.g1087.factory.LectieFactory;
import tanase.nicoletaAdelina.g1087.factory.TipLectie;
import tanase.nicoletaAdelina.g1087.prototype.ModelUserGuest;
import tanase.nicoletaAdelina.g1087.singleton.SQLiteConexiune;

public class TestAssignment {
    public static void main(String[] args) throws CloneNotSupportedException {
        //Singleton
        SQLiteConexiune con1 = SQLiteConexiune.conexiune;
        SQLiteConexiune con2 = SQLiteConexiune.conexiune;


        if(con1 == con2) {
            System.out.println("Cele 2 referinte sunt identice");
        } else {
            System.out.println("Cele 2 referinte sunt diferite");
        }

        //Factory
        AbstractLectie lectie = null;

        lectie = LectieFactory.getLectie(TipLectie.ECONOMIE, "Lectie 1");
        lectie = LectieFactory.getLectie(TipLectie.FINANTE, "Lectie 1");
        lectie = LectieFactory.getLectie(TipLectie.CONTABILITATTE, "Lectie 1");

        //Prototype
        ModelUserGuest userGuest = new ModelUserGuest("Andrei");
        ModelUserGuest copieUser = (ModelUserGuest) userGuest.clone();

        //Decorator
        InterataTest testC = new TestContabilitate();
        InterataTest testE = new TestEconomie();
        InterataTest testF = new TestFinante();

        System.out.println(testC.Test());
        System.out.println(testE.Test());
        System.out.println(testF.Test());

        //Builder
        User user = new User.UserBuilder("user1@gmail.com", "12345678", "User1").setLectiiUtilizator(() -> System.out.println("Utilizatorul are 6 lectii terminate")).build();
        User user2 = new User.UserBuilder("user2@gmail.com", "023456789", "User2").setLectiiUtilizator(() -> System.out.println("Utilizatorul are 12 lectii terminate")).build();

        System.out.println(user.getNume());
        System.out.println(user2.getNume());


        //Adapter
        UserConcret userConcret1 = new UserConcret();
        userConcret1.readLessons("", "");
        userConcret1.readLessons("Ana", "");
        userConcret1.readLessons("Ana", "12356");

    }
}
