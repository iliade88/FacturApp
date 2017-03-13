package com.gcs.facturapp.models;

/**
 * Created by Cris on 13/03/2017.
 */

public class Cliente {
    public String nombre;
    public String apellidos;
    public String dnicif;
    public String direccion;
    public String telefono;
    public String email;

    public Cliente(){};

    public Cliente(String nombre, String apellidos, String dnicif, String direccion, String telefono, String email)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dnicif = dnicif;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(Cliente cliente)
    {
        this.nombre = cliente.nombre;
        this.apellidos = cliente.apellidos;
        this.dnicif = cliente.dnicif;
        this.direccion = cliente.direccion;
        this.telefono = cliente.telefono;
        this.email = cliente.email;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj instanceof Cliente) {
            Cliente cli = (Cliente)obj;
            return this.nombre.equals(cli.nombre) && this.apellidos.equals(cli.apellidos) || this.dnicif.equals(cli.dnicif);
        }

        return false;
    }
}
