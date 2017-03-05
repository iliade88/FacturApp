using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacturApp.Models
{
    class Perfil
    {
        public string email { get; set; }
        public string dni { get; set; }
        public string nombre { get; set; }
        public string emailbackup { get; set; }
        public string direccion { get; set; }
        public string telefono { get; set; }

        public Perfil()
        {
            email = "";
            dni = "";
            nombre = "";
            emailbackup = "";
            direccion = "";
            telefono = "";
        }

        public Perfil(string email, string dni, string nombre, string emailbackup, string direccion, string telefono)
        {
            this.email = email;
            this.dni = dni;
            this.nombre = nombre;
            this.emailbackup = emailbackup;
            this.direccion = direccion;
            this.telefono = telefono;
        }
    }
}
