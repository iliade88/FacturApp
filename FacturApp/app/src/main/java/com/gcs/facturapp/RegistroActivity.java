package com.gcs.facturapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void registrar(View view) {
        switch (view.getId())
        {
            case R.id.registrar:

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                break;

        }
    }
}
