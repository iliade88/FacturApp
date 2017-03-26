package com.gcs.facturapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gcs.facturapp.models.Cliente;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearClienteActivity extends AppCompatActivity {

    private EditText nombre_cliente;
    private EditText apellidos_cliente;
    private EditText dnicif_cliente;
    private EditText direccion_cliente;
    private EditText telefono_cliente;
    private EditText email_cliente;
    private Button boton_cancelar_crear_cliente;
    private Button boton_crear_cliente;

    ArrayList<Cliente> clienteslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);
        clienteslist = (ArrayList<Cliente>) getIntent().getExtras().getSerializable("listaclientes");
        Log.d("crear cliente", clienteslist.size()+"");
    }

    public void onClickCancelar(View view)
    {
        switch (view.getId())
        {
            case R.id.boton_cancelar_crear_cliente:
                Intent intent = new Intent(view.getContext(), ClientesActivity.class);
                //intent.putExtra("nuevocliente", cli);
                intent.putExtra("listaclientes", clienteslist);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void onClickCrearCliente(View view)
    {
        nombre_cliente = (EditText) findViewById(R.id.nombre_cliente);
        String nombre = nombre_cliente.getText().toString().trim();
        apellidos_cliente = (EditText) findViewById(R.id.apellidos_cliente);
        String apellidos = apellidos_cliente.getText().toString().trim();
        dnicif_cliente = (EditText) findViewById(R.id.dnicif_cliente);
        String dni = dnicif_cliente.getText().toString().trim();
        direccion_cliente = (EditText) findViewById(R.id.direccion_cliente);
        String direccion = direccion_cliente.getText().toString().trim();
        telefono_cliente = (EditText) findViewById(R.id.telefono_cliente);
        String telefono = telefono_cliente.getText().toString().trim();
        email_cliente = (EditText) findViewById(R.id.email_cliente);
        String email = email_cliente.getText().toString().trim();
        boton_cancelar_crear_cliente = (Button) findViewById(R.id.boton_cancelar_crear_cliente);
        boton_crear_cliente = (Button) findViewById(R.id.boton_crear_cliente);

        boolean hay_errores = false;

        /*TODO - Pasar datos a ClientesActivity para añadirlo a la lista*/
        switch (view.getId())
        {
            case R.id.boton_crear_cliente:
                if(nombre.length()==0)
                {
                    nombre_cliente.setError("El nombre no puede estar vacío");
                    hay_errores=true;
                }

                if(apellidos.length()==0)
                {
                    apellidos_cliente.setError("Los apellidos no pueden estar vacíos");
                    hay_errores=true;
                }

                if(!validarDNICIF(dni))
                {
                    dnicif_cliente.setError("DNI o CIF inválido");
                    hay_errores=true;
                }

                if(direccion.length()==0)
                {
                    direccion_cliente.setError("La dirección no puede estar vacía");
                    hay_errores=true;
                }

                if(telefono.length()!=9 || !validarTFNO(telefono))
                {
                    telefono_cliente.setError("Debes introducir un teléfono válido");
                    hay_errores=true;
                }

                if(!validarEmail(email))
                {
                    email_cliente.setError("El email introducido es incorrecto.");
                    hay_errores=true;
                }

                if (hay_errores)
                {
                    Toast.makeText(view.getContext(), "Hay errores en el formulario", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Cliente cli = new Cliente(nombre, apellidos, dni, direccion, telefono, email);
                    clienteslist.add(cli);
                    Intent intent = new Intent(view.getContext(), ClientesActivity.class);
                    //intent.putExtra("nuevocliente", cli);
                    intent.putExtra("listaclientes", clienteslist);
                    startActivity(intent);
                    finish();
                }
                break;
        }
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
        Intent intent = new Intent(getApplicationContext(), ClientesActivity.class);
        intent.putExtra("listaclientes", clienteslist);
        startActivity(intent);
        finish();
    }
}
