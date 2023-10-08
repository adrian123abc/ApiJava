package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

import lombok.Data;

@Data
public class BisaResponse {
    private int id;
    private boolean exito; 
    private String codigo; 
    private String mensaje; 
}
