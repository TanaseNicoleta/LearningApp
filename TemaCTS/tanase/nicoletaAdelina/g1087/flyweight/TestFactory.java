package tanase.nicoletaAdelina.g1087.flyweight;

import java.util.HashMap;

public class TestFactory {

    static HashMap<String, ModelTest> teste = new HashMap<>();

    public static ModelTest createModelTest(String domeniu, HashMap<String, String> intrebari) {
        ModelTest newModelTest = new ModelTest(intrebari, domeniu);
        teste.put(domeniu, newModelTest);
        return newModelTest;
    }

    public static ModelTest getDomenii(String domeniu) {
        return teste.get(domeniu);
    }
}


