package com.ubetween.hadisnzl.interfaces;


import com.ubetween.hadisnzl.model.HttpResult;
import com.ubetween.hadisnzl.model.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author hadis on 16.7.19.
 */
public interface MovieService1 {

    // https://api.douban.com/v2/movie/top250?start=0&count=10

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
