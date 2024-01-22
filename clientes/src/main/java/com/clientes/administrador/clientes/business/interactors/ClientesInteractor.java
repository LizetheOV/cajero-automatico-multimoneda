
package com.clientes.administrador.clientes.business.interactors;

import com.clientes.administrador.clientes.business.exceptions.ClienteNoEncontradoException;
import com.clientes.administrador.clientes.business.exceptions.ClienteYaExisteException;
import com.clientes.administrador.clientes.business.models.Cliente;
import com.clientes.administrador.clientes.business.repositories.ClientesRepository;
import com.clientes.administrador.clientes.transport.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.business.interactors
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:55
 * <p>
 */


@Service
public class ClientesInteractor {

  private ClientesRepository clientesRepository;

  public ClientesInteractor(ClientesRepository clientesRepository) {
    this.clientesRepository = clientesRepository;
  }

  public ClienteDTO obtenerCliente(UUID codigo) throws ClienteNoEncontradoException {

    Cliente cliente = clientesRepository.obtenerCliente(codigo);

    ClienteDTO clienteDTO = new ClienteDTO();
    clienteDTO.setId(cliente.getCodigo());
    clienteDTO.setCi(cliente.getCi());
    clienteDTO.setEmail(cliente.getEmail());
    clienteDTO.setNombre(cliente.getNombre());
    clienteDTO.setApellido(cliente.getApellido());
    clienteDTO.setTelefono(cliente.getTelefono());

    return clienteDTO;

  }

  public ClienteDTO guardarCliente(ClienteDTO clienteDTO) throws ClienteYaExisteException {

    Cliente cliente = new Cliente(
        UUID.randomUUID(),
        clienteDTO.getCi(),
        clienteDTO.getEmail(),
        clienteDTO.getNombre(),
        clienteDTO.getApellido(),
        clienteDTO.getTelefono(),
        "ACTIVO"
    );

    clientesRepository.guardarCliente(cliente);
    clienteDTO.setId(cliente.getCodigo());

    return clienteDTO;

  }

}
