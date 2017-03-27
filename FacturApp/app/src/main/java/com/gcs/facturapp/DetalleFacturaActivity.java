package com.gcs.facturapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gcs.facturapp.adapters.ConceptoAdapter;
import com.gcs.facturapp.models.Cliente;
import com.gcs.facturapp.models.Concepto;
import com.gcs.facturapp.models.Factura;
import com.gcs.facturapp.models.TempDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetalleFacturaActivity extends AppCompatActivity {

    private Factura factura;
    private ListView listview_conceptos;
    private TempDB tempdb;
    private int posicion_seleccionada;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);

        tempdb = (TempDB) getIntent().getExtras().getSerializable("tempdb");
        posicion_seleccionada = getIntent().getExtras().getInt("posicion_seleccionada");

        //Lista de conceptos
        factura = tempdb.facturas.get(posicion_seleccionada);
        listview_conceptos = (ListView) findViewById(R.id.listview_conceptos_detalle_factura);
        View header = getLayoutInflater().inflate(R.layout.header_listview_conceptos, null);
        listview_conceptos.addHeaderView(header);
        ConceptoAdapter adapter = new ConceptoAdapter(this, factura.validada, factura.conceptos);
        listview_conceptos.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (factura.validada)
        {
            TextView eliminar_header_list_conceptos = (TextView) findViewById(R.id.eliminar_header_list_conceptos);
            eliminar_header_list_conceptos.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            Button btn_validar_factura = (Button) findViewById(R.id.btn_validar_factura);
            btn_validar_factura.setVisibility(View.GONE);
        }
        else
        {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetalleFacturaActivity.this);
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
    }

    public void validarFactura(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_validar_factura:
                factura.validada = true;
                ConceptoAdapter adapter = new ConceptoAdapter(this, factura.validada, factura.conceptos);
                listview_conceptos.setAdapter(adapter);
                Snackbar.make(view, "La factura ha sido validada y ya no se podrá modificar", Snackbar.LENGTH_LONG).show();
                TextView eliminar_header_list_conceptos = (TextView) findViewById(R.id.eliminar_header_list_conceptos);
                eliminar_header_list_conceptos.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                Button btn_validar_factura = (Button) findViewById(R.id.btn_validar_factura);
                btn_validar_factura.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        //Recalculamos precio total
        factura.calcularPrecioTotal();
        //Volcamos los nuevos datos a la posición seleccionada
        tempdb.facturas.set(posicion_seleccionada, factura);
    
        Intent intent = new Intent(getApplicationContext(), FacturasActivity.class);
        intent.putExtra("tempdb", tempdb);
        startActivity(intent);
        finish();
    }
}
