package com.batata.authentication.controller;

import com.batata.authentication.model.entity.User;
import com.batata.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller to expose a test endpoint (/username)  and endpoint to validate token ( Principal oauth)
 * o endpoint /usuario serve para validar o token recebido
 */
@RestController
@RequestMapping(path = "user")
public class UsuarioController {

    private UserService usuarioService;

    @Autowired
    public UsuarioController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = "/{username}")
    public User getUser(@PathVariable("username") String userName){
        return usuarioService.get(userName);
    }

    //this should be worked to not expose all info..but meh :P
    @RequestMapping( method = {RequestMethod.POST, RequestMethod.GET} )
    public Principal oauth(Principal principal) {
        return principal;
    }
}
