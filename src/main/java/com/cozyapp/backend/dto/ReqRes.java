package com.cozyapp.backend.dto;

import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.entity.Product;
import com.cozyapp.backend.entity.Wishlist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String role;
    private String profilePictureUrl;
    private String fullname;
    private String companyName;
    private String tiktok;
    private String youtube;
    private String insta;
    private String phone;
    private String password;
    private List<Product> products;
    private OurUsers ourUsers;
    //add wishlist 
    private List<Wishlist> wishlists;


}
