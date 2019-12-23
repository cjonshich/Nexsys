package com.cbolije.nexsys.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.cbolije.nexsys.model.bean.Event;
import com.cbolije.nexsys.model.dto.GraphEvent;

import java.util.List;

public class EventRepository {

    private MutableLiveData<List<Event>> events = new MutableLiveData<>();


    public void processEvents(List<GraphEvent> graphEvents) {

    }

}
