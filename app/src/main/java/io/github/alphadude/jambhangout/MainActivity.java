package io.github.alphadude.jambhangout;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.github.alphadude.jambhangout.Models.Login;
import io.github.alphadude.jambhangout.Models.Response;
import io.github.alphadude.jambhangout.Network.NetworkUtils;
import io.github.alphadude.jambhangout.Utils.Constants;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    AlertDialog dialog;
    private CompositeSubscription mSubscriptions;
    private SharedPreferences mSharedPreferences;

    private Button login,signup;
    private EditText username, password;
    private TextView fogottenPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSharedPreferences();
        initViews();

    }

    private void initViews() {

        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        fogottenPass = (TextView) findViewById(R.id.txtforgotten);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1();
            }
        });
        fogottenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click2();
            }
        });
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    }

    public void click(){
        Intent i = new Intent(this,Register.class);
        startActivity(i);

    }
    public void click1(){
     loginUser();

    }

    private void loginUser() {
        String Username =username.getText().toString();
        String Password =password.getText().toString();

        if (TextUtils.isEmpty(Username)||TextUtils.isEmpty(Password)){
            showMessage("Field can not be empty");

        }

        Login login = new Login();
        login.setLoginUsername(Username);
        login.setLoginPassword(Password);
        loginUserAccount(login);

    }

    private void loginUserAccount(Login login) {
        dialog.show();
        mSubscriptions.add(NetworkUtils.getRetrofit().login(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        handleResponse(response);
                    }
                }));
    }

    private void handleResponse(Response response) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.TOKEN, response.getToken());

        editor.apply();

      username.setText(null);
       password.setText(null);

        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);
        dialog.dismiss();
    }

    private void handleError(Throwable e) {
        dialog.dismiss();
        if (e instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) e).response().errorBody().string();
                Response response = gson.fromJson(errorBody, Response.class);
                showMessage(response.getMessage());

            } catch (IOException error) {
                error.printStackTrace();
            }
        } else {

            showMessage("Please check your network connection !");
        }
    }

    public void click2(){
        Intent i = new Intent(this,Profile.class);
        startActivity(i);

    }
    private void showMessage(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
