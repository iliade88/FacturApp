package com.gcs.facturapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CrearClienteActivity extends AppCompatActivity {

    private EditText nombre_cliente;
    private EditText dnicif_cliente;
    private EditText direccion_cliente;
    private EditText telefono_cliente;
    private EditText email_cliente;
    private Button boton_cancelar_crear_cliente;
    private Button boton_crear_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);

        nombre_cliente = (EditText) findViewById(R.id.nombre_cliente);
        dnicif_cliente = (EditText) findViewById(R.id.dnicif_cliente);
        direccion_cliente = (EditText) findViewById(R.id.direccion_cliente);
        telefono_cliente = (EditText) findViewById(R.id.telefono_cliente);
        email_cliente = (EditText) findViewById(R.id.email_cliente);
        boton_cancelar_crear_cliente = (Button) findViewById(R.id.boton_cancelar_crear_cliente);
        boton_crear_cliente = (Button) findViewById(R.id.boton_crear_cliente);
    }

    public void onClickCancelar(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_cancelar_crear_cliente:
                finish();
                break;
        }
    }

    public void onClickCrearCliente(View view)
    {
        /*TODO - Pasar datos a ClientesActivity para añadirlo a la lista*/
        switch (view.getId())
        {
            case R.id.boton_crear_cliente:
                finish();
                break;
        }
    }
}
