package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

import java.sql.Date;
import lombok.Data;

@Data
public class PersonaRequest {    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaDeNacimiento;    
    private String carnetDeIdentidad;
    private String ubicacionGeografica;
    private String zonaBarrioCalle;
    private Integer numeroDeDomicilio;
    private String referencia;    
}
