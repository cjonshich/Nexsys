package com.cbolije.nexsys.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cbolije.nexsys.R;
import com.cbolije.nexsys.model.api.GraphAPI;
import com.cbolije.nexsys.viewmodel.LoginViewModel;
import com.microsoft.graph.authentication.IAuthenticationAdapter;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.DefaultClientConfig;
import com.microsoft.graph.core.IClientConfig;
import com.microsoft.graph.extensions.GraphServiceClient;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.extensions.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
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

        mMicrosoftAuthAdapter = GraphAPI.getInstance(getApplication());
        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mViewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });
    }

    @OnClick(R.id.btnOffice)
    void login() {
        mViewModel.changeLoadingState(true);
        tvLoadingText.setText(R.string.login_connect_with_365_text);
        mMicrosoftAuthAdapter.login(this, new ICallback<Void>() {
            @Override
            public void success(Void aVoid) {
                tvLoadingText.setText(R.string.login_syncing_info);
                show("Exito");
                hideProgressBar();
            }

            @Override
            public void failure(ClientException ex) {
                show("Fallo");
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

    void show(String msg){
        IClientConfig mClientConfig = DefaultClientConfig
                .createWithAuthenticationProvider( GraphAPI.getInstance(getApplication()));
        IGraphServiceClient mClient = new GraphServiceClient
                .Builder()
                .fromConfig(mClientConfig)
                .buildClient();


        mClient.getMe().buildRequest().get(new ICallback<User>() {
            @Override
            public void success(User user) {
                final String msg = "Found USER " + user.id;
                Log.d("GRAPH_USER", msg);
            }

            @Override
            public void failure(ClientException ex) {

            }
        });

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

}

