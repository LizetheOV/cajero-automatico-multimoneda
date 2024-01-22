
package com.productos.administrador.cuentas.datasource;

import com.productos.administrador.cuentas.business.repositories.ClientesRepository;
import com.productos.administrador.cuentas.datasource.postgres.clientes.ClientesProjection;
import com.productos.administrador.cuentas.datasource.postgres.clientes.ClientesRepositoryFacade;
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

  private ClientesRepositoryFacade clientesRepositoryFacade;

  public ClientesRepositoryImpl(ClientesRepositoryFacade clientesRepositoryFacade) {
    this.clientesRepositoryFacade = clientesRepositoryFacade;
  }

  @Override
  public void registrarCliente(String codigo, String estado) {

    ClientesProjection clientesProjection = new ClientesProjection();
    clientesProjection.setCodigo(codigo);
    clientesProjection.setEstado(estado);

    clientesRepositoryFacade.save(clientesProjection);

  }

  @Override
  public boolean existeCliente(String codigo) {

    ClientesProjection clientesProjection = clientesRepositoryFacade.findByCodigo(codigo);

    if (Objects.nonNull(clientesProjection)) {

      return true;

    }

    return false;

  }

}
