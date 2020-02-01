package com.cbolije.nexsys.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cbolije.nexsys.model.bean.Event;
import com.cbolije.nexsys.model.dto.GraphData;
import com.cbolije.nexsys.model.repository.EventRepository;
import com.cbolije.nexsys.viewmodel.states.EventsState;

import java.util.List;

public class EventsViewModel extends AndroidViewModel {

    private EventRepository repository;
    private MutableLiveData<EventsState> state = new MutableLiveData<>();
    private MutableLiveData<List<Event>> events = new MutableLiveData<>();
    private MutableLiveData<Event> event;

    public EventsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<EventsState> getState() {
        return this.state;
    }

    public void processEvents(List<com.microsoft.graph.extensions.Event> graphEvents) {
        this.repository.processEvents(graphEvents);
    }
}
