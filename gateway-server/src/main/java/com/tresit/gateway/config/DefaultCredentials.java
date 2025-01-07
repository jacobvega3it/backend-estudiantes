package com.tresit.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Clase para manejar las credenciales por defecto
 */

 @Component
 @ConfigurationProperties(prefix = "jwt.default-credentials")
 public class DefaultCredentials {
     private String username;
     private String password;
 
     public String getUsername() {
         return username;
     }
 
     public void setUsername(String username) {
         this.username = username;
     }
 
     public String getPassword() {
         return password;
     }
 
     public void setPassword(String password) {
         this.password = password;
     }
 }
