package cal.almacenV1.servicioProducto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;



@Entity
@Table(name="PRODUCTO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre del Producto no debe ser vacío.")
    private String nombre;
    private String descripcion;
    @Positive(message = "Es stock no puede ser negativo.")
    private double stock;
    private double precio;
    private String estado;

    @Column(name="FECHADECREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategoria")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @NotNull(message = "La categoría NO puede ser vacía.")
    private Categoria categoria;



}
