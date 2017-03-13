package com.gcs.facturapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText email;
    private EditText contrasenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        contrasenya = (EditText) findViewById(R.id.password);

        Button entrar = (Button) findViewById(R.id.email_sign_in_button);
        entrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.email_sign_in_button:

                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        final TextView link_registro = (TextView) findViewById(R.id.link_registro);

        SpannableString string = new SpannableString(getResources().getString(R.string.registrate));
        string.setSpan(new UnderlineSpan(), 0, string.length(), 0);
        link_registro.setText(string);
        link_registro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.link_registro:

                        Intent intent = new Intent(view.getContext(), RegistroActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}

