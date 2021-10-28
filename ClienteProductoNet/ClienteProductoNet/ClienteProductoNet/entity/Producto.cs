using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteProductoNet.entity
{
    class Producto
    {
        private long id;
        private String nombre;
        private String descripcion;
        private double stock;
        private double precio;
        private String estado;
        private DateTime fechaCreacion;
        private Categoria categoria;

        public long Id { get => id; set => id = value; }
        public string Nombre { get => nombre; set => nombre = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
        public double Stock { get => stock; set => stock = value; }
        public double Precio { get => precio; set => precio = value; }
        public string Estado { get => estado; set => estado = value; }
        public DateTime FechaCreacion { get => fechaCreacion; set => fechaCreacion = value; }
        internal Categoria Categoria { get => categoria; set => categoria = value; }
    }
}
