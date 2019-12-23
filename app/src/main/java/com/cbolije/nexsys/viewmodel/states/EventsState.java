package com.cbolije.nexsys.viewmodel.states;

public class EventsState {

    public enum State {
        LOADING_EVENTS_SUCCESSFUL, LOADING_EVENTS_FAILED, LOADING_DETAIL_SUCCESSFUL, LOADING_DETAIL_FAILED, UPDATING_EVENT_SUCCESSFUL, UPDATING_EVENT_FAILED
    }
    private EventsState.State state;
    private String message;

    public EventsState() { }

    public EventsState(EventsState.State state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventsState.State getState() {
        return state;
    }

    public void setState(EventsState.State state) {
        this.state = state;
    }
}
