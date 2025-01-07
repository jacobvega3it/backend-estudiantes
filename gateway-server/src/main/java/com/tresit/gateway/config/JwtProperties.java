package com.tresit.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración que mapea las propiedades JWT desde el application.yml
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    private Long expiration;
    private DefaultCredentials defaultCredentials;

    // Constructor con DefaultCredentials inyectado
    public JwtProperties(DefaultCredentials defaultCredentials) {
        this.defaultCredentials = defaultCredentials;
    }

    // Getters y setters
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public DefaultCredentials getDefaultCredentials() {
        return defaultCredentials;
    }

    public void setDefaultCredentials(DefaultCredentials defaultCredentials) {
        this.defaultCredentials = defaultCredentials;
    }
    
}
