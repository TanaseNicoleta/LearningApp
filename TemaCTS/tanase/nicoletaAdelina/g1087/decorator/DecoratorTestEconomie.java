package tanase.nicoletaAdelina.g1087.decorator;

public class DecoratorTestEconomie extends TestDecorator {
    int difficulty;

    public DecoratorTestEconomie(InterataTest testDecorat, int difficulty) {
        super(testDecorat);
        this.difficulty = difficulty;
    }

    public DecoratorTestEconomie(InterataTest testDecorat) {
        super(testDecorat);
    }

    @Override
    public String Test() {
        return super.Test();
    }

    private void setDifficulty(int diff) {
        this.difficulty = diff;
    }
}
