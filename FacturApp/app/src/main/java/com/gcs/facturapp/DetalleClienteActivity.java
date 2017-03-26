package com.gcs.facturapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.gcs.facturapp.models.Cliente;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetalleClienteActivity extends AppCompatActivity {

    public boolean en_edicion;
    private ViewSwitcher switcher_editar_nombre;
    private TextView nombre;
    private EditText nuevo_nombre;
    private ViewSwitcher switcher_editar_apellido;
    private TextView apellido;
    private EditText nuevo_apellido;
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

    ArrayList<Cliente> clienteslist;
    Cliente cliente_sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);

        en_edicion = false;
        switcher_editar_nombre = (ViewSwitcher) findViewById(R.id.switcher_editar_nombre);
        nombre = (TextView) findViewById(R.id.nombre);
        nuevo_nombre = (EditText) findViewById(R.id.nuevo_nombre);
        switcher_editar_apellido = (ViewSwitcher) findViewById(R.id.switcher_editar_apellido);
        apellido = (TextView) findViewById(R.id.apellido);
        nuevo_apellido = (EditText) findViewById(R.id.nuevo_apellido);
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

        clienteslist = (ArrayList<Cliente>) getIntent().getExtras().getSerializable("listaclientes");
        cliente_sel = (Cliente) getIntent().getExtras().getSerializable("clienteseleccionado");
        nombre.setText(cliente_sel.nombre);
        apellido.setText(cliente_sel.apellidos);
        dnicif.setText(cliente_sel.dnicif);
        direccion.setText(cliente_sel.direccion);
        telefono.setText(cliente_sel.telefono);
        email.setText(cliente_sel.email);
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
        boolean hay_errores = false;
        switch (view.getId())
        {
            case R.id.boton_guardar_cambios_cliente:
                String n_nombre = nuevo_nombre.getText().toString().trim();
                String n_apellido = nuevo_apellido.getText().toString().trim();
                String n_dni = nuevo_dnicif.getText().toString().trim();
                String n_direccion = nueva_direccion.getText().toString().trim();
                String n_telf = nuevo_telefono.getText().toString().trim();
                String n_email = nuevo_email.getText().toString().trim();
                if(n_nombre.length()==0)
                {
                    nuevo_nombre.setError("El nombre no puede estar vacío");
                    hay_errores=true;
                }

                if(n_apellido.length()==0)
                {
                    nuevo_apellido.setError("Los apellidos no pueden estar vacíos");
                    hay_errores=true;
                }

                if(!validarDNICIF(n_dni))
                {
                    nuevo_dnicif.setError("DNI o CIF inválido");
                    hay_errores=true;
                }

                if(n_direccion.length()==0)
                {
                    nueva_direccion.setError("La dirección no puede estar vacía");
                    hay_errores=true;
                }

                if(n_telf.length()!=9 || !validarTFNO(n_telf))
                {
                    nuevo_telefono.setError("Debes introducir un teléfono válido");
                    hay_errores=true;
                }

                if(!validarEmail(n_email))
                {
                    nuevo_email.setError("El email introducido es incorrecto.");
                    hay_errores=true;
                }

                if (hay_errores)
                {
                    Toast.makeText(view.getContext(), "Hay errores en el formulario", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    toogleSwitchers();
                    cliente_sel.email=n_email;
                    cliente_sel.nombre=n_nombre;
                    cliente_sel.apellidos=n_apellido;
                    cliente_sel.direccion=n_direccion;
                    cliente_sel.telefono=n_telf;
                    cliente_sel.dnicif=n_dni;
                }

                break;
        }
    }

    public void toogleSwitchers()
    {
        en_edicion = !en_edicion;

        //Cambiamos entre textview y edittext
        switcher_editar_nombre.showNext();
        switcher_editar_apellido.showNext();
        switcher_editar_dnicif.showNext();
        switcher_editar_direccion.showNext();
        switcher_editar_telefono.showNext();
        switcher_editar_email.showNext();
        switcher_botones_editar.showNext();

        //Copiamos los valores al text o al edit segun si estamos editando o no
        copiaValorSegunEdicion(en_edicion, nombre, nuevo_nombre);
        copiaValorSegunEdicion(en_edicion, apellido, nuevo_apellido);
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

    private boolean validarEmail(String email)
    {
        //Regexp email
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]{2,15}.[a-zA-Z]{2,4}(.[a-zA-Z]{2,4})?$");
        Matcher m = p.matcher(email);
        //si el correo es correcto devuelve TRUE o de lo contrario FALSE
        return m.matches();
    }

    private boolean validarDNICIF(String dnicif)
    {
        boolean es_dni = false;
        boolean es_cif = false;

        //Regexp para dni
        Pattern p = Pattern.compile("(([X-Z]{1})([-]?)(\\d{7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]{1}))");
        Matcher m = p.matcher(dnicif);

        if (m.matches())
        {
            try
            {
                int dni = Integer.parseInt(dnicif.substring(0, dnicif.length()-1));
                String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKE";
                int modulo= dni % 23;
                char letra = juegoCaracteres.charAt(modulo);
                char letra_formulario = dnicif.charAt(dnicif.length()-1);
                if (letra == letra_formulario)
                {
                    es_dni = true;
                }
            }
            catch (Exception e){
                es_dni = false;
            }
        }

        //Regexp para cif
        p = Pattern.compile("^[a-zA-Z]{1}[0-9]{8}$");
        m = p.matcher(dnicif);

        es_cif = m.matches();

        return es_dni || es_cif;
    }

    private boolean validarTFNO(String tfno)
    {
        boolean devolver=true;
        try{
            int i = Integer.parseInt(tfno);
        } catch(Exception e) {
            devolver=false;
        }
        return devolver;
    }

    @Override
    public void onBackPressed() {

        if(en_edicion)
        {
            cliente_sel.nombre=nombre.getText().toString().trim();
            cliente_sel.apellidos=apellido.getText().toString().trim();
            cliente_sel.dnicif=dnicif.getText().toString().trim();
            cliente_sel.direccion=direccion.getText().toString().trim();
            cliente_sel.telefono=telefono.getText().toString().trim();
            cliente_sel.email=email.getText().toString().trim();
            toogleSwitchers();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), ClientesActivity.class);
            intent.putExtra("listaclientes", clienteslist);
            startActivity(intent);
            finish();
        }
    }
}
