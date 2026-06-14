package com.JAFCL.WishList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "WishList")
@Data
public class WishList {
    
    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio_total")
    private double precioTotal;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "pedido")
    private boolean pedido;
}
