package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    /**
     * I.D. con el que se identifica el producto.
     */
    private Long id;
    /**
     * I.D. del catálogo al que pertenece el producto.
     */
    private Long idCatalogo;
    /**
     * Nombre del producto.
     */
    private String nombre;
    /**
     * Número de unidades en stock.
     */
    private int stock;
    /**
     * El precio de un ejemplar del producto.
     */
    private double precioUnitario;
}
