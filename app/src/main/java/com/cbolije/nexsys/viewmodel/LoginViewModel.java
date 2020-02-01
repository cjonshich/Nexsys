package com.cbolije.nexsys.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cbolije.nexsys.model.repository.EventRepository;
import com.cbolije.nexsys.model.repository.UserRepository;
import com.cbolije.nexsys.viewmodel.states.LoginState;
import com.google.gson.Gson;
import com.microsoft.graph.extensions.Event;
import com.microsoft.graph.extensions.User;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private MutableLiveData<LoginState> state = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
        eventRepository = new EventRepository();
    }

    public LiveData<LoginState> getState() {
        return this.state;
    }

    public void processUserData(User user) {
        boolean userSaved = userRepository.saveUser(user);
        if (userSaved) {
            state.postValue(new LoginState(LoginState.State.SAVING_SUCCESs, "Datos del usuario grabados con éxito"));
        } else {
            state.postValue(new LoginState(LoginState.State.SAVING_FAIL, "Ocurrió un error al intentar grabar los datos del usuario"));
        }
    }

    public void processEventsData(List<Event> events) {
        this.eventRepository.processEvents(events);
    }


}
