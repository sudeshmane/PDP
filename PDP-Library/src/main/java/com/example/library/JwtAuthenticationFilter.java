package com.example.library;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtHandlerAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

   // @Autowired
   // private TokenProvider jwtTokenUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("authorization");
        String username = null;
        String authToken = null; 
        String TOKEN_PREFIX = "Bearer "; 
        
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
            	
               // username = jwtTokenUtil.getUsernameFromToken(authToken);
            	// System.out.println("in OncePerREquestFilter "+header+" "+username+" "+authToken);
            	// Jwts.parser().setSigningKey("my-secret-key").parseClaimsJws(authToken).getBody().getSubject()
            	Claims sub = Jwts.parser().setSigningKey(getPublicKeyFromString(OAuth2Config.dummyPublicKey)).parseClaimsJws(authToken).getBody(); 
            	
            	//Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(OAuth2Config.publicKey)).parse(authToken, new JwtHandlerAdapter<>());
            	System.out.println((String)sub.get("user_name"));
            	UserDetails userdetail = userDetailsService.loadUserByUsername((String)sub.get("user_name"));
            	// UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                // authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            	//SecurityContextHolder.getContext().setAuthentication(userdetail.getAuthorities().);
            	 final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            			//(String)sub.get("user_name"),
            			//(String)sub.get("password")
            			userdetail.getUsername(),
            			userdetail.getPassword()
                ));
            	 
            	 SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (IllegalArgumentException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | GeneralSecurityException e) {
                logger.error("an error occured during getting username from token", e);
            } 
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

/*            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtToke.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            */
        }
        
        
       chain.doFilter(req, res);
    }
    
    public static RSAPublicKey getPublicKeyFromString(String key) throws 
    IOException, GeneralSecurityException {

    String publicKeyPEM = key;

    /**replace headers and footers of cert, if RSA PUBLIC KEY in your case, change accordingly*/
    publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
    publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");

    byte[] encoded = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(publicKeyPEM);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));

    return pubKey;
    }
}