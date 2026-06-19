package com.JAFCL.WishList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.JAFCL.WishList.dto.CatalogResponseDTO;
import com.JAFCL.WishList.dto.HttpGlobalResponse;
import com.JAFCL.WishList.dto.MessageResponseDTO;
import com.JAFCL.WishList.dto.ProductResponseDTO;
import com.JAFCL.WishList.dto.RegisterRequestDTO;
import com.JAFCL.WishList.dto.WishListResponseDTO;
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

    /**
     * Este método recibe una lista de I.D.s de productos en fromato JSON y le asigna esos productos a la lista
     * de deseos del usuario. También, permite que el stock de los productos de la empresa se actualice en el
     * momento que el usuario agregue un producto a su lista.
     * @param requestList= Lista en fromato JSON de los I.D.s de los productos y su cantidad.
     * @retun = Lista de advertencias que indican si se agregaron exitosamente los productos a los favoritos
     * del usuario.
     */
    public List<MessageResponseDTO> addProducts(List<RegisterRequestDTO> requestList) {
        List<MessageResponseDTO> answerList = new ArrayList<>();
        for (RegisterRequestDTO request : requestList) {
            MessageResponseDTO response = new MessageResponseDTO();
            Optional<Product> productFound = productRepository.findById(request.getId());
            
            if(productFound.isEmpty()) {
                response.setMessage("I.D. de producto que no existe.");
                answerList.add(response);
            } else {           

                Product product = productFound.get();
                
                WishList wish = new WishList();
                wish.setIdProducto(product.getId().intValue());
                wish.setNombre(product.getNombre());
                wish.setCantidad(request.getCantidad());
                wish.setPrecioTotal(request.getCantidad() * product.getPrecioUnitario());
                wishListRepository.save(wish);

                product.setStock(product.getStock() - request.getCantidad());
                productRepository.save(product);

                response.setMessage("Prodcuto con I.D. de número " + request.getId() + " ha sido añadido satisfactoriamente a la lista de deseos.");
                answerList.add(response);
            }
        }
        return answerList;
    }

    /**
     * Aquí el objeto de la clase del 'Service' accede al repositorio de WishListRepository para retornar los
     * productos que están contenidos en la lista de deseos del usuario.
     * @return = Lista de productos en la lista de deseos en formato JSON.
     */
    public HttpGlobalResponse<List<WishListResponseDTO>> getWishList() {
        HttpGlobalResponse<List<WishListResponseDTO>> response = new HttpGlobalResponse<List<WishListResponseDTO>>();
        List<WishListResponseDTO> finalList = new ArrayList<>();
        List<WishList> wishFound = wishListRepository.findAll();

        if (wishFound.isEmpty()) {
            response.setMessage("No hay productos añadidos a lista de deseos aún.");
        } else {
            for (WishList wish : wishFound) {
                WishListResponseDTO finalWish = new WishListResponseDTO();
                finalWish.setIdDeseo(wish.getId());
                finalWish.setIdProducto(wish.getIdProducto());
                finalWish.setNombre(wish.getNombre());
                finalWish.setCantidad(wish.getCantidad());
                finalWish.setPrecioTotal(wish.getPrecioTotal());
                if (!wish.isActivo()) {
                   finalWish.setActivo("ELIMINADO."); 
                } else {
                    finalWish.setActivo("Sí.");
                }
                finalList.add(finalWish);              
            }
            response.setData(finalList);
            response.setMessage("Estos son los productos añadidos a la lista de deseos.");
        }
        return response;
    }

    public HttpGlobalResponse<WishListResponseDTO> upDateProduct(Long idDeseo, int cantidad) {
        HttpGlobalResponse<WishListResponseDTO> response = new HttpGlobalResponse<WishListResponseDTO>();
        WishListResponseDTO finalWish = new WishListResponseDTO();

        Optional<WishList> wishFound = wishListRepository.findById(idDeseo);
        if (wishFound.isEmpty()) {
            response.setMessage("I.D. de producto no encontrado.");
        } else {
            WishList wish = wishFound.get();
            Optional<Product> productFound = productRepository.findById(wish.getIdProducto());        
            Product product = productFound.get();
            product.setStock(product.getStock() + wish.getCantidad());

            wish.setCantidad(cantidad);
            
            int cantidadAnterior = wish.getCantidad();
            wish.setCantidad(request.getCantidad());
            wish.setPrecioTotal(request.getCantidad() * product.getPrecioUnitario());
            wishListRepository.save(wish);

            product.setStock(product.getStock() + cantidadAnterior - request.getCantidad());
            productRepository.save(product);

            finalWish.setIdDeseo(wish.getId());
            finalWish.setIdProducto(wish.getIdProducto());
            finalWish.setNombre(wish.getNombre());
            finalWish.setCantidad(wish.getCantidad());
            finalWish.setPrecioTotal(wish.getPrecioTotal());
            if (!wish.isActivo()) {
                finalWish.setActivo("ELIMINADO."); 
                } else {
                    finalWish.setActivo("Sí.");
                }
            
            response.setData(finalWish);
            response.setMessage("Producto actualizado exitosamente.");

        }
        return response;
    }
        
}
