package com.gcs.facturapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{

    public long id;
    public Date fecha;
    public Cliente cliente;
    public float precio_total;
    public ArrayList<Concepto> conceptos;
    public boolean validada;

    public Factura(){
        conceptos = new ArrayList<Concepto>();
        validada = false;
    }

    public Factura(long id, Date fecha, Cliente cliente, float precio_total, ArrayList<Concepto> conceptos, boolean validada)
    {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.precio_total = precio_total;
        this.conceptos = (ArrayList<Concepto>) conceptos.clone();
        this.validada = validada;
    }

    public Factura(Factura factura)
    {
        this.id = -1;
        this.fecha = factura.fecha;
        this.cliente = factura.cliente;
        this.precio_total = factura.precio_total;
        this.conceptos = (ArrayList<Concepto>) factura.conceptos.clone();
        this.validada = factura.validada;
    }

    public void anyadirConcepto(Concepto concepto)
    {
        conceptos.add(concepto);
    }

    public short getNumConceptos()
    {
        return (short) conceptos.size();
    }

    public short getSiguienteIdConcepto()
    {
        short maxId = 0;
        for (int i = 0; i < conceptos.size(); i++)
        {
            if (conceptos.get(i).id_concepto > maxId)
            {
                maxId = (short) (conceptos.get(i).id_concepto + 1);
            }
        }
        return  maxId;
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

    public void calcularPrecioTotal()
    {
        float precio_acumulado = 0;

        for (int i = 0; i < conceptos.size(); i++)
        {
            Concepto concepto;
            concepto = conceptos.get(i);
            precio_acumulado += concepto.precio * concepto.cantidad;
        }

        precio_total = precio_acumulado;
    }
}
