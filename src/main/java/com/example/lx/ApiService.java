package com.example.lx;

import bean.BannerBean;
import bean.ResultBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String BASE_BANNER="https://www.wanandroid.com/";
    @GET("banner/json")
    Observable<BannerBean>getData();

    String BASE_RESULT="https://www.wanandroid.com/";
    @GET("article/list/{page}/json")
    Observable<ResultBean>getResult(@Path("page")int page);

}
