package com.gestionstock.Gestion.de.stock.controller;

import com.gestionstock.Gestion.de.stock.dto.auth.AuthenticationRequest;
import com.gestionstock.Gestion.de.stock.dto.auth.AuthenticationResponse;
import com.gestionstock.Gestion.de.stock.services.auth.ApplicationUserDetailsService;
import com.gestionstock.Gestion.de.stock.utils.JwtUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gestionstock.Gestion.de.stock.utils.Constants.*;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtil.generateToken(userDetails);

        //return ResponseEntity.ok(AuthenticationResponse.builder().accessToken("dummy_access_token").build());
        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
