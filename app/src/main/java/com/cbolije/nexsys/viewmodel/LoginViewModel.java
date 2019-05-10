package com.cbolije.nexsys.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public void changeLoadingState(boolean state) {
        this.isLoading.postValue(state);
    }

}
