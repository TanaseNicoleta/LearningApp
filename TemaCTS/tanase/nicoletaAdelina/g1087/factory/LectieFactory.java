package tanase.nicoletaAdelina.g1087.factory;

public class LectieFactory {

    public static AbstractLectie getLectie(TipLectie tipLectie, String text) {
        AbstractLectie lectie = null;
        int i = 0;

       if(tipLectie.equals("ECONOMIE")) {
            lectie = new LectieEconomie("Economie" + i, text, 0);
            return lectie;
       } else
           if(tipLectie.equals("FINANTE")) {
               lectie = new LectieEconomie("Finante" + i, text, 0);
               return lectie;
           } else
               if(tipLectie.equals("CONTABILITATE")) {
                   lectie = new LectieEconomie("Contabilitate" + i, text, 0);
                   return lectie;
               } else
                   return null;
    }
}
