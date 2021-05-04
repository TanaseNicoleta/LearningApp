package tanase.nicoletaAdelina.g1087.decorator;

import tanase.nicoletaAdelina.g1087.factory.TipLectie;

public class TestContabilitate implements InterataTest {
    @Override
    public String Test() {
        return String.valueOf(TipLectie.CONTABILITATTE);
    }
}
