package com.JAFCL.WishList.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JAFCL.WishList.dto.MessageResponseDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/wishList")
@RequiredArgsConstructor
public class WishListController {
    
    private final WishListService wishListService;

    @GetMapping("/getWishList")   
    public HttpGlobalResponse<List<ProductResponseDTO>> getWishList() {
        HttpGlobalResponse<List<ProductResponseDTO>> response = wishListService.getWishList();
        return response;
    }

    public MessageResponseDTO addProduct()
}
