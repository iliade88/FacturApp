package com.gcs.facturapp.models;

import android.net.Uri;

import java.io.Serializable;

public class Usuario implements Serializable {

    public String email;
    public String contrasenya;
    public String dnicif;
    public String nombre_empresa;
    public String nombre;
    public String apellidos;
    public String foto;

    public Usuario(){}

    public Usuario(String email, String contrasenya, String dnicif, String nombre_empresa, String nombre, String apellidos)
    {
        this.email = email;
        this.contrasenya = contrasenya;
        this.dnicif = dnicif;
        this.nombre_empresa= nombre_empresa;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(Usuario usuario)
    {
        this.email = usuario.email;
        this.contrasenya = usuario.contrasenya;
        this.dnicif = usuario.dnicif;
        this.nombre_empresa= usuario.nombre_empresa;
        this.nombre = usuario.nombre;
        this.apellidos = usuario.apellidos;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj instanceof Usuario) {
            Usuario usu = (Usuario)obj;
            return this.email.equals(usu.email);
        }

        return false;
    }

    @Override
    public String toString()
    {
        return email + " " + contrasenya + " " + dnicif + " " + nombre_empresa + " " + nombre + " " + apellidos;
    }
}
