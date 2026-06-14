package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    /**
     * I.D. que identifica al producto.
     */
    private Long id;
    /**
     * Número de ejemplares a comprar del producto.
     */
    private int cantidad;
}   
