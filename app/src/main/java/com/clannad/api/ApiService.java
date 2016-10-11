package com.clannad.api;


import com.clannad.bean.GirlData;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * des:ApiService
 * Created by xsf
 * on 2016.06.15:47
 */
public interface ApiService {

    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);
}
