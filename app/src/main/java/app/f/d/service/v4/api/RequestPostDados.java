package app.f.d.service.v4.api;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import app.f.d.service.v4.model.Contador;

public class RequestPostDados extends StringRequest {

    Map<String, String> dados;

    public RequestPostDados(
            Contador contador,
            Response.Listener<String> listener,
            @Nullable Response.ErrorListener errorListener
    ) {
        super(Method.POST, AppUtil.URL_POST, listener, null);

        dados = new HashMap<>();
        dados.put("token", "FDSERVICES");
        dados.put("id_task", String.valueOf(contador.getContagem()));
        dados.put("data", contador.getDataContagem());
        dados.put("hora", contador.getHoraContagem());
        dados.put("descricao", contador.getDescricaoContagem());

    }

    @Override
    public Map<String, String> getParams(){
        return dados;
    }

}
