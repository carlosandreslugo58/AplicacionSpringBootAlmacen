package cal.almacenV1.servicioProducto;


import cal.almacenV1.servicioProducto.entity.Categoria;
import cal.almacenV1.servicioProducto.entity.Producto;
import cal.almacenV1.servicioProducto.repositorio.RepositorioProducto;
import cal.almacenV1.servicioProducto.servicios.IServicioProducto;
import cal.almacenV1.servicioProducto.servicios.ServicioProductoImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ServicioProductoMockTest {

    @Mock
    private RepositorioProducto repositorioProducto;

    private IServicioProducto servicioProducto;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        servicioProducto = new ServicioProductoImpl(repositorioProducto);

        Producto computador = Producto.builder()
                .id(1L)
                .nombre("Computador")
                .categoria(Categoria.builder().id(1L).nombre("Computación").build())
                .stock(5)
                .precio(2300000)
                .build();

        Mockito.when(repositorioProducto.findById(1L))
                .thenReturn(Optional.of(computador));

        Mockito.when(repositorioProducto.save(computador)).thenReturn(computador);
    }

    @Test
    public void whenValidGetId_ThenReturnProducto(){
        Producto buscado = servicioProducto.buscarProducto(1L);
        Assertions.assertThat(buscado.getNombre()).isEqualTo("Computador");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Producto actualizado = servicioProducto.actualizarStock(1L, 5D);
        Assertions.assertThat(actualizado.getStock()).isEqualTo(10D);

    }
}
