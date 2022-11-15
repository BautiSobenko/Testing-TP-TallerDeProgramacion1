package com.grupo8.app.negocio;

import com.grupo8.app.dto.AddProductoRequest;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Producto;
import com.grupo8.app.persistencia.Ipersistencia;
import com.grupo8.app.persistencia.PersistenciaXML;
import com.grupo8.app.wrappers.ProductoWrapper;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionDeProductos {
    public Empresa empresa;

    public GestionDeProductos() {
        this.empresa = Empresa.getEmpresa();
    }

    /**
     * Agrega un producto a la lista de productos de la empresa
     * @param request DTO con los datos del producto a agregar
     * @return DTO del producto agregado
     */
    public ProductoDTO addProducto(AddProductoRequest request) {
        if (request.getCosto() <= 0 || request.getPrecio() <= 0) {
            throw new IllegalArgumentException("El costo y el precio deben ser mayores a 0");
        }

        if (request.getPrecio() < request.getCosto()) {
            throw new IllegalArgumentException("El precio debe ser mayor al costo");
        }

        Producto producto = new Producto(request.getNombre(), request.getPrecio(), request.getCosto(), request.getStock());
        this.empresa.getProductos().getProductos().add(producto);
        persistir();

        return ProductoDTO.of(producto);
    }

    /**
     * Elimina un producto de la lista de productos de la empresa
     * @param id ID del producto a eliminar
     * @throws IllegalArgumentException si el ID pertenece a un producto en una comanda
     */
    public void deleteProducto(@NonNull String id) {

        if (this.empresa.getComandas().getComandas().stream().anyMatch(c -> c.getPedidos().stream().anyMatch(p -> p.getProducto().getId().equals(id)))) {
            throw new IllegalArgumentException("No se puede eliminar un producto que existe en una comanda");
        }

        this.empresa.getProductos().getProductos().removeIf(producto -> id.equals(producto.getId()));

        persistir();
    }

    /**
     * Modifica un producto de la lista de productos de la empresa
     * @param id id del producto a modificar
     * @param informacion DTO con los datos a modificar
     * @throws EntidadNoEncontradaException
     */
    public void editProducto(String id, AddProductoRequest informacion) throws EntidadNoEncontradaException {
        Optional<Producto> producto = this.empresa.getProductos().getProductos().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst();

        if (producto.isPresent()) {
            producto.get().update(informacion);
            persistir();
        } else {
            throw new EntidadNoEncontradaException("No se encontro el producto");
        }
    }

    /**
     * Persiste los productos de la empresa en el archivo productos.xml
     */
    public void persistir() {
        Ipersistencia<ProductoWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("productos.xml");
            persistencia.escribir(this.empresa.getProductos());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }

    /**
     * Obtiene todos los productos de la empresa
     * @return Lista de DTOs de los productos
     */
    public List<ProductoDTO> obtenerProductos() {
        return empresa.getProductos()
                .getProductos()
                .stream()
                .map(ProductoDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un producto de la empresa
     * @param id id del producto a obtener
     * @return
     * @throws EntidadNoEncontradaException si no se encuentra el producto
     */
    public ProductoDTO obtenerPorId(String id) throws EntidadNoEncontradaException {
        Optional<Producto> producto = this.empresa.getProductos()
                .getProductos()
                .stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst();

        if (producto.isPresent()) {
            return ProductoDTO.of(producto.get());
        } else {
            throw new EntidadNoEncontradaException("No se encontro el producto");
        }
    }
}
