package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gcs.facturapp.adapters.ClienteAdapter;
import com.gcs.facturapp.models.Cliente;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClientesActivity extends AppCompatActivity {

    private ListView lista_clientes;
    private ArrayList<Cliente> clientes;
    ClienteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        lista_clientes = (ListView) findViewById(R.id.lista_clientes);

        Cliente clien;

        try{
            clientes = (ArrayList<Cliente>) getIntent().getExtras().getSerializable("listaclientes");
        } catch(Exception e) {
            clientes = new ArrayList<Cliente>();
        }

        adapter = new ClienteAdapter(this, clientes);
        lista_clientes.setAdapter(adapter);
        lista_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente_seleccionado = clientes.get(position);

                Intent detalle = new Intent(view.getContext(), DetalleClienteActivity.class);
                detalle.putExtra("listaclientes", clientes);
                detalle.putExtra("clienteseleccionado", cliente_seleccionado);
                startActivity(detalle);
                finish();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CrearClienteActivity.class);
                intent.putExtra("listaclientes", clientes);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
