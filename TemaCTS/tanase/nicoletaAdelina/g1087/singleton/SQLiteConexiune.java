package tanase.nicoletaAdelina.g1087.singleton;

public class SQLiteConexiune {

    private static String path = "Lectii_economice";
    private static String mode = "CREATE_IF_NEEDED";

    public static final SQLiteConexiune conexiune = new SQLiteConexiune(path, mode);

    private SQLiteConexiune(String path, String mode) {
        this.path = path;
        this.mode = mode;
    }

}
