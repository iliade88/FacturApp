using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacturApp.Models
{
    public class Cliente
    {
        public string nombre { get; set; }
        public string dnicif { get; set; }
        public string direccion { get; set; }
        public int telefono { get; set; }
        public string email { get; set; }

        public Cliente()
        {
            nombre = "";
            dnicif = "";
            direccion = "";
            telefono = 111111111;
            email = "";
        }

        public Cliente(string nombre, string dnicif, string direccion, int telefono, string email)
        {
            this.nombre = nombre;
            this.dnicif = dnicif;
            this.direccion = direccion;
            this.telefono = telefono;
            this.email = email;
        }
    }
}
