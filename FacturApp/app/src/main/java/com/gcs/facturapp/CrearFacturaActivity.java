package com.gcs.facturapp;

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

import java.util.ArrayList;
import java.util.Calendar;

public class CrearFacturaActivity extends AppCompatActivity {

    private ArrayList<Cliente> clientes;
    private Factura factura;
    private ListView listview_conceptos;

    private Factura creaFacturaStub()
    {
        Calendar calendario = Calendar.getInstance();
        calendario.set(2017, 3, 12);

        Cliente cliente = new Cliente("Mario", "Navarro ruiz", "59343928J", "Calle de elda", "65676543", "mario@viajesta.com");

        ArrayList<Concepto> conceptos = new ArrayList<>();
        conceptos.add(new Concepto(1, (short) 1, "Galletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 2, "Papel", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 3, "Servilletas", Float.parseFloat("1.20"), 2));
        return new Factura(1,calendario.getTime(), cliente, Float.parseFloat("12.20"), conceptos);
    }

    private ArrayList<Cliente> crearClientes()
    {
        ArrayList<Cliente> nuevos_clientes = new ArrayList<>();

        nuevos_clientes.add(new Cliente("Mario", "Navarro ruiz", "59343928J", "Calle de elda", "65676543", "mario@viajesta.com"));
        nuevos_clientes.add(new Cliente("Alberto", "Sapiña Mora", "59343928J", "Calle de elda", "965692617", "mario@viajesta.com"));
        nuevos_clientes.add(new Cliente("Cristobal", "Jesus Gonzalez", "59343928J", "Calle de elda", "96543421", "mario@viajesta.com"));
        nuevos_clientes.add(new Cliente("Manuel", "Volteador Garcia", "59343928J", "Calle de elda", "67654356", "mario@viajesta.com"));

        return nuevos_clientes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_factura);

        //Inicializacion de datos stub necesarios
        clientes = crearClientes();
        factura = creaFacturaStub();

        //Creamos el select de cliente
        Spinner spinner = (Spinner) findViewById(R.id.seleccion_cliente);
        ArrayAdapter<Cliente> spinnerArrayAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, clientes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        //Creamos el listview de conceptos
        listview_conceptos = (ListView) findViewById(R.id.listview_conceptos_crear_factura);
        View header = getLayoutInflater().inflate(R.layout.header_listview_conceptos, null);
        listview_conceptos.addHeaderView(header);
        ConceptoAdapter adapter = new ConceptoAdapter(this, factura.conceptos);
        listview_conceptos.setAdapter(adapter);

        //El boton para añadir conceptos
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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

                            Concepto nuevo_concepto = new Concepto(factura.id, factura.getNumConceptos(), descripcion_concepto, precio_concepto, cantidad);
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
