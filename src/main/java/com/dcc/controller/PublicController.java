package com.dcc.controller;

import com.dcc.entity.User;
import com.dcc.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

//    @PostMapping("/signup")
//    public ResponseEntity<?> createUser(@RequestBody User user){
//        boolean ok = userService.saveNewUser(user);
//        if(ok) return new ResponseEntity(HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.CONFLICT);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
//            System.out.println(jwt);
//            return new ResponseEntity<>(Map.of("token",jwt), HttpStatus.OK);
            return new ResponseEntity<>(Map.of(
                    "message", "Login successful",
                    "token", jwt
            ), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            log.error("Exception occured while creating authenticaton token");
            return new ResponseEntity<>(Map.of("message","username or password is incorrect"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }

}
