package br.com.escolaarcadia.consumindowebservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import br.com.escolaarcadia.consumindowebservice.R;
import br.com.escolaarcadia.consumindowebservice.app.AppControle;
import br.com.escolaarcadia.consumindowebservice.model.Filme;

/**
 * Created by Martin on 28/05/2015.
 */
public class ListAdapterPersonalizado extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Filme> itensFilmes;
    ImageLoader carregadorImagem = AppControle.getInstance().getMeuCarregadorImagem();

    public ListAdapterPersonalizado(Activity activity, List<Filme> itensFilmes) {
        this.activity = activity;
        this.itensFilmes = itensFilmes;
    }

    @Override
    public int getCount() {
        return itensFilmes.size();
    }

    @Override
    public Object getItem(int location) {
        return itensFilmes.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converterView, ViewGroup principal) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (converterView == null)
            converterView = inflater.inflate(R.layout.list_row, null);

        if (carregadorImagem == null)
            carregadorImagem = AppControle.getInstance().getMeuCarregadorImagem();
        NetworkImageView miniatura = (NetworkImageView) converterView
                .findViewById(R.id.miniatura);
        TextView tituloTextView = (TextView) converterView.findViewById(R.id.titulo);
        TextView clasificacaoTextView = (TextView) converterView.findViewById(R.id.classificacao);
        TextView generoTextView = (TextView) converterView.findViewById(R.id.genero);
        TextView anoTextView = (TextView) converterView.findViewById(R.id.ano);

        // Pega os dados de um Filme
        Filme filme = itensFilmes.get(position);

        // Miniatura image
        miniatura.setImageUrl(filme.getMiniaturaUrl(), carregadorImagem);

        // tituloTextView
        tituloTextView.setText(filme.getTitulo());

        // clasificacao
        clasificacaoTextView.setText(activity.getString(R.string.classificacao) + String.valueOf(filme.getClassificacao()));

        // generoTextView
        String generoStr = "";
        for (String str : filme.getGenero()) {
            generoStr += str + ", ";
        }
        generoStr = generoStr.length() > 0 ? generoStr.substring(0,
                generoStr.length() - 2) : generoStr;
        generoTextView.setText(generoStr);

        // anoTextView de lan�amento
        anoTextView.setText(String.valueOf(filme.getAno()));

        return converterView;
    }
}
