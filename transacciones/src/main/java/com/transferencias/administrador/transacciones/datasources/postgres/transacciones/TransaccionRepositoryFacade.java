
package com.transferencias.administrador.transacciones.datasources.postgres.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.datasources.postgres.transacciones
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 06:49
 * <p>
 */

public interface TransaccionRepositoryFacade extends JpaRepository<TransaccionProjection, Long> {
}
