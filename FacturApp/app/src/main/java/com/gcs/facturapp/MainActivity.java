package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setClickListenerBotonClientes();
        setClickListenerBotonFacturas();

    }

    protected void setClickListenerBotonClientes()
    {
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

    protected void setClickListenerBotonFacturas()
    {
        Button boton_clientes = (Button) findViewById(R.id.boton_facturas);
        boton_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.boton_facturas:

                        Intent intent = new Intent(view.getContext(), FacturasActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

}
