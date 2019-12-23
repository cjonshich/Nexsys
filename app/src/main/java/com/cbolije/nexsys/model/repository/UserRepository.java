package com.cbolije.nexsys.model.repository;

import com.cbolije.nexsys.model.dto.GraphUser;
import com.cbolije.nexsys.model.local.PreferenceManager;
import com.cbolije.nexsys.model.local.PreferenceManager.Key;
import com.google.gson.Gson;
import com.microsoft.graph.extensions.User;

public class UserRepository {

    private PreferenceManager preferenceManager;

    public UserRepository() {
        preferenceManager = PreferenceManager.getInstance();
    }

    public boolean saveUser(User graphUser) {
        Gson gson = new Gson();

        GraphUser user = new GraphUser(graphUser);
        String userJson = gson.toJson(user);

        preferenceManager.put(Key.IS_LOGGED, true);
        return preferenceManager.putAndWait(Key.USER_DATA, userJson);
    }


}
