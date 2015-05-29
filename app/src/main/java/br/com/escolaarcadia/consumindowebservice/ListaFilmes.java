package br.com.escolaarcadia.consumindowebservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.escolaarcadia.consumindowebservice.adapter.ListAdapterPersonalizado;
import br.com.escolaarcadia.consumindowebservice.app.AppControle;
import br.com.escolaarcadia.consumindowebservice.model.Filme;


public class ListaFilmes extends Activity {
    // Log tag
    private static final String TAG = ListaFilmes.class.getSimpleName();

    // URL JSon dos filmes
    private static final String url = "http://api.androidhive.info/json/movies.json";
    private ProgressDialog dialogo;
    private List<Filme> listaFilme = new ArrayList<>();
    private ListView listView;
    private ListAdapterPersonalizado adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ListAdapterPersonalizado(this, listaFilme);
        listView.setAdapter(adapter);

        dialogo = new ProgressDialog(this);
        dialogo.setMessage(getString(R.string.carregando));
        dialogo.show();

        // Criando volley requisicao de obj
        JsonArrayRequest filmesRequisitado = new JsonArrayRequest(
                //Parâmetro UM
                url,
                //Parâmetro DOIS
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray resposta) {
                        Log.d(TAG, resposta.toString());
                        desfazDialogo();

                        // Percorrendo json
                        for (int i = 0; i < resposta.length(); i++) {
                            try {
                                JSONObject obj = resposta.getJSONObject(i);
                                Filme filme = new Filme();
                                filme.setTitulo(obj.getString("title"));
                                filme.setMiniaturaUrl(obj.getString("image"));
                                filme.setClassificacao(((Number) obj.get("rating"))
                                        .doubleValue());
                                filme.setAno(obj.getInt("releaseYear"));

                                // array de genero do json
                                JSONArray generoArray = obj.getJSONArray("genre");
                                ArrayList<String> generoLista = new ArrayList<String>();
                                for (int j = 0; j < generoArray.length(); j++) {
                                    generoLista.add((String) generoArray.get(j));
                                }
                                filme.setGenero(generoLista);

                                // Adciona filme na lista de filmes
                                listaFilme.add(filme);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifica o listAdapterPersonalizado sobre alteracoes
                        adapter.notifyDataSetChanged();
                    }
                },
                //Parâmetro TRÊS
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, getString(R.string.erro) + error.getMessage());
                        desfazDialogo();

                    }
        });

        // Adiciona filmes requisitados na lista
        AppControle.getInstance().addNaListaRequisicao(filmesRequisitado);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        desfazDialogo();
    }

    private void desfazDialogo() {
        if (dialogo != null) {
            dialogo.dismiss();
            dialogo = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_filmes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
