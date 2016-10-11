package com.clannad.api;


import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.clannad.common.baseapp.BaseApplication;
import com.clannad.common.commonutils.NetWorkUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * des:retorfit api
 */
public class Api {
    //超时时间 毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长
    public static final int CONNECT_TIME_OUT = 7676;
    public Retrofit retrofit;
    public ApiService apiService;

    /**
     * SparseArray是android里为<Interger,Object> 这样的Hashmap而专门写的类,目的是提高效率，其核心是折半查找函数（binarySearch）。在Android中，当我们需要定义
     * HashMap <Integer, E> hashMap = new HashMap <Integer, E> ();
     * 时，我们可以使用如下的方式来取得更好的性能.
     * SparseArray <E> sparseArray = new SparseArray <E> ();
     */
    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    /***********缓存设置***********/
    /*
          1.noCache         不使用缓存,全部走网络
          2.noStore         不使用缓存,也不存储缓存
          3.onlyIfCached    只使用缓存
          4.maxAge          设置最大失效时间,失效则不使用,需要服务器配合
          5.maxStale        设置最大*************
          6.minFresh        设置有效时间,同上
          7.FORCE_NETWORK   只走网络
          8.FORCE_CACHE     只走缓存
     */

    /**
     * 设置缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置,为only-if-cache时只查缓存而不会请求服务器,
     * max-stale可以配合设置缓存失效时间
     * max-stale指示客户机可以接收超出超时期间的响应消息,
     * 如果指定max-stale消息的值,那么客户机可接收超出超时期指定值之内的响应消息
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached,max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache_Control设置,头部Cache-Control设置max-age=0
     * (假如请求了服务器并在a时刻返回响应结果,则在max-age规定的秒数内,浏览器将不会发送对应的请求到服务器,
     * 数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    /*
    Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
        .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
        .serializeNulls()
        .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
        .setPrettyPrinting() //对json结果格式化.
        .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
                            //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
                            //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
        .create();
     */

    private Api(int hostType) {
        //开启log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiConstants.getHost(hostType))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getDefault(int hostType) {
        Api retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new Api(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.apiService;
    }

    /**
     * 根据网络状况获取缓存的策略
     * 网络好的时候请求网络
     * 网络不好的时候本地缓存提取
     */
    @NonNull
    public static String getCacheControl() {
        return NetWorkUtils.isNetConnected(BaseApplication.getAppContext()) ?
                CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

}