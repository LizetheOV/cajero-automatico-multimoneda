/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.productos.administrador.cuentas.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.transport.dto
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 15:01
 * <p>
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class SaldoCuentaDTO {
  private String codigo;
  private BigDecimal saldo;
}
