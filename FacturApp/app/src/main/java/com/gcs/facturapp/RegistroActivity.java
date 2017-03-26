package com.gcs.facturapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gcs.facturapp.models.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void registrar(View view) {

        EditText ed_email = (EditText) findViewById(R.id.email);
        String email = ed_email.getText().toString().trim();
        EditText ed_password = (EditText) findViewById(R.id.password);
        String password = ed_password.getText().toString().trim();
        EditText ed_repeat_password = (EditText) findViewById(R.id.repeat_password);
        String repeat_password = ed_repeat_password.getText().toString().trim();
        EditText ed_dnicif = (EditText) findViewById(R.id.dnicif);
        String dnicif = ed_dnicif.getText().toString().trim();
        EditText ed_nombre_empresa = (EditText) findViewById(R.id.nombre_empresa);
        String nombre_empresa = ed_nombre_empresa.getText().toString().trim();
        EditText ed_nombre = (EditText) findViewById(R.id.nombre);
        String nombre = ed_nombre.getText().toString().trim();
        EditText ed_apellidos = (EditText) findViewById(R.id.apellidos);
        String apellidos = ed_apellidos.getText().toString().trim();
        boolean hay_error = false;

        if (!validarEmail(email))
        {
            ed_email.setError("Email inválido");
            hay_error = true;
        }

        if (password.length() < 5)
        {
            ed_password.setError("Contraseña demasiado corta");
            hay_error = true;
        }
        else if(!password.equals(repeat_password))
        {
            ed_password.setError("Las contraseñas no coinciden");
            hay_error = true;
        }

        if (!validarDNICIF(dnicif))
        {
            ed_dnicif.setError("DNI o CIF inválido");
            hay_error = true;
        }

        if (nombre_empresa.length() < 1)
        {
            ed_nombre_empresa.setError("El nombre de empresa no puede estar vacío");
            hay_error = true;
        }

        if (nombre.length() < 1)
        {
            ed_nombre.setError("El nombre no puede estar vacío");
            hay_error = true;
        }

        if (apellidos.length() < 1)
        {
            ed_apellidos.setError("Los apellidos no pueden estar vacío");
            hay_error = true;
        }

        if (hay_error)
        {
            Toast.makeText(view.getContext(), "Hay errores en el formulario", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Usuario usu = new Usuario(email, password, dnicif, nombre_empresa, nombre, apellidos);

            switch (view.getId())
            {
                case R.id.registrar:

                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    intent.putExtra("parametro", usu);
                    Log.d("REGISTRO:", "Mandando usu " + usu.toString());
                    startActivity(intent);
                    break;
            }
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
}
