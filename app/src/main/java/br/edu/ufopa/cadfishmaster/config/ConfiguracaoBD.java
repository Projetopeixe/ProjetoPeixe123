package br.edu.ufopa.cadfishmaster.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConfiguracaoBD extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String CadFish_BD = "bd_cadfish";


    public ConfiguracaoBD(@Nullable Context context) {
        super(context, CadFish_BD, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


