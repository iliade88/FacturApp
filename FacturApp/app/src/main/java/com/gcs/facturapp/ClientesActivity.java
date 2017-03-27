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
import com.gcs.facturapp.models.TempDB;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClientesActivity extends AppCompatActivity {

    private TempDB tempdb;
    private ListView lista_clientes;
    private ArrayList<Cliente> clientes;
    ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        lista_clientes = (ListView) findViewById(R.id.lista_clientes);

        tempdb = (TempDB) getIntent().getExtras().getSerializable("tempdb");
        clientes = tempdb.clientes;

        adapter = new ClienteAdapter(this, clientes);
        lista_clientes.setAdapter(adapter);
        lista_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalle = new Intent(view.getContext(), DetalleClienteActivity.class);
                detalle.putExtra("tempdb", tempdb);
                detalle.putExtra("posicion", position);
                startActivity(detalle);
                finish();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CrearClienteActivity.class);
                intent.putExtra("tempdb", tempdb);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("tempdb", tempdb);
        startActivity(intent);
        finish();
    }
}
