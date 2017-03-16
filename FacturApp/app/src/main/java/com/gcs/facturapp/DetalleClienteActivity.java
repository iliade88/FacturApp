package com.gcs.facturapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class DetalleClienteActivity extends AppCompatActivity {

    public boolean en_edicion;
    private ViewSwitcher switcher_editar_nombre;
    private TextView nombre;
    private EditText nuevo_nombre;
    private ViewSwitcher switcher_editar_dnicif;
    private TextView dnicif;
    private EditText nuevo_dnicif;
    private ViewSwitcher switcher_editar_direccion;
    private TextView direccion;
    private EditText nueva_direccion;
    private ViewSwitcher switcher_editar_telefono;
    private TextView telefono;
    private EditText nuevo_telefono;
    private ViewSwitcher switcher_editar_email;
    private TextView email;
    private EditText nuevo_email;
    private ViewSwitcher switcher_botones_editar;
    private TextView boton_editar_cliente;
    private TextView boton_cancelar_cambios_cliente;
    private TextView boton_guardar_cambios_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);

        en_edicion = false;
        switcher_editar_nombre = (ViewSwitcher) findViewById(R.id.switcher_editar_nombre);
        nombre = (TextView) findViewById(R.id.nombre);
        nuevo_nombre = (EditText) findViewById(R.id.nuevo_nombre);
        switcher_editar_dnicif = (ViewSwitcher) findViewById(R.id.switcher_editar_dnicif);
        dnicif = (TextView) findViewById(R.id.dnicif);
        nuevo_dnicif = (EditText) findViewById(R.id.nuevo_dnicif);
        switcher_editar_direccion = (ViewSwitcher) findViewById(R.id.switcher_editar_direccion);
        direccion = (TextView) findViewById(R.id.direccion);
        nueva_direccion = (EditText) findViewById(R.id.nueva_direccion);
        switcher_editar_telefono = (ViewSwitcher) findViewById(R.id.switcher_editar_telefono);
        telefono = (TextView) findViewById(R.id.telefono);
        nuevo_telefono = (EditText) findViewById(R.id.nuevo_telefono);
        switcher_editar_email = (ViewSwitcher) findViewById(R.id.switcher_editar_email);
        email = (TextView) findViewById(R.id.email);
        nuevo_email = (EditText) findViewById(R.id.nuevo_email);
        switcher_botones_editar = (ViewSwitcher) findViewById(R.id.switcher_botones_editar);
        boton_editar_cliente = (TextView) findViewById(R.id.boton_editar_cliente);
        boton_cancelar_cambios_cliente = (TextView) findViewById(R.id.boton_cancelar_cambios_cliente);
        boton_guardar_cambios_cliente = (TextView) findViewById(R.id.boton_guardar_cambios_cliente);

        nombre.setText("Mario Navarro Ruiz");
        dnicif.setText("45928554J");
        direccion.setText("Calle de elda, 123");
        telefono.setText("965696969");
        email.setText("mario@viajesta.com");
    }

    public void onClickEditarCliente(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_editar_cliente:

                toogleSwitchers();
                break;
        }
    }

    public void onClickCancelarCambios(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_cancelar_cambios_cliente:

                toogleSwitchers();
                break;
        }
    }

    public void onClickGuardarCambios(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_guardar_cambios_cliente:

                toogleSwitchers();
                break;
        }
    }

    public void toogleSwitchers()
    {
        en_edicion = !en_edicion;

        //Cambiamos entre textview y edittext
        switcher_editar_nombre.showNext();
        switcher_editar_dnicif.showNext();
        switcher_editar_direccion.showNext();
        switcher_editar_telefono.showNext();
        switcher_editar_email.showNext();
        switcher_botones_editar.showNext();

        //Copiamos los valores al text o al edit segun si estamos editando o no
        copiaValorSegunEdicion(en_edicion, nombre, nuevo_nombre);
        copiaValorSegunEdicion(en_edicion, dnicif, nuevo_dnicif);
        copiaValorSegunEdicion(en_edicion, direccion, nueva_direccion);
        copiaValorSegunEdicion(en_edicion, telefono, nuevo_telefono);
        copiaValorSegunEdicion(en_edicion, email, nuevo_email);
    }

    public void copiaValorSegunEdicion(boolean en_edicion, TextView text, EditText edit)
    {
        if (en_edicion)
            edit.setText(text.getText());
        else
            text.setText(edit.getText());
    }
}
