package com.vanduong.ims.config;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    private SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());


    public String generateToken(Authentication authentication){

        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        String roles=populateAuthorities(authorities);

        String jwt= Jwts.builder().setIssuedAt(new Date())
                .claim("email",authentication.getName())
                .claim("authorities",roles)
                .setExpiration(new Date(new Date().getTime()+600000000))
                .signWith(key)
                .compact();

        return jwt;

    }


    public String getEmailFromJwtToken(String token)
    {

        token=token.substring(7);
        Claims claims= Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();

        String email=  String.valueOf(claims.get("email"));

        return email;
    }


    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        Set<String> auths=new HashSet<>();
        for(GrantedAuthority authority:authorities)
        {
            auths.add(authority.getAuthority());
        }

        return String.join(",",auths);
    }


}
