package com.tresit.gateway.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para la solicitud de login
 */
public record LoginRequest(
    @NotBlank(message = "El username no puede estar vacío")
    String username,

    @NotBlank(message = "El password no puede estar vacío")
    String password
) {}
