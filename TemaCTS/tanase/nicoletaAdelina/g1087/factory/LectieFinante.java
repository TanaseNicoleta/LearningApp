package tanase.nicoletaAdelina.g1087.factory;

public class LectieFinante extends AbstractLectie {
    public LectieFinante(String titlu, String text, int progres) {
        super(titlu, text, progres);
    }

    @Override
    public void evolutieLectie(int ev) {
        setTitlu("Finante 1");
        setProgres(ev);
    }
}
