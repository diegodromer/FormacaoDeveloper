package app.f.d.service.v4.api;

public class AppUtil {

    public static final String TAG = "sucesso";
    public static final String TAG_LOG = "app_service";
    public static final String TAREFA_SERVICE_ID = "tarefaID";
    public static final String TAREFA_SERVICE_NOME = "app.f.d.service.contagem";

    public static final int PAUSE_SPLASH = 3 * 1000;
    public static final int SERVICE_DALAY = 5 * 1000;

    // CREATE TABLE

    public static final String TABELA_CONTAGEM = "CREATE TABLE contagem ( id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT, hora TEXT, descricao TEXT )";

    public static final String URL_POST = "http://localhost/aluno/services/postDadosServices.php";

}
