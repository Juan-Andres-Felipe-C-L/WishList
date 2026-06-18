package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class CatalogResponseDTO {    
     /**
     * I.D. con el que se identifica el catálogo.
     */
    private Long id;
    /**
     * Nombre del catalógo.
     */
    private String nombre;
}
