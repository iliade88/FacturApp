package com.gcs.facturapp.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cris on 13/03/2017.
 */

public class Factura {
    public long id;
    public Date fecha;
    public Cliente cliente;
    public ArrayList<Concepto> conceptos;

    public Factura(){};

    public Factura(long id, Date fecha, Cliente cliente, ArrayList<Concepto> conceptos)
    {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.conceptos = conceptos;
    }

    public Factura(Factura factura)
    {
        this.id = -1;
        this.fecha = factura.fecha;
        this.cliente = factura.cliente;
        this.conceptos = (ArrayList<Concepto>) factura.conceptos.clone();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj instanceof Factura) {
            Factura fac = (Factura)obj;
            return this.id == fac.id && this.fecha.equals(fac.fecha) && this.cliente.equals(fac.cliente);
        }
        return false;
    }
}
