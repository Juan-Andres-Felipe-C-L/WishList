package com.JAFCL.WishList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JAFCL.WishList.dto.CatalogResponseDTO;
import com.JAFCL.WishList.dto.HttpGlobalResponse;
import com.JAFCL.WishList.dto.MessageResponseDTO;
import com.JAFCL.WishList.dto.ProductResponseDTO;
import com.JAFCL.WishList.dto.RegisterRequestDTO;
import com.JAFCL.WishList.dto.WishListResponseDTO;
import com.JAFCL.WishList.service.eCommerceService;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/e-commerce")
@RequiredArgsConstructor
public class eCommerceController {
    
    private final eCommerceService eCommerceService;

    /**
     * El método "... getCatalogs" acude al objeto 'catalogService' para que use su método que trae toda 
     * la información de los catálogos de la empresa, que están almacenados en los repositorios.
     * @return = Este pedazo de código devuelve una lista de catálogos de la empresa en formato JSON.
     */
    @GetMapping("/getCatalogs")
    public HttpGlobalResponse<List<CatalogResponseDTO>> getCatalogs() {
        HttpGlobalResponse<List<CatalogResponseDTO>> response = eCommerceService.getCatalogs();
        return response;
    }

    /**
     * '...getProducts' utiliza el objeto de la clase del servicio del catálogo para que traiga la información de 
     * los productos que pertenecen a un catálogo en específico.
     * @param idCatalogo = Es el I.D. del catálogo al que pertenecen los productos que se quieren traer. Éste se pasa
     * como parametro por medio del endpoint.
     * @return = Retorna una lista de productos que pertenecen a un catálogo específico, en formato JSON.
     */
    @GetMapping("/getProducts/{idCatalogo}")
    public HttpGlobalResponse<List<ProductResponseDTO>> getProducts(@PathVariable Long idCatalogo) {
        HttpGlobalResponse<List<ProductResponseDTO>> response = eCommerceService.getProducts(idCatalogo);
        return response;
    
    }

    /**
     * Aquí el endpoint llama al objeto del 'Service' para que el usuario pueda agregar una lista de productos
     * a su lista de deseos.
     * @param requestList = Lista de productos en formato JSON.
     * @return = Lista de advertencias que demuestran si los productos fueron agregados exitosamente.
     */
    @PostMapping("/user/addProducts")
    public List<MessageResponseDTO> addProducts(@RequestBody List<RegisterRequestDTO> requestList) {
        List<MessageResponseDTO> response = eCommerceService.addProducts(requestList);        
        return response;
    }

    /**
     * Este es el método que funciona para obtener todos los productos de la empresa que el usuario ha añadido
     * a su lista de deseos.
     * @return = Devuelve la lista de deseos en formato JSON.
     */
    @GetMapping("/user/getWishList")
    public HttpGlobalResponse<List<WishListResponseDTO>> getWishList() {
        HttpGlobalResponse<List<WishListResponseDTO>> response =  eCommerceService.getWishList();
        return response;
    }

    /**
     * Es un método que permite actualizar la cantidad de ejemplares de un producto en la lista de deseos del usuario.
     * @param id = Es el I.D. del producto que se quiere actualizar, el cual se pasa como parametro por medio del endpoint.
     * @param request = Es la información que se quiere actualizar del producto. Está en formato JSON.
     * @return = Devuelve un mensaje que demuestra si el producto fue actualizado exitosamente o no.
     */
    @PutMapping("/user/up-date-quantity-product/{idDeseo}/{cantidad}")
    public HttpGlobalResponse<WishListResponseDTO> upDateProduct(@PathVariable Long idDeseo, @PathVariable int cantidad) {
        HttpGlobalResponse<WishListResponseDTO> response = eCommerceService.upDateProduct(idDeseo, cantidad);
        return response;
    }
    
}