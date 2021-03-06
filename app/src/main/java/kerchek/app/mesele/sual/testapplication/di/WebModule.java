package kerchek.app.mesele.sual.testapplication.di;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kerchek.app.mesele.sual.testapplication.BuildConfig;
import kerchek.app.mesele.sual.testapplication.data.network.WebClient;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class WebModule {

    public WebModule() {
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        Interceptor tokenInterceptor = chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        };

        return new OkHttpClient.Builder()
                .cache(cache)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .addNetworkInterceptor(tokenInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    WebClient provideWebClient(Retrofit retrofit) {
        return new WebClient(retrofit);
    }
}
