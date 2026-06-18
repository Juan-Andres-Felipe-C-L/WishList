package com.JAFCL.WishList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JAFCL.WishList.dto.CatalogResponseDTO;
import com.JAFCL.WishList.dto.HttpGlobalResponse;
import com.JAFCL.WishList.dto.MessageResponseDTO;
import com.JAFCL.WishList.dto.ProductResponseDTO;
import com.JAFCL.WishList.dto.RegisterRequestDTO;
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

    @PostMapping("/user/addProducts")
    public MessageResponseDTO addProducts(@RequestBody List<RegisterRequestDTO> requestList) {
        MessageResponseDTO response = eCommerceService.addProducts(requestList);        
        return response;
    }

}