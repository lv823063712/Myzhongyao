package soexample.umeng.com.weekthree.okhttp;

import android.view.View;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

    private OkHttpClient okHttpClient;

    private OkHttp() {
        this.okHttpClient = new OkHttpClient();
    }

    public static OkHttp getInstance() {
        return ViewHolder.okHttp;
    }

    static class ViewHolder {
        private static final OkHttp okHttp = new OkHttp();
    }

    public void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
