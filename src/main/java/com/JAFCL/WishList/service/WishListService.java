package com.JAFCL.WishList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.JAFCL.WishList.dto.MessageResponseDTO;
import com.JAFCL.WishList.dto.RegisterRequestDTO;
import com.JAFCL.WishList.entity.WishList;
import com.JAFCL.WishList.repository.ProductRepository;
import com.JAFCL.WishList.repository.WishListRepository;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    
    public MessageResponseDTO addProducts(List<RegisterRequestDTO> requestList) {
        MessageResponseDTO response = new MessageResponseDTO();
        for (RegisterRequestDTO request : requestList) {
            WishList wish = new WishList();
            wish.setId(request.getId());
        }
    }
}
