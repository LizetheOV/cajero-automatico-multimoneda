
package com.transferencias.administrador.transacciones.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.models
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:14
 * <p>
 */

@Data
@AllArgsConstructor
public class CuentaDTO {
  private String codigo;
  private BigDecimal saldo;
  private String moneda;
  private String estado;

}
