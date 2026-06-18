package com.JAFCL.WishList.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.JAFCL.WishList.dto.CatalogResponseDTO;
import com.JAFCL.WishList.dto.HttpGlobalResponse;
import com.JAFCL.WishList.entity.Catalog;
import com.JAFCL.WishList.repository.CatalogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;
    /**
     * El método "... getProducts" trae toda la información de los productos de la empresa, que están almacenados 
     * en los repositorios, para luego mostrarlos en el endpoint del catálogo de la API-REST de la empresa.
     * @return = Este pedazo de código devuelve una lista de formato JSON con todos los productos actuales de la
     * empresa.
     */
    public HttpGlobalResponse<List<CatalogResponseDTO>> getProducts() {
        HttpGlobalResponse<List<CatalogResponseDTO>> response = new HttpGlobalResponse<List<CatalogResponseDTO>>();
        List<CatalogResponseDTO> catalog = new ArrayList<>();
        List<Catalog> productsFound = catalogRepository.findAll();
        for(Catalog product : productsFound) {
            CatalogResponseDTO finalProduct = new CatalogResponseDTO();
            finalProduct.setId(product.getId());
            finalProduct.setNombre(product.getNombre());
            finalProduct.setPrecio(product.getPrecio());
            finalProduct.setCantidad(product.getCantidad());
            catalog.add(finalProduct);
        }
        response.setData(catalog);
        response.setMessage("Éste es el catálogo de productos de la compañia Carvajal.");
        return response;
    }
    
}
