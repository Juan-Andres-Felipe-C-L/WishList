package com.JAFCL.WishList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JAFCL.WishList.dto.MessageResponseDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.JAFCL.WishList.dto.RegisterRequestDTO;
import com.JAFCL.WishList.service.WishListService;



@RestController
@RequestMapping("/wishList")
@RequiredArgsConstructor
public class WishListController {
    
    private final WishListService wishListService;

    @PostMapping("/addProducts")
    public MessageResponseDTO addProducts(@RequestBody List<RegisterRequestDTO> requestList) {
        MessageResponseDTO response = wishListService.addProducts(requestList);        
        return response;
    }
    
}
