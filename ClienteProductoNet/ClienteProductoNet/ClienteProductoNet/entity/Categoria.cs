using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteProductoNet.entity
{
    class Categoria
    {
        private long id;
        private String nombre;

        public long Id { get => id; set => id = value; }
        public string Nombre { get => nombre; set => nombre = value; }
    }
}
