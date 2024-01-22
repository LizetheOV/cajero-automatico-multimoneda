
package com.productos.administrador.cuentas.datasource.postgres.clientes;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.datasource.postgres.clientes
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 16:49
 * <p>
 */

public interface ClientesRepositoryFacade extends JpaRepository<ClientesProjection, Long> {

  ClientesProjection findByCodigo(String codigo);

}
