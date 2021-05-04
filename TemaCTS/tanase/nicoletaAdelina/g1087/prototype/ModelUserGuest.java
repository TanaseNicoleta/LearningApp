package tanase.nicoletaAdelina.g1087.prototype;

import tanase.nicoletaAdelina.g1087.builder.InterfataLectii;
import tanase.nicoletaAdelina.g1087.factory.TipLectie;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelUserGuest implements Cloneable, InterfataLectii {
    String nume;
    HashMap<TipLectie, Integer> lectii = new HashMap<>();

    public ModelUserGuest() {
    }

    public ModelUserGuest(String nume) {
        this.nume = nume;
        this.returnLectii();
    }

    @Override
    public void returnLectii() {
        lectii.put(TipLectie.ECONOMIE, 2);
        lectii.put(TipLectie.FINANTE, 10);
        lectii.put(TipLectie.CONTABILITATTE, 5);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ModelUserGuest copie = new ModelUserGuest();
        copie.nume = this.nume;
        copie.lectii = (HashMap<TipLectie, Integer>) this.lectii.clone();
        return copie;
    }
}
