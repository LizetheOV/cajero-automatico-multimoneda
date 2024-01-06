/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.clientes.administrador.clientes.transport.rest;
import com.clientes.administrador.clientes.transport.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.transport.rest
 * <p>
 * User: LOvandoV
 * Date: 6/1/2024
 * Time: 07:33
 * <p>
 */

@Service
public class ClientesApiDelegateImpl implements ClientesApiDelegate {

  @Override
  public ResponseEntity<ClienteDTO> crearCliente(ClienteDTO clienteDTO) {
    return ResponseEntity.ok(null);
  }

  @Override
  public ResponseEntity<ClienteDTO> obtenerCliente(UUID id) {
    return ResponseEntity.ok(null);
  }

}
