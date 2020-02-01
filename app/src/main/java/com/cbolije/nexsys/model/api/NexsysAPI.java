package com.cbolije.nexsys.model.api;

import com.cbolije.nexsys.commons.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NexsysAPI {

    private static NexsysAPI instance;

    private EventService eventService;

    private NexsysAPI() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        // Creating retrofit instance
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        // Creating services
        eventService = retrofit.create(EventService.class);
    }

    public static NexsysAPI getInstance() {
        if (instance == null) {
            instance = new NexsysAPI();
        }
        return instance;
    }

    public EventService getEventService() {
        return this.eventService;
    }

}
