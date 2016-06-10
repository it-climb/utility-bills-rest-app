package com.ubr.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.ubr.model.Admin;
import com.ubr.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.security.util.Password;

@RestController
@RequestMapping("/user")
public class  UserController {

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
        throws ServletException {
        List<Admin> list = adminRepository.findByName(login.name);
        Admin admin = null;
        try {
            admin = list.get(0);
            if(!admin.getPassword().equals(login.password)){
                throw new Exception();
            }

        } catch (Exception e){
            throw new ServletException("Invalid login or password");

        }
        LoginResponse loginResponse =  new LoginResponse(Jwts.builder().setSubject(login.name)
            .claim("roles", admin.getRole()).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").setHeaderParam("typ", "JWT").compact());
        return loginResponse;
    }

    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}

