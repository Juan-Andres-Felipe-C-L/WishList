package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    /**
     * I.D. con el que se identifica el producto.
     */
    private Long id;
    /**
     * Nombre del producto.
     */
    private String nombre;
    /**
     * El precio total de todos los ejemplares del producto a llevar.
     */
    private double precioTotal;
    /**
     * Cantidad de ejemplares del producto.
     */
    private int cantidad;
    /**
     * Estado del producto en la wishlist.
     */
    private boolean pedido;
}
