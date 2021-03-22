package com.example.apoteka.jwtAuthentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// servis koji nam sluzi da vrati detalje o korisniku iz baze
// na osnovu kojih validiramo JWT
@Service
public class JwtUserDetailsService implements UserDetailsService {

    // ovde bismo vrsili proveru sa korisnicima iz baze
    // trenutne vrednosti su hardkodirane zarad lakse ilustracije
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("camil".equals(username)) {
            return new User("camil", "$2a$10$N4KNlIV52WLJdDjFwVSV6.Ff40CTw5y3yh7GatpEAgvNe2gR.xic.",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
