package com.gcs.facturapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gcs.facturapp.adapters.FacturaAdapter;
import com.gcs.facturapp.models.Cliente;
import com.gcs.facturapp.models.Concepto;
import com.gcs.facturapp.models.Factura;
import com.gcs.facturapp.models.TempDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FacturasActivity extends AppCompatActivity {

    private TempDB tempdb;
    private ListView lista_facturas;
    private ArrayList<Factura> facturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);
        lista_facturas = (ListView) findViewById(R.id.lista_facturas);

        tempdb = (TempDB) getIntent().getExtras().getSerializable("tempdb");
        facturas = tempdb.facturas;

        FacturaAdapter adapter = new FacturaAdapter(this, facturas);
        lista_facturas.setAdapter(adapter);
        lista_facturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalle = new Intent(view.getContext(), DetalleFacturaActivity.class);
                detalle.putExtra("posicion_seleccionada", position);
                detalle.putExtra("tempdb", tempdb);
                startActivity(detalle);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crear_factura = new Intent(view.getContext(), CrearFacturaActivity.class);
                crear_factura.putExtra("tempdb", tempdb);
                startActivity(crear_factura);
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
