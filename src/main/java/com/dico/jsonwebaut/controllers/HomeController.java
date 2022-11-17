package com.dico.jsonwebaut.controllers;

import com.dico.jsonwebaut.jwtutility.JwtUtility;
import com.dico.jsonwebaut.model.JwtRequest;
import com.dico.jsonwebaut.model.JwtResponse;
import com.dico.jsonwebaut.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String Home(){
        return "Welcome to JWT Client Complain";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate (@RequestBody JwtRequest jwtRequest) throws Exception {
      try{
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          jwtRequest.getUsername(),
                          jwtRequest.getPassword()
                  )
          );
      }catch (BadCredentialsException e){
          throw new Exception("Invalide Credentials");
      }

      final UserDetails userDetails = userDetailsService.loadUserByUsername(
              jwtRequest.getUsername());

      final String token = jwtUtility.generateToken(userDetails);

      return new JwtResponse(token);

    }

}
