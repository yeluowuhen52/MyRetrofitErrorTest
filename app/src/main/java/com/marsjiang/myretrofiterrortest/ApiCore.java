package com.marsjiang.myretrofiterrortest;

import com.marsjiang.myretrofiterrortest.bean.Result;
import com.marsjiang.myretrofiterrortest.bean.UserReturnBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jiang on 2017-09-28.
 */

public interface ApiCore {
    //用户信息
    @GET("Server/User.txt")
    Call<Result<UserReturnBean>> getLoginInfo();
}

