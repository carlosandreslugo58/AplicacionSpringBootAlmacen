package cal.almacenV1.servicioProducto.servicios;

import cal.almacenV1.servicioProducto.entity.Categoria;
import cal.almacenV1.servicioProducto.entity.Producto;
import cal.almacenV1.servicioProducto.repositorio.RepositorioProducto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ServicioProductoImpl implements IServicioProducto{

    private final RepositorioProducto respositorioProducto;


    @Override
    public List<Producto> listarTodosProductos() {
        return respositorioProducto.findAll();
    }

    @Override
    public Producto buscarProducto(Long id) {
        return respositorioProducto.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        producto.setEstado("DISPONIBLE");
        producto.setFechaCreacion(new Date());
        return respositorioProducto.save(producto);
    }

    @Override
    public Producto actualizarproducto(Producto producto) {
        Producto buscado = buscarProducto(producto.getId());
        if (buscado==null) {
            return null;
        }
        buscado.setNombre(producto.getNombre());
        buscado.setDescripcion(producto.getDescripcion());
        buscado.setCategoria(producto.getCategoria());
        buscado.setPrecio(producto.getPrecio());
        return respositorioProducto.save(buscado);
    }

    @Override
    public Producto eliminarProducto(Long id) {
        Producto buscado = buscarProducto(id);
        if (buscado==null) {
            return null;
        }

        buscado.setEstado("ELIMINADO");
        return respositorioProducto.save(buscado);
    }

    @Override
    public List<Producto> buscarPorCategoria(Categoria categoria) {
        return respositorioProducto.findByCategoria(categoria);
    }

    @Override
    public Producto actualizarStock(Long id, Double cantidad) {
        Producto buscado = buscarProducto(id);
        if (buscado==null) {
            return null;
        }
        buscado.setStock(buscado.getStock() + cantidad);
        return respositorioProducto.save(buscado);
    }
}
