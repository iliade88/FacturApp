package com.gcs.facturapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.gcs.facturapp.adapters.ConceptoAdapter;
import com.gcs.facturapp.models.Cliente;
import com.gcs.facturapp.models.Concepto;
import com.gcs.facturapp.models.Factura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetalleFacturaActivity extends AppCompatActivity {

    private Toolbar menu;
    private Factura factura;
    private ListView listview_conceptos;

    public Factura creaFacturaStub()
    {
        Calendar calendario = Calendar.getInstance();
        calendario.set(2017, 3, 12);

        Cliente cliente = new Cliente("Mario", "Navarro ruiz", "59343928J", "Calle de elda", "65676543", "mario@viajesta.com");

        ArrayList<Concepto> conceptos = new ArrayList<>();
        conceptos.add(new Concepto(1, (short) 1, "Galletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 2, "Papel", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 3, "Servilletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 4, "Tenedores", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 5, "Platos", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 6, "Mayonesa", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 7, "Salsa BBQ", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 8, "Chuletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 9, "Queso", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 10, "Pan", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 11, "Agua", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 12, "Cerveza", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 13, "Cebolla", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 14, "Morcilla", Float.parseFloat("1.20"), 2));
        return new Factura(1,calendario.getTime(), cliente, Float.parseFloat("12.20"), conceptos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);

        //Creamos el menú | TODO - Implementar acciones menú
        menu = (Toolbar) findViewById(R.id.menu_detalle_factura);
        menu.getBackground().setAlpha(255);
        menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.validar_factura:
                        //texto.setText("Validar");
                        return true;

                    case R.id.imprimir_factura:
                        //texto.setText("Imprimir");
                        return true;

                    case R.id.enviar_factura:
                        //texto.setText("Enviar");
                        return true;
                }
                return true; //TODO comprobar esto
            }
        });
        menu.inflateMenu(R.menu.menu_detalle_factura);

        //Y después la lista de conceptos
        factura = creaFacturaStub();
        listview_conceptos = (ListView) findViewById(R.id.listview_conceptos_detalle_factura);
        ConceptoAdapter adapter = new ConceptoAdapter(this, factura.conceptos);
        listview_conceptos.setAdapter(adapter);
    }
}
