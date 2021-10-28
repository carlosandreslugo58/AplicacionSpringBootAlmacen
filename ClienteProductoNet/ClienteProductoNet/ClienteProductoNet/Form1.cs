using ClienteProductoNet.entity;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteProductoNet
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnListarProductos_Click(object sender, EventArgs e)
        {
            Producto prod;
            WebClient webClient;
            String uri;
            String respuesta = "";


            uri = "http://localhost:8091/productos";

            webClient = new WebClient();
            respuesta = webClient.DownloadString(uri);

         

            txtProductos.Text = respuesta;
        }

        private void btnBuscarProducto_Click(object sender, EventArgs e)
        {
            String cad = "", id;
            Producto prod;
            WebClient webClient;
            String uri;
            String respuesta = "";

            id = txtIdProducto.Text;

            uri = "http://localhost:8091/productos/" + id;

            webClient = new WebClient();
            respuesta = webClient.DownloadString(uri);

            prod = JsonConvert.DeserializeObject<Producto>(respuesta);

            cad = "DATOS DEL PRODUCTO" + Environment.NewLine;
            cad = cad + "Id = " + prod.Id + Environment.NewLine;
            cad = cad + "Nombre = " + prod.Nombre + Environment.NewLine;
            cad = cad + "Stock = " + prod.Stock + Environment.NewLine;
            cad = cad + "Precio = " + prod.Precio + Environment.NewLine;
            cad = cad + "Fecha de creación = " + prod.FechaCreacion + Environment.NewLine;



            txtProductos.Text = cad;
        }

        private void btnAdicionarProducto_Click(object sender, EventArgs e)
        {
            Producto prod;
            Categoria cate;
            String nombre;
            String prodJSON;
            WebClient webClient;
            String uri;
            HttpClient cliente;
            HttpContent contenido;
            HttpResponseMessage respuesta;

            prod = new Producto();
            cate = new Categoria();
            nombre = txtNombre.Text;

            cate.Id = 1;
            cate.Nombre = "Alimentos";

            prod.Id = 0;
            prod.Nombre = nombre;
            prod.Descripcion = "Descripción ... " + nombre;
            prod.Stock = 10;
            prod.Precio = 1000;
            prod.Estado = "POS ASIGNAR";
            prod.FechaCreacion = new DateTime();
            prod.Categoria = cate;

            prodJSON = JsonConvert.SerializeObject(prod);
            
            //LA SERIALIZACIÓN LO TRAE TODO Y LO TRAE MAL! HAY QUE HACER MAS PRUEBAS!

            prodJSON = "{'nombre': 'Huevos','descripcion': 'Play 5','stock': 5.0,'precio': 2500000.0,'categoria': {'id': 3,'nombre': 'Electrodomésticos'}}";
            prodJSON = prodJSON.Replace((char)39, (char)34);

            cliente = new HttpClient();
            uri = "http://localhost:8091/productos";
            cliente.BaseAddress = new Uri(uri);
            cliente.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            contenido = new StringContent(prodJSON, UTF8Encoding.UTF8, "application/json");
            respuesta = cliente.PostAsync(uri, contenido).Result;
            String resultado = respuesta.Content.ReadAsStringAsync().Result;
            txtProductos.Text = resultado;


            if (respuesta.IsSuccessStatusCode)
            {
                MessageBox.Show("Producto Adicionado");
            }
        }
    }
}
