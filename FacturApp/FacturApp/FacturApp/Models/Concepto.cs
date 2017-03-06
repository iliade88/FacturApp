using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacturApp.Models
{
    public class Concepto
    {
        public string texto { get; set; }
        public float precio { get; set; }

        public Concepto()
        {
            texto = "";
            precio = 0.00F;
        }

        public Concepto(string texto, float precio)
        {
            this.texto = texto;
            this.precio = precio;
        }

        public Concepto(Concepto concepto)
        {
            this.texto = concepto.texto;
            this.precio = concepto.precio;
        }
    }
}
