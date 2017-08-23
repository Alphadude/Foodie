package io.github.alphadude.jambhangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by alphadude on 8/20/17.
 */

public class ForgottenPassword extends AppCompatActivity {
    //private EditText newpass;
    private Button reset;
    private TextView txtlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initialize();


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });


        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1();
            }
        });

    }

    public void click() {
        Intent i = new Intent(this,Register.class);
        startActivity(i);

    }
    public void click1() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public void initialize() {
        reset = (Button) findViewById(R.id.btnreset);
        txtlogin = (TextView) findViewById(R.id.login);

    }

    }
