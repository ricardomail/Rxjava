package com.test.nice.rxjavawithretrofit;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xingge on 2017/5/16.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("f/signIn/userLogin")
    Observable<HttpResult<User>> getLogin(@Field("userName") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("getActivityProductInfoList")
    Observable<HttpResult<List<SearchBean.DataBean>>> getSearch(@Field("id") String id, @Field("agUserId") String agUserId);

}
