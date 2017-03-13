package com.gcs.facturapp.models;

/**
 * Created by Cris on 13/03/2017.
 */

public class Concepto {

    public long id_factura;
    public short id_concepto;
    public String descripcion;
    public int precio;
    public int cantidad;

    public Concepto(){};

    public Concepto(long id_factura, short id_concepto, String descripcion, int precio, int cantidad)
    {
        this.id_factura = id_factura;
        this.id_concepto = id_concepto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Concepto(Concepto concepto)
    {
        this.id_factura = concepto.id_factura;
        this.id_concepto = concepto.id_concepto;
        this.descripcion = concepto.descripcion;
        this.precio = concepto.precio;
        this.cantidad = concepto.cantidad;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj instanceof Concepto) {
            Concepto con = (Concepto)obj;
            return this.id_factura == con.id_factura && this.id_concepto == con.id_concepto;
        }
        return false;
    }
}
