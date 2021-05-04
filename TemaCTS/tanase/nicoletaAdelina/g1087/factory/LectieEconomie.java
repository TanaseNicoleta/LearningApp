package tanase.nicoletaAdelina.g1087.factory;

public class LectieEconomie extends AbstractLectie {
    public LectieEconomie(String titlu, String text, int progres) {
        super(titlu, text, progres);
    }

    @Override
    public void evolutieLectie(int ev) {
        setTitlu("Economie 1");
        setProgres(ev);
    }
}
