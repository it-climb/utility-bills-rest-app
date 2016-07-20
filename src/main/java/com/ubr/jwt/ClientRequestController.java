package com.ubr.jwt;


import com.ubr.model.TokenGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class ClientRequestController {
    @RequestMapping(value = "/clientTokenCheck",
            method = RequestMethod.POST,
            produces =MediaType.ALL_VALUE//.APPLICATION_JSON_VALUE
    )
    public String getTokenFromRestClient(HttpServletRequest request) {
        String clientToken=request.getParameter("token");
        String serverToken =new String(TokenGenerator.generateRandomCharArray(32));
        if (serverToken.equals(clientToken)){
            return "tokens match";
        } else {
            return "tokens missmatch";
        }
    }
}
