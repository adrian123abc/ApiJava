package com.ApiRest.Bisa.WebApiGestionClientes.ControllerLayer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiRest.Bisa.WebApiGestionClientes.BusinessLayer.BusinessCliente;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.AccesibilidadResponse;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.BisaResponse;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ClienteRequest;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.PersonaRequest;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ReferenciaClienteRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class ClienteController {
    // declaracion de variables privadas;
    private final BusinessCliente _businessCliente;

    @PostMapping("cliente/crear")
    public ResponseEntity<BisaResponse> CrearCliente(@Valid @RequestBody ClienteRequest request) {
        var response = new BisaResponse();
        try {
            response = _businessCliente.CrearCliente(request);
        } catch (Exception ex) {
            response.setMensaje("Ocurrio un error Inesperado: " + ex.getMessage());
            response.setCodigo("500");
            response.setExito(false);
            return new ResponseEntity<BisaResponse>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("persona/crear")
    public ResponseEntity<BisaResponse> CrearPersona(@RequestBody PersonaRequest request) {
        var response = new BisaResponse();
        try {
            response = _businessCliente.CrearPersona(request);
        } catch (Exception ex) {
            response.setMensaje("Ocurrio un error Inesperado: " + ex.getMessage());
            response.setCodigo("500");
            response.setExito(false);
            return new ResponseEntity<BisaResponse>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("cliente/referencia/crear")
    public ResponseEntity<BisaResponse> CrearReferenciaCliente(@RequestBody ReferenciaClienteRequest request) {
        var response = new BisaResponse();
        try {
            response = _businessCliente.CrearReferencia(request);
        } catch (Exception ex) {
            response.setMensaje("Ocurrio un error Inesperado: " + ex.getMessage());
            response.setCodigo("500");
            response.setExito(false);
            return new ResponseEntity<BisaResponse>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("cliente/referencia/eliminar/{id}")
    public ResponseEntity<BisaResponse> EliminarReferenciaCliente(@PathVariable Long id) {
        var response = new BisaResponse();
        try {
            response = _businessCliente.EliminarReferenciaCliente(id);
        } catch (Exception ex) {
            response.setMensaje("Ocurrio un error Inesperado: " + ex.getMessage());
            response.setCodigo("500");
            response.setExito(false);
            return new ResponseEntity<BisaResponse>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("cliente/listado/accesibilidad")
    public ResponseEntity<AccesibilidadResponse> ListadoClienteByAccesibilidad() {
        var response = new AccesibilidadResponse();
        try {
            response = _businessCliente.ListadoClienteByAccesibilidad();
        } catch (Exception ex) {
            response.setMensaje("Ocurrio un error Inesperado: " + ex.getMessage());
            response.setCodigo("500");
            response.setExito(false);
            return new ResponseEntity<AccesibilidadResponse>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(response);
    }
}
