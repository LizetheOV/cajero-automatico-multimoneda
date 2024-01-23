
package com.transferencias.administrador.transacciones.datasources.postgres.transacciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.datasources.postgres.transacciones
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 06:05
 * <p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transacciones")
public class TransaccionProjection {
}
