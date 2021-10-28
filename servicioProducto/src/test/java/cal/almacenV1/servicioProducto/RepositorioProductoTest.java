package cal.almacenV1.servicioProducto;

import cal.almacenV1.servicioProducto.entity.Categoria;
import cal.almacenV1.servicioProducto.entity.Producto;
import cal.almacenV1.servicioProducto.repositorio.RepositorioProducto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class RepositorioProductoTest {


    @Autowired
    private RepositorioProducto repositorioProducto;

    @Test
    public void whenFindByCategoria_thenReturnListProducto(){
        Producto producto01 = Producto.builder().nombre("Computador")
                .categoria(Categoria.builder().id(1L).nombre("Computación").build())
                .descripcion("Computador Asus")
                .stock(10)
                .precio(2300000)
                .estado("DISPONIBLE")
                .fechaCreacion(new Date())
                .build();

        repositorioProducto.save(producto01);

        List<Producto> encontrados = repositorioProducto.findByCategoria(producto01.getCategoria());
        Assertions.assertThat(encontrados.size()).isEqualTo(3);

    }

}
