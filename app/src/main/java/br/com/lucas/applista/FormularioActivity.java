package br.com.lucas.applista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etNomeMercado, etValor;
    private Spinner spCategorias;
    private Button btnSalvar;
    private String acao;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);



        etNome = findViewById(R.id.etNome);
        etNomeMercado = findViewById(R.id.etNomeMercado);
        etValor = findViewById(R.id.etValor);
        spCategorias = findViewById(R.id.spCategorias);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")){
            carregarFormulario();
        }


    }

   private void carregarFormulario(){
        int idProduto = getIntent().getIntExtra("idProduto", 0);
        produto = ProdutoDAO.getProdutoByID(this, idProduto);

        etNome.setText(produto.getNome());
        etNomeMercado.setText(produto.getNomeMercado());
        String[] categorias = getResources().getStringArray(R.array.categorias);
        int posicao = 0;
        for (int i = 0 ; i < categorias.length ; i++ ){
            if (produto.getCategoria().equals( categorias[i])){
                spCategorias.setSelection(i);
                break;
            }

        }
        etValor.setText(String.valueOf(produto.getValor()));
   }

    private void salvar (){
        String nome = etNome.getText().toString();
        String nomeMercado = etNomeMercado.getText().toString();
        float valor = Float.valueOf(etValor.getText().toString());

        if (nome.isEmpty() || spCategorias.getSelectedItemPosition() == 0 || nomeMercado.isEmpty() || etValor.getText().toString().isEmpty() ) {

            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();

        }
        else {

            if (acao.equals("inserir")) produto = new Produto();

            produto.setNome( nome );
            produto.setCategoria( spCategorias.getSelectedItem().toString());
            produto.setValor( valor );
            produto.setNomeMercado(nomeMercado);

            if (acao.equals("inserir")){
                ProdutoDAO.inserir(this, produto);

                etNome.setText("");
                spCategorias.setSelection(0, true);
                etValor.setText("");
                etNomeMercado.setText("");

            }
            else {
                ProdutoDAO.editar(this,produto);
                finish();
            }


        }
    }
}