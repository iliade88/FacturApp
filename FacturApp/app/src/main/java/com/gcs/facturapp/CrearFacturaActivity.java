package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gcs.facturapp.adapters.ConceptoAdapter;
import com.gcs.facturapp.models.Cliente;
import com.gcs.facturapp.models.Concepto;
import com.gcs.facturapp.models.Factura;
import com.gcs.facturapp.models.TempDB;

import java.util.ArrayList;
import java.util.Calendar;

public class CrearFacturaActivity extends AppCompatActivity {

    private TempDB tempdb;
    private Factura factura;
    private Spinner spinner_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_factura);

        tempdb = (TempDB) getIntent().getExtras().getSerializable("tempdb");
        //Inicializacion de datos stub necesarios
        ArrayList<Cliente> clientes = tempdb.clientes;
        ArrayList<Factura> facturas = tempdb.facturas;
        factura = new Factura();

        //Creamos el select de cliente
        spinner_listview = (Spinner) findViewById(R.id.seleccion_cliente);
        ArrayAdapter<Cliente> spinnerArrayAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, clientes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_listview.setAdapter(spinnerArrayAdapter);

        //Creamos el listview de conceptos
        ListView listview_conceptos = (ListView) findViewById(R.id.listview_conceptos_crear_factura);
        View header = getLayoutInflater().inflate(R.layout.header_listview_conceptos, null);
        listview_conceptos.addHeaderView(header);
        ConceptoAdapter adapter = new ConceptoAdapter(this, factura.validada, factura.conceptos);
        listview_conceptos.setAdapter(adapter);

        //El boton para añadir conceptos
        Button btn_anyadir_concepto = (Button) findViewById(R.id.btn_anyadir_concepto);
        btn_anyadir_concepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CrearFacturaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_anyadir_concepto, null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                Button btn = (Button) mView.findViewById(R.id.anyadir_concepto_btn);

                final EditText descripcion_nuevo_concepto = (EditText) mView.findViewById(R.id.descripcion_concepto);
                final EditText precio_nuevo_concepto = (EditText) mView.findViewById(R.id.precio_concepto);
                final EditText cantidad_nuevo_concepto = (EditText) mView.findViewById(R.id.cantidad_concepto);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try
                        {
                            String descripcion_concepto = descripcion_nuevo_concepto.getText().toString();
                            float precio_concepto = Float.parseFloat(precio_nuevo_concepto.getText().toString());
                            int cantidad = Integer.parseInt(cantidad_nuevo_concepto.getText().toString());

                            Concepto nuevo_concepto = new Concepto(factura.id, factura.getSiguienteIdConcepto(), descripcion_concepto, precio_concepto, cantidad);
                            factura.anyadirConcepto(nuevo_concepto);
                            dialog.dismiss();
                        }
                        catch (NumberFormatException ne)
                        {
                            Snackbar.make(v, "El precio y la cantidad deben ser números",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
    }



    public void onClickCrearFactura(View view)
    {
        switch (view.getId())
        {
            case R.id.crear_factura:

                if (factura.conceptos.size() < 1)
                    Toast.makeText(view.getContext(), "Debes añadir al menos un cocepto para crear una factura", Toast.LENGTH_SHORT).show();
                else
                {
                    factura.id = tempdb.facturas.size() + 1;
                    factura.cliente = (Cliente) spinner_listview.getSelectedItem();
                    factura.fecha = Calendar.getInstance().getTime();
                    factura.calcularPrecioTotal();
                    tempdb.facturas.add(factura);
                    Intent intent = new Intent(getApplicationContext(), FacturasActivity.class);
                    intent.putExtra("tempdb", tempdb);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), FacturasActivity.class);
        intent.putExtra("tempdb", tempdb);
        startActivity(intent);
        finish();
    }
}
