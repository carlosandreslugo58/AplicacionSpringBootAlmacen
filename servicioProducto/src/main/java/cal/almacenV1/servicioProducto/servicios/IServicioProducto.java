package cal.almacenV1.servicioProducto.servicios;

import cal.almacenV1.servicioProducto.entity.Categoria;
import cal.almacenV1.servicioProducto.entity.Producto;

import java.util.List;

public interface IServicioProducto
{
    public List<Producto> listarTodosProductos();
    public Producto buscarProducto(Long id);
    public Producto crearProducto(Producto producto);
    public Producto actualizarproducto(Producto producto);
    public Producto eliminarProducto(Long id);
    public List<Producto> buscarPorCategoria(Categoria categoria);
    public Producto actualizarStock(Long id, Double cantidad);
}
