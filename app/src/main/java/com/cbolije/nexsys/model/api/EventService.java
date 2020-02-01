package com.cbolije.nexsys.model.api;

import com.cbolije.nexsys.model.dto.RestResponse;
import com.microsoft.graph.extensions.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventService {

    @POST("events/process")
    Call<RestResponse<Integer>> processData(@Body List<Event> data);

}
