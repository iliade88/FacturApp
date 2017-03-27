package com.gcs.facturapp.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TempDB implements Serializable {

    public Usuario usuario;
    public ArrayList<Cliente> clientes;
    public ArrayList<Factura> facturas;

    public TempDB()
    {
        usuario = new Usuario();
        clientes = new ArrayList<Cliente>();
        facturas = new ArrayList<Factura>();
    }
}
