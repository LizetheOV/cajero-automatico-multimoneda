
package com.transferencias.administrador.transacciones.business.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.business.models
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 06:55
 * <p>
 */

@Data
@AllArgsConstructor
public class Transaccion {

  private String codigo;
  private String tipo;
  private BigDecimal saldoInicial;
  private BigDecimal montoRecibido;
  private String moneda;
  private BigDecimal cotizacion;
  private BigDecimal montoTransaccion;

}
