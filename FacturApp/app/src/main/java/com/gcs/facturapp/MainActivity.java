package com.gcs.facturapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.gcs.facturapp.models.Cliente;
import com.gcs.facturapp.models.Factura;
import com.gcs.facturapp.models.TempDB;
import com.gcs.facturapp.models.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TempDB tempdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempdb = (TempDB) getIntent().getExtras().getSerializable("tempdb");

        setClickListenerBotonClientes();
        setClickListenerBotonFacturas();
        setCLickListenerBotonPlantillas();

        if(tempdb.usuario.foto != null){
            ImageButton ib = (ImageButton)findViewById(R.id.boton_perfil);
            ib.setImageURI(Uri.parse(tempdb.usuario.foto));
        }

    }

    public void setClickListenerImagenPerfil(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_perfil:

                Intent intent = new Intent(view.getContext(), PerfilActivity.class);
                intent.putExtra("tempdb", tempdb);
                startActivity(intent);
                finish();
                break;
        }
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
                        intent.putExtra("tempdb", tempdb);
                        startActivity(intent);
                        finish();
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
                        intent.putExtra("tempdb", tempdb);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }

    protected void setCLickListenerBotonPlantillas()
    {
        Button boton_plantillas = (Button) findViewById(R.id.boton_plantillas);
        boton_plantillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlantillasActivity.class);
                startActivity(intent);
        }
            }
        );
    }

}
