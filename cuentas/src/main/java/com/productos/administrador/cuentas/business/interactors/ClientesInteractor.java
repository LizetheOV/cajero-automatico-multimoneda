
package com.productos.administrador.cuentas.business.interactors;

import com.productos.administrador.cuentas.business.repositories.ClientesRepository;
import com.productos.administrador.cuentas.transport.dto.ClienteDTO;
import org.springframework.stereotype.Service;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.interactors
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 16:57
 * <p>
 */

@Service
public class ClientesInteractor {

  private ClientesRepository clientesRepository;

  public ClientesInteractor(ClientesRepository clientesRepository) {
    this.clientesRepository = clientesRepository;
  }

  public void registrarCliente(ClienteDTO clienteDTO) {

    clientesRepository.registrarCliente(clienteDTO.getCodigo(), clienteDTO.getEstado());

  }

}
