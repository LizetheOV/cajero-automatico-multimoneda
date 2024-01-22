
package com.clientes.administrador.clientes.transport.rest;

import com.clientes.administrador.clientes.business.exceptions.ClienteNoEncontradoException;
import com.clientes.administrador.clientes.business.exceptions.ClienteYaExisteException;
import com.clientes.administrador.clientes.business.interactors.ClientesInteractor;
import com.clientes.administrador.clientes.transport.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.transport.rest
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:28
 * <p>
 */


@Service
public class ClientesApiRest implements ClientesApiDelegate {

  @Autowired
  ClientesInteractor clientesInteractor;

  @Override
  public ResponseEntity crearCliente(ClienteDTO clienteDTO) {
    try {
      return ResponseEntity.ok(clientesInteractor.guardarCliente(clienteDTO));
    } catch (ClienteYaExisteException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", e.getMessage()));
    }
  }

  @Override
  public ResponseEntity obtenerCliente(UUID id) {
    try {
      return ResponseEntity.ok(clientesInteractor.obtenerCliente(id));
    } catch (ClienteNoEncontradoException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
    }
  }

}
