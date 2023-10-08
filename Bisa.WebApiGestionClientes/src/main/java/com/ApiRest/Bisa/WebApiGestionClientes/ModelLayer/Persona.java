package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona")
public class Persona{  
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;
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
