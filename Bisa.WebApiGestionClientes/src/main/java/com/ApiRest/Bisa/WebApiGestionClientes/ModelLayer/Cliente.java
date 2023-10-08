package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

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
@Table(name = "Cliente")
public class Cliente{      
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private Integer idPersona;
    private String email;
    private Integer telefono;
    private String ocupacion;
    private String estado;    
}
