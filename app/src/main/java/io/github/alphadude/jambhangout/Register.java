package io.github.alphadude.jambhangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by alphadude on 8/20/17.
 */

public class Register extends AppCompatActivity {
    private TextView logscreen;
    private EditText fullname,username,email,password,password1;
    private Button register;

    public static final String BASE_URL = "http://www.circlepanda.com/Jamb/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

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
                click();
            }
        });

    }

    private void insertUser(){
        RestAdapter adapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
                .build();

        RegisterApi api = adapter.create(RegisterApi.class);
/*
        api.insertUser(
                //fullname.getText().toString();
                username.getText().toString();
                email.getText().toString();
                password.getText().toString();
                password1.getText().toString();


        new Callback<Response>(){
            @Override
            public void success(Response result,Response response){

                BufferedReader reader = null;

                String output =" ";

                try{
                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                    output = reader.readLine();
                }catch (IOException e){

                    e.printStackTrace();

                }
                Toast.makeText(Register.this,output,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Register.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        )*/

    }

    public void click(){
        Intent i = new Intent(this,Following.class);
        startActivity(i);

    }
    public void onClick(View v){

        insertUser();

    }
}
