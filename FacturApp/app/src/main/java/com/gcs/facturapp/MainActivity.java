package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.gcs.facturapp.models.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setClickListenerBotonClientes();
        setClickListenerBotonFacturas();
        setCLickListenerBotonPlantillas();

    }

    //TODO Arreglar esto
    public void setClickListenerImagenPerfil(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_perfil:

                Intent intent = new Intent(view.getContext(), PerfilActivity.class);
                Usuario usuario = new Usuario("mario@viajesta.com", "Hola", "45628552J", "Viajesta S.A.", "Mario", "Navarro Ruiz");
                Bundle b = new Bundle();
                b.putSerializable("usuario", usuario);
                intent.putExtras(b);
                startActivity(intent);
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
