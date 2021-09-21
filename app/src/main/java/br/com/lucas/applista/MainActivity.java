package br.com.lucas.applista;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;

    // POSSÍVEL PERGUNTA DA PROVA <===========================================
    // CRIAR LISTA NA MÃO SEM PUXAR OS DADOS DO BANCO DE DADOS
    private List<Produto> listaDeProdutos;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvProdutos = findViewById(R.id.lvProdutos);
        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idProduto", listaDeProdutos.get(i).getId());
                startActivity(intent);
            }
        });

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( i );
                return true;
            }
        });

        carregarLista();

    }

    private void excluir (int posicao){
        Produto produtoSelecionado = listaDeProdutos.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Atenção");
        alerta.setIcon( android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma a exclusão do produto " + produtoSelecionado.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutoDAO.excluir(MainActivity.this, produtoSelecionado.getId());
                carregarLista();
            }
        });
        alerta.show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        carregarLista();
    }

    // POSSÍVEL PERGUNTA DA PROVA <===========================================
    // CRIAR LISTA NA MÃO SEM PUXAR OS DADOS DO BANCO DE DADOS
    private void carregarLista(){
       listaDeProdutos = ProdutoDAO.getProdutos(this);

       if (listaDeProdutos.size() == 0 ){
           Produto fake = new Produto("Lista vazia", "", "", 0);
           listaDeProdutos.add(fake);
           lvProdutos.setEnabled(false);

       }
       else {
           lvProdutos.setEnabled(true);
       }




        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeProdutos);
        lvProdutos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}