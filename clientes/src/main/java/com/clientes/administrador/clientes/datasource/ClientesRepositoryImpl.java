
package com.clientes.administrador.clientes.datasource;

import com.clientes.administrador.clientes.business.exceptions.ClienteNoEncontradoException;
import com.clientes.administrador.clientes.business.exceptions.ClienteYaExisteException;
import com.clientes.administrador.clientes.business.models.Cliente;
import com.clientes.administrador.clientes.business.repositories.ClientesRepository;
import com.clientes.administrador.clientes.datasource.mongodb.ClientesProjection;
import com.clientes.administrador.clientes.datasource.mongodb.ClientesRepositoryFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.datasource
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:38
 * <p>
 */

@Component
@Slf4j
public class ClientesRepositoryImpl implements ClientesRepository {

  private ClientesRepositoryFacade clientesRepository;

  public ClientesRepositoryImpl(ClientesRepositoryFacade clientesRepository) {
    this.clientesRepository = clientesRepository;
  }

  @Override
  public Cliente obtenerCliente(UUID codigo) throws ClienteNoEncontradoException {

    ClientesProjection clientesProjection = clientesRepository.findByCodigo(codigo.toString());

    if (Objects.isNull(clientesProjection)) {

      throw new ClienteNoEncontradoException(
          String.format("No se encontro registro de cliente con identificación: %s", codigo)
      );

    }

    return new Cliente(
        codigo,
        clientesProjection.getCi(),
        clientesProjection.getEmail(),
        clientesProjection.getNombre(),
        clientesProjection.getApellido(),
        clientesProjection.getTelefono(),
        clientesProjection.getEstado()
    );
  }

  @Override
  public void guardarCliente(Cliente cliente) throws ClienteYaExisteException {

    ClientesProjection clientesProjection = clientesRepository.findByCi(cliente.getCi());

    if (Objects.nonNull(clientesProjection)) {

      throw new ClienteYaExisteException(
          String.format("Ya existe un cliente registrado con el ci: %s", cliente.getCi())
      );

    }

    ClientesProjection projection = new ClientesProjection(
        UUID.randomUUID().toString(),
        cliente.getCodigo().toString(),
        cliente.getCi(),
        cliente.getEmail(),
        cliente.getNombre(),
        cliente.getApellido(),
        cliente.getTelefono(),
        new Date(),
        cliente.getEstado()
    );

    clientesRepository.save(projection);

  }

}
