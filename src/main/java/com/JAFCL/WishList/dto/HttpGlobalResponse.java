package com.JAFCL.WishList.dto;

import lombok.Data;

@Data
public class HttpGlobalResponse<T> {

    private String message;

    private T data;    
}
