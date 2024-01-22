/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.transferencias.administrador.transacciones.business.repositories;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 18:54
 * <p>
 */

public interface CuentasRepository {

  void registrarCuenta(String codigo, String moneda, BigDecimal saldo, String estado);

}
