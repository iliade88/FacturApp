package com.gcs.facturapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.gcs.facturapp.models.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private boolean en_edicion;
    private ViewSwitcher switcher_email;
    private TextView email;
    private EditText edit_email;
    private ViewSwitcher switcher_contrasena;
    private TextView contrasena;
    private EditText nueva_contrasena;
    private ViewSwitcher switcher_repetir_contrasena;
    private EditText repetir_contrasena;
    private ViewSwitcher switcher_dnicif;
    private TextView dnicif;
    private EditText nuevo_dnicif;
    private ViewSwitcher switcher_nombre_empresa;
    private TextView nombre_empresa;
    private EditText nuevo_nombre_empresa;
    private ViewSwitcher switcher_nombre;
    private TextView nombre;
    private EditText nuevo_nombre;
    private ViewSwitcher switcher_apellidos;
    private TextView apellidos;
    private EditText nuevos_apellidos;
    private ViewSwitcher switcher_botones_editar;
    private ImageButton imagenPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        en_edicion = false;
        switcher_email = (ViewSwitcher) findViewById(R.id.switcher_editar_email);
        email = (TextView) findViewById(R.id.email);
        edit_email = (EditText) findViewById(R.id.nuevo_email);
        switcher_contrasena = (ViewSwitcher) findViewById(R.id.switcher_editar_contrasena);
        contrasena = (TextView) findViewById(R.id.contrasena);
        nueva_contrasena = (EditText) findViewById(R.id.nueva_contrasena);
        switcher_repetir_contrasena = (ViewSwitcher) findViewById(R.id.switcher_repetir_contrasena);
        repetir_contrasena = (EditText) findViewById(R.id.repeat_password);
        switcher_dnicif = (ViewSwitcher) findViewById(R.id.switcher_editar_dnicif);
        dnicif = (TextView) findViewById(R.id.dnicif);
        nuevo_dnicif = (EditText) findViewById(R.id.nuevo_dnicif);
        switcher_nombre_empresa = (ViewSwitcher) findViewById(R.id.switcher_editar_nombre_empresa);
        nombre_empresa = (TextView) findViewById(R.id.nombre_empresa);
        nuevo_nombre_empresa = (EditText) findViewById(R.id.nuevo_nombre_empresa);
        switcher_nombre = (ViewSwitcher) findViewById(R.id.switcher_editar_nombre);
        nombre = (TextView) findViewById(R.id.nombre);
        nuevo_nombre = (EditText) findViewById(R.id.nuevo_nombre);
        switcher_apellidos = (ViewSwitcher) findViewById(R.id.switcher_editar_apellidos);
        apellidos = (TextView) findViewById(R.id.apellidos);
        nuevos_apellidos = (EditText) findViewById(R.id.nuevos_apellidos);
        switcher_botones_editar = (ViewSwitcher) findViewById(R.id.switcher_botones_editar);

        Bundle b = this.getIntent().getExtras();
        Usuario u = new Usuario();
        u = (Usuario)b.getSerializable("usuario");

        email.setText(u.email);
        contrasena.setText(u.contrasenya);
        dnicif.setText(u.dnicif);
        nombre_empresa.setText(u.nombre_empresa);
        nombre.setText(u.nombre);
        apellidos.setText(u.apellidos);
        /*email.setText("mario@viajesta.com");
        contrasena.setText("Hola");
        dnicif.setText("45628552J");
        nombre_empresa.setText("Viajesta S.A.");
        nombre.setText("Mario");
        apellidos.setText("Navarro Ruiz");*/

        imagenPerfil = (ImageButton) findViewById(R.id.imagenPerfil);
        imagenPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, /*Uri.parse("/mnt/sdcard/")*/android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, 100);
            }
        });


    }

    public void onClickEditarPerfil(View v)
    {
        switch (v.getId())
        {
            case R.id.boton_editar_perfil:

                toogleSwitchers();
                break;
        }
    }

    public void onClickGuardarCambios(View v)
    {
        switch (v.getId())
        {
            case R.id.boton_guardar_cambios_perfil:

                toogleSwitchers();
                break;
        }
    }

    public void onClickCancelarCambios(View v)
    {
        switch (v.getId())
        {
            case R.id.boton_cancelar_cambios_perfil:

                toogleSwitchers();
                break;
        }
    }

    public void toogleSwitchers()
    {
        en_edicion = !en_edicion;

        //Cambiamos entre textview y edittext
        switcher_email.showNext();
        switcher_contrasena.showNext();
        if (en_edicion)
            switcher_repetir_contrasena.setVisibility(View.VISIBLE);
        else
            switcher_repetir_contrasena.setVisibility(View.GONE);
        switcher_dnicif.showNext();
        switcher_nombre_empresa.showNext();
        switcher_nombre.showNext();
        switcher_apellidos.showNext();
        switcher_botones_editar.showNext();

        //Copiamos los valores al text o al edit segun si estamos editando o no
        copiaValorSegunEdicion(en_edicion, email, edit_email);
        copiaValorSegunEdicion(en_edicion, contrasena, nueva_contrasena);
        copiaValorSegunEdicion(en_edicion, dnicif, nuevo_dnicif);
        copiaValorSegunEdicion(en_edicion, nombre_empresa, nuevo_nombre_empresa);
        copiaValorSegunEdicion(en_edicion, nombre, nuevo_nombre);
        copiaValorSegunEdicion(en_edicion, apellidos, nuevos_apellidos);
    }

    public void copiaValorSegunEdicion(boolean en_edicion, TextView text, EditText edit)
    {
        if (en_edicion)
            edit.setText(text.getText());
        else
            text.setText(edit.getText());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            Uri imageUri = data.getData();
            imagenPerfil.setImageURI(imageUri);
        }
    }
}
