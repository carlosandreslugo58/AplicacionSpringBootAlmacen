package cal.almacenV1.servicioProducto.repositorio;

import cal.almacenV1.servicioProducto.entity.Categoria;
import cal.almacenV1.servicioProducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {

    public List<Producto> findByCategoria(Categoria categoria);
    public List<Producto> findByNombre(String nombre);

}
