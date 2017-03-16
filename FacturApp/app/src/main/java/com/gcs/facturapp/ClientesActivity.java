package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gcs.facturapp.adapters.ClienteAdapter;
import com.gcs.facturapp.models.Cliente;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    private ListView lista_clientes;
    private ArrayList<Cliente> clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        lista_clientes = (ListView) findViewById(R.id.lista_clientes);

        clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("Mario", "Navarro ruiz", "59343928J", "Calle de elda", "65676543", "mario@viajesta.com"));
        clientes.add(new Cliente("Alberto", "Sapiña Mora", "59343928J", "Calle de elda", "965692617", "mario@viajesta.com"));
        clientes.add(new Cliente("Cristobal", "Jesus Gonzalez", "59343928J", "Calle de elda", "96543421", "mario@viajesta.com"));
        clientes.add(new Cliente("Manuel", "Volteador Garcia", "59343928J", "Calle de elda", "67654356", "mario@viajesta.com"));

        ClienteAdapter adapter = new ClienteAdapter(this, clientes);
        lista_clientes.setAdapter(adapter);
        lista_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente_seleccionado = clientes.get(position);

                Intent detalle = new Intent(view.getContext(), DetalleClienteActivity.class);
                startActivity(detalle);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CrearClienteActivity.class);
                startActivity(intent);
            }
        });
    }

}
