package com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer;

import java.util.List;

import lombok.Data;

@Data
public class AccesibilidadResponse extends BisaResponse {
    private List<AccesibilidadDescripcion> accesibilidadBuena;
    private List<AccesibilidadDescripcion> accesibilidadRegular;
    private List<AccesibilidadDescripcion> accesibilidadMala;
    private List<AccesibilidadDescripcion> accesibilidadNula;
}
