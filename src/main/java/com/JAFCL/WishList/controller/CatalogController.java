package com.JAFCL.WishList.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JAFCL.WishList.dto.CatalogResponseDTO;
import com.JAFCL.WishList.dto.HttpGlobalResponse;
import com.JAFCL.WishList.service.CatalogService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
    
    private final CatalogService catalogService;

    @GetMapping("/getProducts")
    public HttpGlobalResponse<List<CatalogResponseDTO>> getProducts() {
        HttpGlobalResponse<List<CatalogResponseDTO>> response = catalogService.getProducts();
        return response;
    }
    
}
