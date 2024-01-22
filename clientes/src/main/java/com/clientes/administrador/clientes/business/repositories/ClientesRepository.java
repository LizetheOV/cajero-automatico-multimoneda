
package com.clientes.administrador.clientes.business.repositories;

import com.clientes.administrador.clientes.business.exceptions.ClienteNoEncontradoException;
import com.clientes.administrador.clientes.business.exceptions.ClienteYaExisteException;
import com.clientes.administrador.clientes.business.models.Cliente;

import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:36
 * <p>
 */

public interface ClientesRepository {

  Cliente obtenerCliente(UUID codigo) throws ClienteNoEncontradoException;
  void guardarCliente(Cliente cliente) throws ClienteYaExisteException;

}
