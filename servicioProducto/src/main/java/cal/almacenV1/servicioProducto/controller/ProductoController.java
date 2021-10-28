package cal.almacenV1.servicioProducto.controller;


import cal.almacenV1.servicioProducto.entity.Categoria;
import cal.almacenV1.servicioProducto.entity.Producto;
import cal.almacenV1.servicioProducto.servicios.IServicioProducto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {

    @Autowired
    private IServicioProducto servicioProducto;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(@RequestParam(value = "idCategoria",required = false) Long idCategoria){
        List <Producto> productos = new ArrayList<>();
        if(idCategoria == null){
            productos = servicioProducto.listarTodosProductos();
            if (productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }

        }else{
            productos = servicioProducto.buscarPorCategoria(Categoria.builder().id(idCategoria).build());
            if (productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(productos);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> darProducto(@PathVariable("id") Long id){
        Producto buscado = servicioProducto.buscarProducto(id);

        if(buscado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(buscado);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Producto nuevo = servicioProducto.crearProducto(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto){
        producto.setId(id);
        Producto actualizado = servicioProducto.actualizarproducto(producto);
        if (actualizado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable(value = "id") Long id){
        Producto eliminado = servicioProducto.eliminarProducto(id);
        if (eliminado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eliminado);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Producto> updateStockProducto(@PathVariable(value = "id") Long id, @RequestParam(value = "cantidad",required = true) Double cantidad){

        Producto actualizado = servicioProducto.actualizarStock(id, cantidad);
        if (actualizado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errores = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .mensajes(errores)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }


}
