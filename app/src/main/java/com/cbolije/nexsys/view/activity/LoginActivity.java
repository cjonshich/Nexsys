package com.cbolije.nexsys.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cbolije.nexsys.R;
import com.cbolije.nexsys.model.api.GraphAPI;
import com.cbolije.nexsys.commons.NexsysApp;
import com.cbolije.nexsys.viewmodel.LoginViewModel;
import com.cbolije.nexsys.viewmodel.states.LoginState;
import com.google.gson.Gson;
import com.microsoft.graph.authentication.IAuthenticationAdapter;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.DefaultClientConfig;
import com.microsoft.graph.core.IClientConfig;
import com.microsoft.graph.extensions.GraphServiceClient;
import com.microsoft.graph.extensions.IEventCollectionPage;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.extensions.User;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.lnProgress)
    LinearLayout lnProgress;
    @BindView(R.id.tvLoadingText)
    TextView tvLoadingText;

    private IAuthenticationAdapter mMicrosoftAuthAdapter;
    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mMicrosoftAuthAdapter = GraphAPI.getInstance();
        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mViewModel.getState().observe(this, state -> {
            if (state.getState().equals(LoginState.State.SAVING_SUCCESs)) {
                redirectToEventsActivity();
            } else if (state.getState().equals(LoginState.State.SAVING_FAIL)) {
                onFailedSaving(state.getMessage());
            }
        });
    }

    @OnClick(R.id.btnOffice)
    void login() {
        showProgressBar();
        tvLoadingText.setText(R.string.login_connect_with_365_text);

        mMicrosoftAuthAdapter.login(this, new ICallback<Void>() {
            @Override
            public void success(Void aVoid) {
                tvLoadingText.setText(R.string.login_syncing_info);
                requestGraphAPI();
            }
            @Override
            public void failure(ClientException ex) {
                Toast.makeText(NexsysApp.getContext(), "Ocurrio un error la conexión con Office 365", Toast.LENGTH_LONG).show();
                hideProgressBar();
            }
        });
    }

    private void requestGraphAPI() {
        IClientConfig mClientConfig = DefaultClientConfig
                .createWithAuthenticationProvider( GraphAPI.getInstance());
        IGraphServiceClient client = new GraphServiceClient.Builder().fromConfig(mClientConfig).buildClient();

        List<Option> queryOptions = new LinkedList<>();
        queryOptions.add(new QueryOption("select", "subject,attendees,iCalUId,isCancelled,createdDateTime,start,end,organizer"));
        //queryOptions.add(new QueryOption("filter", "isCancelled eq false"));
        //Getting user data
        client.getMe()
                .getEvents()
                .buildRequest(queryOptions)
                .get(new ICallback<IEventCollectionPage>() {
            @Override
            public void success(IEventCollectionPage iEventCollectionPage) {
                Gson gson = new Gson();
                Log.i("GRAPH", gson.toJson(iEventCollectionPage.getCurrentPage()));
                mViewModel.processEventsData(iEventCollectionPage.getCurrentPage());
            }

            @Override
            public void failure(ClientException ex) {
                Log.e("GRAPH", "error");
            }
        });
        client.getMe().buildRequest().get(new ICallback<User>() {
            @Override
            public void success(User user) {
                mViewModel.processUserData(user);
                //Log.d("DATA_USUARIO", user.displayName + " - " + user.mail + " - " + user.photo);
            }

            @Override
            public void failure(ClientException ex) {
                Toast.makeText(NexsysApp.getContext(), "Ocurrio un error la conexión con Office 365", Toast.LENGTH_LONG).show();
                hideProgressBar();
            }
        });
    }

    void showProgressBar() {
        lnProgress.setVisibility(View.VISIBLE);
    }

    void hideProgressBar() {
        lnProgress.setVisibility(View.GONE);
        tvLoadingText.setText(null);
    }

    void onFailedSaving(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    void redirectToEventsActivity() {
        hideProgressBar();

        Intent intent = new Intent(this, EventsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}

