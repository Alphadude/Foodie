package io.github.alphadude.jambhangout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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

import dmax.dialog.SpotsDialog;
import io.github.alphadude.jambhangout.Models.Response;
import io.github.alphadude.jambhangout.Models.User;
import io.github.alphadude.jambhangout.Network.NetworkUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by alphadude on 8/20/17.
 */

public class Register extends AppCompatActivity {
    private TextView logscreen;
    private EditText fullname,username,email,password,password1;
    private Button register;

    AlertDialog dialog;
    // initialise a CompositeSubscription object and add all the RxJava subscriptions to it.
    private CompositeSubscription mSubscriptions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mSubscriptions = new CompositeSubscription();
        dialog = new SpotsDialog(Register.this, R.style.Custom);
        initViews();

    }

    private void initViews() {
        fullname= (EditText)findViewById(R.id.edfullname);
        username= (EditText)findViewById(R.id.edusername);
        email= (EditText)findViewById(R.id.edemail);
        password= (EditText)findViewById(R.id.edpass);
        password1= (EditText)findViewById(R.id.edpassword1);

        register = (Button)findViewById(R.id.btnregister);

        logscreen = (TextView)findViewById(R.id.logscreen);
        logscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });

        register = (Button) findViewById(R.id.btnregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void insertUser(){
        //fullname.getText().toString();
        String Username =username.getText().toString();
        String Email =email.getText().toString();
        String Password =password.getText().toString();
        String PasswordConfirm = password1.getText().toString();

        if (!Password.equalsIgnoreCase(PasswordConfirm)){
            showMessage("Password does not match");
            return ;
        }

        if (TextUtils.isEmpty(Username)||TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)||TextUtils.isEmpty(PasswordConfirm)){
            showMessage("Fields cannot be empty");
            return;
        }

        User user = new User();
        user.setUserName(Username);
        user.setEmail(Email);
        user.setPassword(Password);
        registerUser(user);


    }

    private void registerUser(User user) {
        dialog.show();
        mSubscriptions.add(NetworkUtils.getRetrofit().register(user)
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
        username.setText("");
        email.setText("");
        password.setText("");
        password1.setText("");

        Intent profile = new Intent(Register.this,Profile.class);
        startActivity(profile);
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

            // if it is not a server error show a toast that it is a network error
            showMessage("Please check your internet connection  !");
        }
    }

    private void showMessage(String s) {

        Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
    }

    public void click(){
        insertUser();

    }
    public void onClick(View v){



    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }
}
