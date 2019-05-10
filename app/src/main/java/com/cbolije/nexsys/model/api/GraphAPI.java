package com.cbolije.nexsys.model.api;

import android.app.Application;
import com.cbolije.nexsys.R;
import com.microsoft.graph.authentication.IAuthenticationAdapter;
import com.microsoft.graph.authentication.MSAAuthAndroidAdapter;


public class GraphAPI {

    private static IAuthenticationAdapter authenticationAdapter;

    public static IAuthenticationAdapter getInstance(final Application app) {
        if (authenticationAdapter == null) {

            authenticationAdapter = new MSAAuthAndroidAdapter(app) {
                @Override
                public String getClientId() {
                    return app.getApplicationContext().getString(R.string.graph_id);
                }

                @Override
                public String[] getScopes() {
                    return new String[] {
                            // An example set of scopes your application could use
                            "https://graph.microsoft.com/Calendars.Read",
                            "https://graph.microsoft.com/User.Read",
                            "openid"
                    };
                }
            };
        }

        return authenticationAdapter;
    }
}
