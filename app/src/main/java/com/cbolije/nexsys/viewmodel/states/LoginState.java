package com.cbolije.nexsys.viewmodel.states;

public class LoginState {

    public enum State {
        LOADING, SAVING_USER, SAVING_SUCCESs, SAVING_FAIL;
    }

    private State state;
    private String message;

    public LoginState() { }

    public LoginState(State state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
