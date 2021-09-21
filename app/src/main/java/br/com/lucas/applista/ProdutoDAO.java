package br.com.lucas.applista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void inserir (Context context, Produto produto){
        ContentValues valores = new ContentValues();
        valores.put ("nome", produto.getNome());
        valores.put ("categoria", produto.getCategoria());
        valores.put ("nomeMercado", produto.getNomeMercado());
        valores.put ("valor", produto.getValor());

        // CRIAR UM OBJETO DO TIPO BANCO
        Banco conn = new Banco (context);
        SQLiteDatabase db = conn.getWritableDatabase();

        // INSERE NO BANCO DE DADOS
        db.insert("produtos", null, valores);

    }

    public static void editar (Context context, Produto produto){
        ContentValues valores = new ContentValues();
        valores.put ("nome", produto.getNome());
        valores.put ("categoria", produto.getCategoria());
        valores.put ("nomeMercado", produto.getNomeMercado());
        valores.put ("valor", produto.getValor());

        // CRIAR UM OBJETO DO TIPO BANCO
        Banco conn = new Banco (context);
        SQLiteDatabase db = conn.getWritableDatabase();

        // INSERE NO BANCO DE DADOS
        db.update("produtos", valores, "id = "+produto.getId(), null);

    }

    public static void excluir (Context context, int idProduto){

        // CRIAR UM OBJETO DO TIPO BANCO
        Banco conn = new Banco (context);
        SQLiteDatabase db = conn.getWritableDatabase();

        // INSERE NO BANCO DE DADOS
        db.delete("produtos", "id = " + idProduto, null);

    }

    public static List<Produto> getProdutos (Context context){
        List<Produto> lista = new ArrayList<>();

        Banco conn = new Banco (context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome", null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do {
                Produto produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setNome(cursor.getString(1));
                produto.setCategoria(cursor.getString(2));
                produto.setValor(cursor.getFloat(3));
                produto.setNomeMercado(cursor.getString(4));

                lista.add(produto);


            }while (cursor.moveToNext());

            return lista;
        }

        return lista;

    }

    public static Produto getProdutoByID (Context context, int idProduto){


        Banco conn = new Banco (context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos WHERE id = " + idProduto, null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setNome(cursor.getString(1));
            produto.setCategoria(cursor.getString(2));
            produto.setValor(cursor.getFloat(3));
            produto.setNomeMercado(cursor.getString(4));


            return produto;
        }

        return null;

    }
}
