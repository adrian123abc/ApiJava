package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

import org.springframework.format.annotation.NumberFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteRequest {   
    @NotNull         
    @NumberFormat
    private Integer idPersona;
    @NotNull
    @NotBlank
    @NotEmpty
    private String email;
    @NotNull    
    @NumberFormat
    private Integer telefono;
    @NotNull
    @NotBlank
    @NotEmpty
    private String ocupacion;    
}
