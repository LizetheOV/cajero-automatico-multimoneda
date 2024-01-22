

package com.clientes.administrador.clientes.datasource.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.datasource.mongodb
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 10:44
 * <p>
 */

public interface ClientesRepositoryFacade extends MongoRepository<ClientesProjection, String> {
  ClientesProjection findByCi(String ci);
  ClientesProjection findByCodigo(String codigo);

}
