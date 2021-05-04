package tanase.nicoletaAdelina.g1087.decorator;

public class TestDecorator implements InterataTest {
    InterataTest testDecorat;

    public TestDecorator(InterataTest testDecorat) {
        this.testDecorat = testDecorat;
    }

    @Override
    public String Test() {
        return testDecorat.Test();
    }
}
