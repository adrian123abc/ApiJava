package com.ApiRest.Bisa.WebApiGestionClientes.DataAccess;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.Cliente;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.Persona;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ReferenciaCliente;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataAccessGestionCliente {
    // declaracion de variables privadas
    private final IClienteRepository _iClienteRepository;
    private final IPersonaRepository _iPersonaRepository;
    private final IReferenciaRepository _iReferenciaRepository;

    public Cliente CrearCliente(Cliente request) {
        try {
            return this._iClienteRepository.save(request);
        } catch (Exception e) {
            throw e;
        }
    }

    public Persona CrearPersona(Persona request) {
        try {
            return this._iPersonaRepository.save(request);
        } catch (Exception e) {
            throw e;
        }
    }

    public ReferenciaCliente CrearReferencia(ReferenciaCliente request) {
        try {
            return this._iReferenciaRepository.save(request);
        } catch (Exception e) {
            throw e;
        }
    }

    public java.util.List<ReferenciaCliente> ObtenerReferenciaByIdCliente() {
        try {
            return this._iReferenciaRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public Cliente ActualizarEstadoByIdCliente(String idCliente, String estado) {
        try {

            var entidad = this._iClienteRepository.findById(idCliente)
                    .orElseThrow(() -> new EntityNotFoundException("El cliente no se encuentra"));

            entidad.setEstado(estado);
            return this._iClienteRepository.save(entidad);
        } catch (Exception e) {
            throw e;
        }
    }

    public void EliminarReferenciaByIdReferencia(String idReferencia) {
        try {
            this._iReferenciaRepository.deleteById(idReferencia);
        } catch (Exception e) {
            throw e;
        }
    }

    public ReferenciaCliente ObtenerReferenciaByIdReferencia(String idReferencia) {
        try {
            return this._iReferenciaRepository.findById(idReferencia)
                    .orElseThrow(() -> new EntityNotFoundException("El cliente no se encuentra"));
        } catch (Exception e) {
            throw e;
        }
    }

    public java.util.List<Cliente> ObtenerTodosLosClientes() {
        try {
            return this._iClienteRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    public Persona ObtenerPersonaById(String idPersona) {
        try {
            return this._iPersonaRepository.findById(idPersona)
                    .orElseThrow(() -> new EntityNotFoundException("La persona no se encuentra"));
        } catch (Exception e) {
            throw e;
        }
    }
}
