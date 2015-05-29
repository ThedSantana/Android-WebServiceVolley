package br.com.escolaarcadia.consumindowebservice.app;

        import android.app.Application;
        import android.text.TextUtils;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.Volley;

        import br.com.escolaarcadia.consumindowebservice.util.BitmapCache;

/**
 * Created by Martin on 28/05/2015.
 * Classe singleton
 *
 */
public class AppControle extends Application {

    public static final String NomeClasse = AppControle.class.getSimpleName();
    private static AppControle minhaInstancia;

    private RequestQueue minhaListaRequisicao;
    private ImageLoader meuCarregadorImagem;

    @Override
    public void onCreate() {
        super.onCreate();
        minhaInstancia = this;
    }

    public static synchronized AppControle getInstance() {
        return minhaInstancia;
    }

    public RequestQueue getMinhaListaRequisicao() {
        if (minhaListaRequisicao == null) {
            minhaListaRequisicao = Volley.newRequestQueue(getApplicationContext());
        }
        return minhaListaRequisicao;
    }

    public ImageLoader getMeuCarregadorImagem() {
        getMinhaListaRequisicao();
        if (meuCarregadorImagem == null) {
            meuCarregadorImagem = new ImageLoader(this.minhaListaRequisicao,
                    new BitmapCache());
        }
        return this.meuCarregadorImagem;
    }

    public <T> void addNaListaRequisicao(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? NomeClasse : tag);
        getMinhaListaRequisicao().add(req);
    }

    public <T> void addNaListaRequisicao(Request<T> req) {
        req.setTag(NomeClasse);
        getMinhaListaRequisicao().add(req);
    }

    public void cancelaRequisicaoPendente(Object tag) {
        if (minhaListaRequisicao != null) {
            minhaListaRequisicao.cancelAll(tag);
        }
    }

}
