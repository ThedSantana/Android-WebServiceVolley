package br.com.escolaarcadia.consumindowebservice.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;


/**
 * Created by Martin on 28/05/2015.
 */
public class BitmapCache extends LruCache<String, Bitmap> implements ImageCache {

    public static int getDefaultCacheSize() {
        final int maxMemoria = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheTamanho = maxMemoria / 8;

        return cacheTamanho;
    }

    public BitmapCache() {
        this(getDefaultCacheSize());
    }

    public BitmapCache(int TamanhoKB) {
        super(TamanhoKB);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}