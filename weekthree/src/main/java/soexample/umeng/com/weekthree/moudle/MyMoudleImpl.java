package soexample.umeng.com.weekthree.moudle;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.weekthree.bean.MyData;
import soexample.umeng.com.weekthree.callback.MyCall;
import soexample.umeng.com.weekthree.okhttp.OkHttp;

public class MyMoudleImpl implements MyMoudle {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String jsons = (String) msg.obj;
            Gson gson = new Gson();
            //JsonReader jsonReader = new JsonReader(new StringReader(jsons));
            //jsonReader.setLenient(true);
            MyData myData = gson.fromJson(jsons, MyData.class);
            myCall.setData(myData);

        }
    };
    private MyCall myCall;

    @Override
    public void startLogin(String url, final MyCall myCall) {
        this.myCall = myCall;
        OkHttp.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.sendMessage(handler.obtainMessage(0, response.body().string()));
            }
        });

    }
}
