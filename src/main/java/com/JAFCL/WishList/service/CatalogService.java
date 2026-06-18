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
     * El método "... getCatalogs" trae toda la información de los catálogos de la empresa, que están almacenados 
     * en los repositorios, para luego mostrarlos en el endpoint de la API-REST de la empresa.
     * @return = Este pedazo de código devuelve una lista de formato JSON con todos los catágolos actuales de la
     * empresa.
     */
    public HttpGlobalResponse<List<CatalogResponseDTO>> getCatalogs() {
        HttpGlobalResponse<List<CatalogResponseDTO>> response = new HttpGlobalResponse<List<CatalogResponseDTO>>();
        List<CatalogResponseDTO> Catalogs = new ArrayList<>();
        List<Catalog> catalogsFound = catalogRepository.findAll();
        for(Catalog catalog : catalogsFound) {
            CatalogResponseDTO finalCatalog = new CatalogResponseDTO();
            finalCatalog.setId(catalog.getId());
            finalCatalog.setNombre(catalog.getNombre());
            
            Catalogs.add(finalCatalog);
        }
        response.setData(Catalogs);
        response.setMessage("Éstos son los diferentes catálogos de la compañia Carvajal.");
        return response;
    }
    
}
