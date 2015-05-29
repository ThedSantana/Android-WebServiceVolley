package br.com.escolaarcadia.consumindowebservice.model;

import java.util.ArrayList;

/**
 * Created by Martin on 28/05/2015.
 */
public class Filme {
    private String titulo;
    private String miniaturaUrl;
    private int ano;
    private double classificacao;
    private ArrayList<String> genero;

    public Filme(){

    }

    public Filme(String titulo, String miniaturaUrl, int ano, double classificacao, ArrayList<String> genero) {
        this.titulo = titulo;
        this.miniaturaUrl = miniaturaUrl;
        this.ano = ano;
        this.classificacao = classificacao;
        this.genero = genero;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMiniaturaUrl() {
        return miniaturaUrl;
    }

    public void setMiniaturaUrl(String miniaturaUrl) {
        this.miniaturaUrl = miniaturaUrl;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public ArrayList<String> getGenero() {
        return genero;
    }

    public void setGenero(ArrayList<String> genero) {
        this.genero = genero;
    }
}
