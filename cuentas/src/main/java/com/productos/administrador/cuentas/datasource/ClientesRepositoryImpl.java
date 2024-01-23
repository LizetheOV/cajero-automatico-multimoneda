
package com.productos.administrador.cuentas.datasource;

import com.productos.administrador.cuentas.business.repositories.ClientesRepository;
import com.productos.administrador.cuentas.datasource.postgres.clientes.ClienteProjection;
import com.productos.administrador.cuentas.datasource.postgres.clientes.ClienteRepositoryFacade;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.datasource.postgres.clientes
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 16:50
 * <p>
 */

@Component
public class ClientesRepositoryImpl implements ClientesRepository {

  private ClienteRepositoryFacade clienteRepositoryFacade;

  public ClientesRepositoryImpl(ClienteRepositoryFacade clienteRepositoryFacade) {
    this.clienteRepositoryFacade = clienteRepositoryFacade;
  }

  @Override
  public void registrarCliente(String codigo, String estado) {

    ClienteProjection clienteProjection = new ClienteProjection();
    clienteProjection.setCodigo(codigo);
    clienteProjection.setEstado(estado);

    clienteRepositoryFacade.save(clienteProjection);

  }

  @Override
  public boolean existeCliente(String codigo) {

    ClienteProjection clienteProjection = clienteRepositoryFacade.findByCodigo(codigo);

    if (Objects.nonNull(clienteProjection)) {

      return true;

    }

    return false;

  }

}
