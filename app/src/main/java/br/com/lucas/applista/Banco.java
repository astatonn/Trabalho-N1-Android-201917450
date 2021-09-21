package br.com.lucas.applista;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

        private static final String NOME_BANCO = "AppLista";
        private static final int VERSAO = 1;

        public Banco(Context context){
            super (context, NOME_BANCO, null, VERSAO);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS produtos (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL, " +
                "categoria TEXT," +
                "valor FLOAT (10.2) NOT NULL," +
                "nomeMercado TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
