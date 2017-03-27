package com.gcs.facturapp.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TempDB implements Serializable {

    public Usuario usuario;
    public ArrayList<Cliente> clientes;
    public ArrayList<Factura> facturas;

    public TempDB()
    {
        usuario = new Usuario();
        clientes = new ArrayList<Cliente>();
        facturas = new ArrayList<Factura>();

        inicializaDatosPrueba();
    }

    private void inicializaDatosPrueba()
    {
        /**
         * Creando clientes...
         */
        Cliente mario = new Cliente("Mario", "Navarro ruiz", "59343928J", "Calle de elda", "65676543", "mario@viajesta.com");
        Cliente alberto = new Cliente("Alberto", "Sapiña Mora", "59343928J", "Calle de elda", "965692617", "mario@viajesta.com");
        Cliente cris = new Cliente("Cristobal", "Jesus Gonzalez", "59343928J", "Calle de elda", "96543421", "mario@viajesta.com");
        Cliente manu = new Cliente("Manuel", "Volteador Garcia", "59343928J", "Calle de elda", "67654356", "mario@viajesta.com");

        clientes.add(mario);
        clientes.add(alberto);
        clientes.add(cris);
        clientes.add(manu);

        /**
         * Creando Facturas...
         */
        ArrayList<Concepto> conceptos = new ArrayList<>();
        conceptos.add(new Concepto(1, (short) 1, "Galletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 2, "Papel", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 3, "Servilletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 4, "Tenedores", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 5, "Platos", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 6, "Mayonesa", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 7, "Salsa BBQ", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 8, "Chuletas", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 9, "Queso", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 10, "Pan", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 11, "Agua", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 12, "Cerveza", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 13, "Cebolla", Float.parseFloat("1.20"), 2));
        conceptos.add(new Concepto(1, (short) 14, "Morcilla", Float.parseFloat("1.20"), 2));

        facturas.add(new Factura(1,creaDate(18,2,2017), mario, Float.parseFloat("12.20"), conceptos, false));
        facturas.add(new Factura(2,creaDate(12,1,2017), mario, Float.parseFloat("12.20"), conceptos, false));
        facturas.add(new Factura(3,creaDate(21,4,2017), alberto, Float.parseFloat("12.20"), conceptos, false));
        facturas.add(new Factura(4,creaDate(30,3,2017), cris, Float.parseFloat("12.20"), conceptos, false));
        facturas.add(new Factura(5,creaDate(6,11,2017), manu, Float.parseFloat("12.20"), conceptos, false));
        facturas.add(new Factura(6,creaDate(8,2,2017), alberto, Float.parseFloat("12.20"), conceptos, false));
        facturas.add(new Factura(7,creaDate(16,2,2017), manu, Float.parseFloat("12.20"), conceptos, false));
    }

    private Date creaDate(int dia, int mes, int anyo)
    {
        Calendar calendario = Calendar.getInstance();
        calendario.set(anyo, mes, dia);
        return calendario.getTime();
    }
}
