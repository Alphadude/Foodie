package io.github.alphadude.jambhangout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button login,signup;
    private EditText username, password;
    private TextView fogottenPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void click(){
        Intent i = new Intent(this,Register.class);
        startActivity(i);

    }
    public void click1(){
        Intent i = new Intent(this,DashBoard.class);
        startActivity(i);

    }
    public void click2(){
        Intent i = new Intent(this,Profile.class);
        startActivity(i);

    }
}
