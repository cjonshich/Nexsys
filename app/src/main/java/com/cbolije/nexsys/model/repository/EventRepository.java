package com.cbolije.nexsys.model.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cbolije.nexsys.commons.Constants;
import com.cbolije.nexsys.model.api.EventService;
import com.cbolije.nexsys.model.api.NexsysAPI;
import com.cbolije.nexsys.model.bean.Event;
import com.cbolije.nexsys.model.bean.User;
import com.cbolije.nexsys.model.dto.GraphData;
import com.cbolije.nexsys.model.dto.RestResponse;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private MutableLiveData<List<Event>> mEvents = new MutableLiveData<>();
    private NexsysAPI mApi;

    public EventRepository() {
        this.mApi = NexsysAPI.getInstance();
    }


    public void processEvents(List<com.microsoft.graph.extensions.Event> events) {
        Log.i(Constants.LOG_TAG, "Sending data ->" + new Gson().toJson(events));
        mApi.getEventService().processData(events).enqueue(new Callback<RestResponse<Integer>>() {
            @Override
            public void onResponse(Call<RestResponse<Integer>> call, Response<RestResponse<Integer>> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<RestResponse<Integer>> call, Throwable t) {

            }
        });
    }

}
