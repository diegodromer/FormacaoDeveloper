package app.f.d.service.v4.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import app.f.d.service.v4.api.AppUtil;

/**
 * Veja a documentação oficial
 * @link http://bit.ly/CursoFDDocService
 */
public class ContagemService extends Service {
    
    Handler handler = new Handler();
    Intent intentContagemService;
    int contador = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w(AppUtil.TAG,"onBind()" );
        return null;
    }

    /**
     * Definição no nome da tarefa - Ver (AndroidManifest.xml)
     */
    @Override
    public void onCreate() {

     intentContagemService = new Intent(AppUtil.TAREFA_SERVICE_NOME);


    }

    /**
     * START_NOT_STICKY
     * Se o sistema interromper o serviço após o retorno de onStartCommand(),
     * não recrie o serviço, a menos que haja intenções pendentes de entrega.
     * Essa é a opção mais segura para evitar a execução do serviço quando não
     * for necessário e quando o aplicativo puder simplesmente reiniciar os
     * trabalhos não concluídos.
     *
     * START_STICKY
     * Se o sistema interromper o serviço após o retorno de onStartCommand(),
     * recrie o serviço e chame onStartCommand(), mas não entregue novamente
     * a última intenção. Em vez disso, o sistema chama onStartCommand()
     * com uma intenção nula, a menos que haja intenções pendentes para
     * iniciar o serviço. Nesse caso, essas intenções são entregues.
     * Isso é adequado para players de mídia (ou serviços similares) que não
     * estão executando comandos, mas estão executando indefinidamente e
     * aguardando um trabalho.
     *
     * START_REDELIVER_INTENT
     * Se o sistema matar o serviço após o retorno de onStartCommand(),
     * recrie o serviço e chame onStartCommand() com a última intenção que foi
     * entregue ao serviço. Quaisquer intenções pendentes são entregues por sua vez.
     * Isso é adequado para serviços que estão executando ativamente um trabalho
     * que deve ser retomado imediatamente, como o download de um arquivo.
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        handler.removeCallbacks(atualizarContagemUI);
        handler.postDelayed(atualizarContagemUI,AppUtil.SERVICE_DALAY);


        return START_STICKY;

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.w(AppUtil.TAG,"service: onLowMemory()" );
    }

    /**
     * Tarefa de contagem que será executada pela Service
     * em segundo-plano.
     */
    private Runnable atualizarContagemUI = new Runnable() {
        public void run() {
            
            contador++;
            
            intentContagemService.putExtra(AppUtil.TAREFA_SERVICE_ID,
                    String.valueOf(contador));
            
            sendBroadcast(intentContagemService);
            
            handler.postDelayed(this,AppUtil.SERVICE_DALAY);


        }
    };


}
