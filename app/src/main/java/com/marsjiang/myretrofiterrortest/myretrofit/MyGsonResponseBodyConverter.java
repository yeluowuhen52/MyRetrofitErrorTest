package com.marsjiang.myretrofiterrortest.myretrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.marsjiang.myretrofiterrortest.bean.Result;
import com.marsjiang.myretrofiterrortest.expection.MyException;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Jiang on 2017-09-28.
 */

final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    private static final int FAILURE = 0;       // 失败 提示失败msg
    private static final int SUCCESS = 1;       // 成功
    private static final int TOKEN_EXPIRE = 2;  // token过期
    private static final int SERVER_EXCEPTION = 3;  // 服务器异常

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        String json = value.string();
        try {
            verify(json);
//            return adapter.read(jsonReader);
            return adapter.read(gson.newJsonReader(new StringReader(json)));
        } finally {
            value.close();
        }
    }

    private void verify(String json) {
        Result result = gson.fromJson(json, Result.class);
        if (result.state != SUCCESS) {
            int a = result.state;
            switch (result.state) {
                case SERVER_EXCEPTION:
                    throw new MyException(result.msg);
                case TOKEN_EXPIRE:
                    throw new MyException(result.msg);
                default:
//                    throw new MyException("不清楚什么原因！");
            }
        }
    }

}
