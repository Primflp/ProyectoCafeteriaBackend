package com.cafeteria.ms_boleta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.ms_boleta.clients.ProductoClientRest;
import com.cafeteria.ms_boleta.clients.UsuarioClientRest;
import com.cafeteria.ms_boleta.dtoProducto.ProductoDTO;
import com.cafeteria.ms_boleta.dtoUsuario.UsuarioDTO;
import com.cafeteria.ms_boleta.model.Carrito;
import com.cafeteria.ms_boleta.model.DetalleCarrito;
import com.cafeteria.ms_boleta.repository.CarritoRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;

@Service 
public class CarritoService {
    @Autowired 
    private CarritoRepository carritoRepository;

    @Autowired 
    private UsuarioClientRest usuarioClientRest;

    @Autowired 
    private ProductoClientRest productoClientRest;



    public Carrito obtenerOCrearCarrito(Long idUsuario) {
        return carritoRepository.findByIdUsuario(idUsuario)
                .orElseGet(() -> {
                    UsuarioDTO usuario = validarClienteExiste(idUsuario);
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setIdUsuario(usuario.getIdUsuario());
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    private UsuarioDTO validarClienteExiste(Long idCliente) {
        try {
            UsuarioDTO usuario = usuarioClientRest.obtenerDatosUsuario(idCliente);
            if (usuario == null || usuario.getIdUsuario() == null) {
                throw new RuntimeException("Cliente no encontrado");
            }
            return usuario;
        } catch (FeignException.NotFound ex) {
            throw new RuntimeException("Cliente no encontrado con ID: " + idCliente);
        }
    }

    @Transactional
    public Carrito agregarProducto(Long idUsuario, Long idProducto, Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Carrito carrito = obtenerOCrearCarrito(idUsuario);
        ProductoDTO producto = validarProducto(idProducto);

        validarStock(producto, cantidad);
        DetalleCarrito nuevoDetalle = new DetalleCarrito();
        nuevoDetalle.setIdProducto(idProducto);
        nuevoDetalle.setCantidad(cantidad);
        nuevoDetalle.setCarrito(carrito);
        carrito.getListaDetalleCarritos().add(nuevoDetalle);

        return carritoRepository.save(carrito);
    }

    public ProductoDTO validarProducto(Long idProducto) {
        try {
            ProductoDTO producto = productoClientRest.obtenerDatosProducto(idProducto);
            if (producto == null || producto.getIdProducto() == null) {
                throw new RuntimeException("Producto no encontrado con ID: " + idProducto);
            }
            return producto;
        } catch (FeignException.NotFound ex) {
            throw new RuntimeException("Producto no encontrado con ID: " + idProducto);
        }
    }

    private void validarStock(ProductoDTO producto, Integer cantidadSolicitada) {
        if (producto.getStockProducto() == null || cantidadSolicitada > producto.getStockProducto()) {
            throw new IllegalArgumentException("Stock insuficiente para el producto " + producto.getNombreProducto());
        }
    }
}
