package com.gcs.facturapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalleFacturaActivity extends AppCompatActivity {

    TextView texto;
    Toolbar menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle_factura);
        texto = (TextView) findViewById(R.id.mensaje_detalle_factura);
        menu = (Toolbar) findViewById(R.id.menu_detalle_factura);
        menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.validar_factura:
                        texto.setText("Validar");
                        return true;

                    case R.id.imprimir_factura:
                        texto.setText("Imprimir");
                        return true;

                    case R.id.enviar_factura:
                        texto.setText("Enviar");
                        return true;
                }
                return true; //TODO comprobar esto
            }
        });
        menu.inflateMenu(R.menu.menu_detalle_factura);
    }
}
