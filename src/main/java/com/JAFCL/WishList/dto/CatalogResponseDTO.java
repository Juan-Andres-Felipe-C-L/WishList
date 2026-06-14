package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class CatalogResponseDTO {    
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
    private double precio;
    /**
     * Cantidad de ejemplares del producto.
     */
    private int cantidad;
}
