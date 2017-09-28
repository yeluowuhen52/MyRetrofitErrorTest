package com.marsjiang.myretrofiterrortest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.marsjiang.myretrofiterrortest.bean.Result;
import com.marsjiang.myretrofiterrortest.bean.UserReturnBean;
import com.marsjiang.myretrofiterrortest.myretrofit.MyGsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private String ipPortString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipPortString = "192.168.10。19:8080";
        String url = "http://" + ipPortString + "/XiaoXiao/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MyGsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        ApiCore userBiz = retrofit.create(ApiCore.class);
        Call<Result<UserReturnBean>> call = userBiz.getLoginInfo();
        call.enqueue(new Callback<Result<UserReturnBean>>() {
            @Override
            public void onResponse(Call<Result<UserReturnBean>> call, Response<Result<UserReturnBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() == null) {

                    }
                    Log.e("infoooo", "normalGet:" + response.body() + "");
                }
            }

            @Override
            public void onFailure(Call<Result<UserReturnBean>> call, Throwable t) {
                Log.e("infoooo", "normalGet:" + t.toString() + "");
            }
        });

    }

    /**
     * 获取okhttp拦截器
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
