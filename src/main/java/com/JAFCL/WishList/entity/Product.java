package com.JAFCL.WishList.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    private Long id;

    @Column(name = "id_catalogo")
    private int idCatalogo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio_unitario")
    private double precioUnitario;    
}
