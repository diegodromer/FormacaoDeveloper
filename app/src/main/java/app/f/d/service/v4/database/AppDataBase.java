package app.f.d.service.v4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import app.f.d.service.v4.api.AppUtil;

public class AppDataBase  extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_service.sqlite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(Context context){
        super(context,DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            Log.i(AppUtil.TAG, "SQL -> "+AppUtil.TABELA_CONTAGEM);
            db.execSQL(AppUtil.TABELA_CONTAGEM);

        }catch (Exception e){

            Log.e(AppUtil.TAG,"DB Error: "+e.getMessage());
            Log.e(AppUtil.TAG, "SQL -> "+AppUtil.TABELA_CONTAGEM);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean insert(String tabela, ContentValues dados){

        boolean salvoComSucesso = true;

        try{

            salvoComSucesso = db.insert(tabela,null,dados) > 0;

        }catch (Exception e){

            salvoComSucesso = false;
        }

        return salvoComSucesso;

    }
}
