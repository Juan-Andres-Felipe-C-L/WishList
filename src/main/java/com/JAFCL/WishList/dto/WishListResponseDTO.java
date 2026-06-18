package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class WishListResponseDTO {
    /**
     * I.D. del producto anexado a la lista de deseos.
     */
    private int idProducto;
    /**
     * Nombre del producto.
     */
    private String nombre;
    /**
     * Cantidad de ejemplares del prodcuto.
     */
    private int cantidad;
    /**
     * Suma total del valor de los ejemplares.
     */
    private double precioTotal;
    /**
     * Producto no eliminado en lista de deseos.
     */
    private boolean activo;
}
