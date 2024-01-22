
package com.transferencias.administrador.transacciones.datasources.postgres.cuentas;

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

public interface CuentasRepositoryFacade extends JpaRepository<CuentasProjection, Long> {

  CuentasProjection findByCodigo(String codigo);

}
