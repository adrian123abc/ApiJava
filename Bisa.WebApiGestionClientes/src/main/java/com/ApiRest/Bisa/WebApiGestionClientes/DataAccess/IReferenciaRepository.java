package com.ApiRest.Bisa.WebApiGestionClientes.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ApiRest.Bisa.WebApiGestionClientes.ModelLayer.ReferenciaCliente;

@Repository
public interface IReferenciaRepository extends JpaRepository<ReferenciaCliente, String> {
}

