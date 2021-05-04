package tanase.nicoletaAdelina.g1087.factory;

public abstract class AbstractLectie {
    String titlu;
    String text;
    int progres;

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProgres() {
        return progres;
    }

    public void setProgres(int progres) {
        this.progres = progres;
    }

    public AbstractLectie(String titlu, String text, int progres) {
        this.titlu = titlu;
        this.text = text;
        this.progres = progres;
    }

    public abstract void evolutieLectie(int ev);
}
