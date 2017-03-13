package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton_clientes = (Button) findViewById(R.id.boton_clientes);
        boton_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.boton_clientes:

                        Intent intent = new Intent(view.getContext(), ClientesActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

}
