package app.f.d.service.v4.controller;

import android.content.ContentValues;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import app.f.d.service.v4.database.AppDataBase;
import app.f.d.service.v4.model.Contador;

public class ContadorController extends AppDataBase {

    ContentValues dados;


    public ContadorController(Context context) {
        super(context);
    }

    public Contador getObjetoContador(Contador obj){


        String dataAtual = new SimpleDateFormat("dd-MM-yyyy",
                Locale.getDefault()).format(new Date());

        String horaAtual = new SimpleDateFormat("HH:mm:ss",
                Locale.getDefault()).format(new Date());

        obj.setDataContagem(dataAtual);
        obj.setHoraContagem(horaAtual);
        obj.setDescricaoContagem("Descrição "+obj.getContagem()+" - "+dataAtual+" - "+horaAtual);

        return obj;
    }

    //CRUD - INSERT ID PK NULL
    public boolean salvarContagem(Contador obj){

        dados = new ContentValues();

        dados.put("data", obj.getDataContagem());
        dados.put("hora", obj.getHoraContagem());
        dados.put("descricao", obj.getDescricaoContagem());

        return insert("contagem", dados);
    }
}
