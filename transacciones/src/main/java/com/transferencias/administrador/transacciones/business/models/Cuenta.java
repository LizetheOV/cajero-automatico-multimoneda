
package com.transferencias.administrador.transacciones.business.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.business.models
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 13:27
 * <p>
 */

@Data
@AllArgsConstructor
public class Cuenta {
  private String codigo;
  private BigDecimal saldo;
  private String moneda;
  private String estado;

  @Override
  public String toString() {
    return "{'codigo': '" + codigo + "', 'saldo': " + saldo + "}";
  }


}

