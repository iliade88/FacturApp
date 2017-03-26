package com.gcs.facturapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gcs.facturapp.models.Usuario;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText email;
    private EditText contrasenya;

    // Parámetros
    private Usuario usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try{
            usu = (Usuario)getIntent().getExtras().getSerializable("parametro");
            Log.d("LOGIN:", "Recibido usu "+ usu.toString());
        }
        catch (NullPointerException ex)
        {
            usu = null;
        }

        email = (EditText) findViewById(R.id.email);
        contrasenya = (EditText) findViewById(R.id.password);

        Button entrar = (Button) findViewById(R.id.email_sign_in_button);
        entrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.email_sign_in_button:

                        Log.d("LOGIN:", "Pulsado boton enviar");
                        if (usu != null)
                        {
                            Log.d("LOGIN:", "usu NO NULL");
                            Log.d("LOGIN:", "EMAIL_FORM-"+email + " EMAIL_USU-"+usu.email+ " PSSW_FORM-"+contrasenya+" PSSW_USU-"+usu.contrasenya);
                            if (email.getText().toString().equals(usu.email) && contrasenya.getText().toString().equals(usu.contrasenya))
                            {
                                Log.d("LOGIN:", "INTENT");
                                Intent intent = new Intent(view.getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(view.getContext(), "Login incorrecto", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(view.getContext(), "Login incorrecto", Toast.LENGTH_SHORT).show();

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

