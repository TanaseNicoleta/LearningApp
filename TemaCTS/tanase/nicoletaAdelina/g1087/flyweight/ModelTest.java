package tanase.nicoletaAdelina.g1087.flyweight;

import java.util.HashMap;

public class ModelTest implements InterfataTest {
    HashMap<String, String> intrebari =  new HashMap<>();
    String domeniu;

    public ModelTest(HashMap<String, String> intrebari, String domeniu) {
        this.intrebari = intrebari;
        this.domeniu = domeniu;
    }

    @Override
    public void InitializeazaTest() {
        intrebari.entrySet().forEach(stringStringEntry -> {
            System.out.println(stringStringEntry.getKey() + "  " + stringStringEntry.getValue());
        });
    }

    @Override
    public int calcNota() {
        int sum = (int) intrebari.entrySet().stream().count();
        return sum;
    }
}
