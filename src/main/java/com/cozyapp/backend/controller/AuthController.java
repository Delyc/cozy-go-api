package com.cozyapp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.ReqRes;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000", "https://cozy-go.netlify.app/"})


public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    // @GetMapping("/public/users")
    // public List<OurUsers> getAllUsers(){
    //     return authService.getAllUsers();
    // }

    //get user by email
    @GetMapping("/user/{email}")
    public OurUsers getUserByEmail(@PathVariable String email){
        System.out.println("email ##########################: "+email);
        return authService.getUserByEmail(email);
               
    }
   

}
