package app.f.d.service.v4.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONObject;

import app.f.d.service.v4.R;
import app.f.d.service.v4.api.AppUtil;
import app.f.d.service.v4.api.RequestPostDados;
import app.f.d.service.v4.controller.ContadorController;
import app.f.d.service.v4.database.AppDataBase;
import app.f.d.service.v4.model.Contador;
import app.f.d.service.v4.service.ContagemService;

public class MainActivity extends AppCompatActivity {

    Intent intentContagemTask;
    Activity activity;
    Context ctx;

    Contador contador;
    ContadorController controller;

    TextView txtContagem;
    TextView txtData;
    TextView txtHora;
    TextView txtDescricao;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity = MainActivity.this;
        ctx = activity.getApplicationContext();

        AppDataBase appDataBase = new AppDataBase(ctx);

        txtContagem = findViewById(R.id.txtContagem);
        txtData = findViewById(R.id.txtData);
        txtHora = findViewById(R.id.txtHora);
        txtDescricao = findViewById(R.id.txtDescricao);

        controller = new ContadorController(ctx);

        initService();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Contagem atual: "+contador.getContagem(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initService() {

        intentContagemTask = new Intent(activity, ContagemService.class);
        activity.startService(intentContagemTask);
        activity.registerReceiver(broadcastReceiver,
                new IntentFilter(AppUtil.TAREFA_SERVICE_NOME));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI(Intent intent){

        int contagem;

        contagem = Integer.valueOf(intent.getStringExtra(AppUtil.TAREFA_SERVICE_ID));

        contador = new Contador(contagem);
        
        contador = controller.getObjetoContador(contador);

        if(controller.salvarContagem(contador)){
            Log.i(AppUtil.TAG,"Salvo com sucesso "+contador.getContagem());
            postDadosWeb(contador);
        }else{
            Log.e(AppUtil.TAG,"Falha ao Salvar "+contador.getContagem());

        }


        Log.e("LOG_Contagem","Contagem atual: "+contador.getContagem());
        Log.d("LOG_Contagem","Data atual....: "+contador.getDataContagem());
        Log.d("LOG_Contagem","Hora atual....: "+contador.getHoraContagem());
        Log.d("LOG_Contagem","Descrição.....: "+contador.getDescricaoContagem());

        txtContagem.setText(String.valueOf(contador.getContagem()));
        txtData.setText(contador.getDataContagem());
        txtHora.setText(contador.getHoraContagem());
        txtDescricao.setText(contador.getDescricaoContagem());

        Log.i(AppUtil.TAG,"Contagem "+contagem);

    }

    private void postDadosWeb(final Contador contador) {

        dialog = ProgressDialog.show(
                this,
                "Enviando contagem..." + contador.getContagem(),
        "Por favor, aguarde...",
                false,
                false
        );

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    boolean sucesso = jsonObject.getBoolean("sucesso");

                    if(sucesso){
                        //Toast.makeText(MainActivity.this.getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT).show();
                        Log.i(AppUtil.TAG_LOG, "Contagem: " + contador.getContagem() + " enviada com sucesso.");
                    }else{
                        Log.i(AppUtil.TAG_LOG, "Contagem: " + contador.getContagem() + " não enviada.");
                    }

                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }

                }catch (Exception e){
                    Log.e(AppUtil.TAG_LOG, "Erro Json: " + e.getMessage());
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                }

            }

        };

        RequestPostDados requestPostDados = new RequestPostDados(contador, listener, null);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(requestPostDados);

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            try {

                Log.i(AppUtil.TAG,"broadcastReceiver Ok");

                updateUI(intent);

            }catch (Exception e){

                Log.e(AppUtil.TAG,"broadcastReceiver "+e.getMessage());

            }

        }
    };



}
