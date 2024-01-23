
package com.productos.administrador.cuentas.business.models;

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
public class Cuenta {
  private String codigo;
  private String clienteID;
  private BigDecimal saldo;
  private String moneda;
  private String estado;

  @Override
  public String toString() {
    return "{'codigo': '" + codigo +
        "', 'saldo': " + saldo +
        ", 'moneda': '" + moneda +
        "', 'estado': '" + estado + "'}";
  }

}
