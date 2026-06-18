package com.JAFCL.WishList.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.JAFCL.WishList.dto.CatalogResponseDTO;
import com.JAFCL.WishList.dto.HttpGlobalResponse;
import com.JAFCL.WishList.dto.MessageResponseDTO;
import com.JAFCL.WishList.dto.ProductResponseDTO;
import com.JAFCL.WishList.dto.RegisterRequestDTO;
import com.JAFCL.WishList.entity.Catalog;
import com.JAFCL.WishList.entity.Product;
import com.JAFCL.WishList.entity.WishList;
import com.JAFCL.WishList.repository.CatalogRepository;
import com.JAFCL.WishList.repository.ProductRepository;
import com.JAFCL.WishList.repository.WishListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class eCommerceService {

    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;

    /**
     * El método "... getCatalogs" trae toda la información de los catálogos de la empresa, que están almacenados 
     * en los repositorios, para luego mostrarlos en el endpoint de la API-REST de la empresa.
     * @return = Este pedazo de código devuelve una lista de catálogos de la empresa en formato JSON.
     */
    public HttpGlobalResponse<List<CatalogResponseDTO>> getCatalogs() {
        HttpGlobalResponse<List<CatalogResponseDTO>> response = new HttpGlobalResponse<List<CatalogResponseDTO>>();
        List<CatalogResponseDTO> catalogs = new ArrayList<>();
        List<Catalog> catalogsFound = catalogRepository.findAll();
        for(Catalog catalog : catalogsFound) {
            CatalogResponseDTO finalCatalog = new CatalogResponseDTO();
            finalCatalog.setId(catalog.getId());
            finalCatalog.setNombre(catalog.getNombre());
            
            catalogs.add(finalCatalog);
        }
        response.setData(catalogs);
        response.setMessage("Éstos son los diferentes catálogos de la compañia Carvajal.");
        return response;
    }

    /**
     * El método "...getProducts" trae la información de los productos que pertenecen a un catálogo en específico, para luego
     * mostrarlos en el endpoint de la API-REST de la empresa.
     * @param idCatalogo = Se pasa como parametro el I.D. del catálogo al que pertenecen los productos que se quieren traer.
     * @return = Retorna una lista de productos que pertenecen a un catálogo específico, en formato JSON.
     */
    public HttpGlobalResponse<List<ProductResponseDTO>> getProducts(Long idCatalogo) {
        HttpGlobalResponse<List<ProductResponseDTO>> response = new HttpGlobalResponse<List<ProductResponseDTO>>();
        List<ProductResponseDTO> products = new ArrayList<>();
        List<Product> productsFound = productRepository.findByIdCatalogo(idCatalogo);
        String categoria = "";
        if(idCatalogo == 1) {
            categoria = "computadores";
        } else if(idCatalogo == 2) {
            categoria = "impresoras";
        } else if(idCatalogo == 3) {
            categoria = "celulares";
        }
        for(Product product : productsFound) {
            ProductResponseDTO finalProduct = new ProductResponseDTO();
            finalProduct.setId(product.getId());
            finalProduct.setIdCatalogo(product.getIdCatalogo());
            finalProduct.setNombre(product.getNombre());
            finalProduct.setStock(product.getStock());
            finalProduct.setPrecioUnitario(product.getPrecioUnitario());
            products.add(finalProduct);
        }
        response.setData(products);
        response.setMessage("Éstos son los diferentes productos del catálogo de " + categoria + " de la compañia Carvajal.");
        return response;
    }

    public MessageResponseDTO addProducts(List<RegisterRequestDTO> requestList) {
        MessageResponseDTO response = new MessageResponseDTO();
        for (RegisterRequestDTO request : requestList) {
            WishList wish = new WishList();
            wish.setId(request.getId());
        }
    }
        
}
