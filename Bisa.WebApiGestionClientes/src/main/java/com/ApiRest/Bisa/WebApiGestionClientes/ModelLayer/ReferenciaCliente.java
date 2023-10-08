package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReferenciaCliente {    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer idReferencia;
    private Integer idCliente;
    private String referencia;
    private boolean isEliminado;
    private String motivoEliminado;
}
