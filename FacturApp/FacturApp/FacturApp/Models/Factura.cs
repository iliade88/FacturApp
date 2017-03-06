using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacturApp.Models
{
    public class Factura
    {
        public ulong numfactura { get; set; }
        public Cliente cliente { get; set; }
        public DateTime fecha { get; set; }
        public List<Concepto> conceptos { get; set; }

        public Factura()
        {
            numfactura = 0;
            cliente = new Cliente();
            fecha = DateTime.Now;
            conceptos = new List<Concepto>();
        }

        public Factura(ulong numfactura, Cliente cliente, DateTime fecha, List<Concepto> conceptos)
        {
            this.numfactura = numfactura;
            this.cliente = cliente;
            this.fecha = fecha;
            this.conceptos = conceptos;

        }

        public Factura(Factura factura)
        {
            this.numfactura = factura.numfactura;
            this.cliente = factura.cliente;
            this.fecha = factura.fecha;
            this.conceptos = new List<Concepto>(factura.conceptos);
        }

        public string toString()
        {
            return numfactura + " - " + fecha.Date;
        }
    }
}
