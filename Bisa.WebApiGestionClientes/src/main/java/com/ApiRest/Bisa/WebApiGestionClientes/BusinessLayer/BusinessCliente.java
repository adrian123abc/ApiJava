package com.ApiRest.Bisa.WebApiGestionClientes.BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ApiRest.Bisa.WebApiGestionClientes.DataAccess.DataAccessGestionCliente;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.AccesibilidadDescripcion;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.AccesibilidadResponse;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.BisaResponse;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.Cliente;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ClienteRequest;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.Persona;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.PersonaRequest;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ReferenciaCliente;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ReferenciaClienteRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessCliente {
    private final DataAccessGestionCliente _dataAccessCliente;

    public BisaResponse CrearCliente(ClienteRequest request) {
        var response = new BisaResponse();
        try {
            var requestData = new Cliente();
            requestData.setIdPersona(request.getIdPersona());
            requestData.setEmail(request.getEmail());
            requestData.setTelefono(request.getTelefono());
            requestData.setOcupacion(request.getOcupacion());
            requestData.setEstado("CREADO");
            var responseSave = this._dataAccessCliente.CrearCliente(requestData);
            response.setId(responseSave.getIdCliente());
            response.setMensaje("cliente creado correctamente.");
            response.setExito(true);
            response.setCodigo("00");
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    public BisaResponse CrearPersona(PersonaRequest request) {
        var response = new BisaResponse();
        try {
            var requestData = new Persona();
            requestData.setNombre(request.getNombre());
            requestData.setApellidoPaterno(request.getApellidoPaterno());
            requestData.setApellidoMaterno(request.getApellidoMaterno());
            requestData.setFechaDeNacimiento(request.getFechaDeNacimiento());
            requestData.setCarnetDeIdentidad(request.getCarnetDeIdentidad());
            requestData.setUbicacionGeografica(request.getUbicacionGeografica());
            requestData.setZonaBarrioCalle(request.getZonaBarrioCalle());
            requestData.setNumeroDeDomicilio(request.getNumeroDeDomicilio());
            requestData.setReferencia(request.getReferencia());
            var responseSave = this._dataAccessCliente.CrearPersona(requestData);
            response.setId(responseSave.getIdPersona());
            response.setMensaje("cliente creado correctamente.");
            response.setExito(true);
            response.setCodigo("00");
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    public BisaResponse CrearReferencia(ReferenciaClienteRequest request) {
        var response = new BisaResponse();
        try {
            var requestData = new ReferenciaCliente();
            requestData.setIdCliente(request.getIdCliente());
            requestData.setReferencia(request.getReferencia());
            var responseSave = this._dataAccessCliente.CrearReferencia(requestData);

            var referenciasCliente = this._dataAccessCliente.ObtenerReferenciaByIdCliente();
            List<ReferenciaCliente> referenciasClienteFiltrado = referenciasCliente.stream()
                    .filter(referencia -> referencia.getIdCliente().equals(request.getIdCliente())).toList();
            if (referenciasClienteFiltrado.size() >= 1)
                this._dataAccessCliente.ActualizarEstadoByIdCliente(request.getIdCliente().toString(), "ACTIVO");
            response.setId(responseSave.getIdReferencia());
            if (responseSave.getIdReferencia() != 0) {
                response.setMensaje("referencia creada correctamente.");
                response.setExito(true);
                response.setCodigo("00");
            } else {
                response.setMensaje("referencia no se creo correctamente.");
                response.setExito(false);
                response.setCodigo("01");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    public BisaResponse EliminarReferenciaCliente(Long id) {
        var response = new BisaResponse();
        try {
            var cliente = this._dataAccessCliente.ObtenerReferenciaByIdReferencia(id.toString());
            var referenciasCliente = this._dataAccessCliente.ObtenerReferenciaByIdCliente();
            List<ReferenciaCliente> referenciasClienteFiltrado = referenciasCliente.stream()
                    .filter(referencia -> referencia.getIdCliente().equals(cliente.getIdCliente())).toList();
            if (referenciasClienteFiltrado.size() == 1)
                this._dataAccessCliente.ActualizarEstadoByIdCliente(cliente.getIdCliente().toString(), "BLOQUEADO");
            this._dataAccessCliente.EliminarReferenciaByIdReferencia(id.toString());
            response.setMensaje("referencia eliminada correctamente.");
            response.setExito(true);
            response.setCodigo("00");
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    public AccesibilidadResponse ListadoClienteByAccesibilidad() {
        var response = new AccesibilidadResponse();
        try {
            List<AccesibilidadDescripcion> accesibilidadBuena = new ArrayList<AccesibilidadDescripcion>();
            List<AccesibilidadDescripcion> accesibilidadRegular = new ArrayList<AccesibilidadDescripcion>();
            List<AccesibilidadDescripcion> accesibilidadMala = new ArrayList<AccesibilidadDescripcion>();            
            var clientes = this._dataAccessCliente.ObtenerTodosLosClientes();
            var referenciasCliente = this._dataAccessCliente.ObtenerReferenciaByIdCliente();
            for (Cliente cliente : clientes) {
                List<ReferenciaCliente> referenciasClienteFiltrado = referenciasCliente.stream()
                        .filter(referencia -> referencia.getIdCliente().equals(cliente.getIdCliente()))
                        .toList();
                Persona persona = this._dataAccessCliente.ObtenerPersonaById(cliente.getIdPersona().toString());
                if (referenciasClienteFiltrado.size() >= 2)
                {                    
                    accesibilidadBuena.add(SetearDatosClienteListado(persona, cliente));
                }
                if (referenciasClienteFiltrado.size() == 1)
                {
                    accesibilidadRegular.add(SetearDatosClienteListado(persona, cliente));
                }   
                if (referenciasClienteFiltrado.size() == 0)
                {                    
                    accesibilidadMala.add(SetearDatosClienteListado(persona, cliente));
                }   
            }
            response.setAccesibilidadBuena(accesibilidadBuena);
            response.setAccesibilidadRegular(accesibilidadRegular);
            response.setAccesibilidadMala(accesibilidadMala);
            response.setMensaje("ejecutado correctamente.");
            response.setExito(true);
            response.setCodigo("00");
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    public AccesibilidadDescripcion SetearDatosClienteListado(Persona persona, Cliente cliente) {
        try {
            AccesibilidadDescripcion accesibilidadDescripcion = new AccesibilidadDescripcion();
            accesibilidadDescripcion.setNombreCliente(persona.getNombre());
            accesibilidadDescripcion.setApellidoPaterno(persona.getApellidoPaterno());
            accesibilidadDescripcion.setApellidoMaterno(persona.getApellidoMaterno());
            accesibilidadDescripcion.setEstado(cliente.getEstado());
            return accesibilidadDescripcion;
        } catch (Exception e) {            
            throw e;
        }
    }
}
