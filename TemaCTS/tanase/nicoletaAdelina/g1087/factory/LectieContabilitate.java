package tanase.nicoletaAdelina.g1087.factory;

public class LectieContabilitate extends AbstractLectie {
    public LectieContabilitate(String titlu, String text, int progres) {
        super(titlu, text, progres);
    }

    @Override
    public void evolutieLectie(int ev) {
        setTitlu("Contabilitate 1");
        setProgres(ev);
    }
}
