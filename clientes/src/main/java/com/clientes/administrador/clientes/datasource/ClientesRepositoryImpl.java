
package com.clientes.administrador.clientes.datasource;

import com.clientes.administrador.clientes.business.exceptions.ClienteNoEncontradoException;
import com.clientes.administrador.clientes.business.exceptions.ClienteYaExisteException;
import com.clientes.administrador.clientes.business.models.Cliente;
import com.clientes.administrador.clientes.business.repositories.ClientesRepository;
import com.clientes.administrador.clientes.datasource.kafka.ClientesProducer;
import com.clientes.administrador.clientes.datasource.mongodb.ClienteProjection;
import com.clientes.administrador.clientes.datasource.mongodb.ClienteRepositoryFacade;
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

  private ClienteRepositoryFacade clientesRepository;
  private ClientesProducer clientesProducer;

  public ClientesRepositoryImpl(ClienteRepositoryFacade clientesRepository, ClientesProducer clientesProducer) {
    this.clientesRepository = clientesRepository;
    this.clientesProducer = clientesProducer;
  }

  @Override
  public Cliente obtenerCliente(UUID codigo) throws ClienteNoEncontradoException {

    ClienteProjection clienteProjection = clientesRepository.findByCodigo(codigo.toString());

    if (Objects.isNull(clienteProjection)) {

      throw new ClienteNoEncontradoException(
          String.format("No se encontro registro de cliente con identificación: %s", codigo)
      );

    }

    return new Cliente(
        codigo,
        clienteProjection.getCi(),
        clienteProjection.getEmail(),
        clienteProjection.getNombre(),
        clienteProjection.getApellido(),
        clienteProjection.getTelefono(),
        clienteProjection.getEstado()
    );
  }

  @Override
  public void guardarCliente(Cliente cliente) throws ClienteYaExisteException {

    ClienteProjection clienteProjection = clientesRepository.findByCi(cliente.getCi());

    if (Objects.nonNull(clienteProjection)) {

      throw new ClienteYaExisteException(
          String.format("Ya existe un cliente registrado con el ci: %s", cliente.getCi())
      );

    }

    ClienteProjection projection = new ClienteProjection(
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
    clientesProducer.sendMessage(
        "client-created",
        cliente.toString()
    );

  }

}
