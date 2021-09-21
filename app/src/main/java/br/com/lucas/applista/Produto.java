package br.com.lucas.applista;

public class Produto {

    public int id;
    public String nome, categoria, nomeMercado;
    public float valor;

    public Produto() {
    }

    public Produto(int id, String nome, String categoria, String nomeMercado, float valor) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.nomeMercado = nomeMercado;
        this.valor = valor;
    }

    public Produto(String nome, String categoria, String nomeMercado, float valor) {
        this.nome = nome;
        this.categoria = categoria;
        this.nomeMercado = nomeMercado;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome + "  -  " + valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeMercado() {
        return nomeMercado;
    }

    public void setNomeMercado(String nomeMercado) {
        this.nomeMercado = nomeMercado;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
