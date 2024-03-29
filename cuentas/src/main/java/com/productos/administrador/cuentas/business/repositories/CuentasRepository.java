
package com.productos.administrador.cuentas.business.repositories;

import com.productos.administrador.cuentas.business.exceptions.CuentaNoEncontradaException;
import com.productos.administrador.cuentas.business.models.Cuenta;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:14
 * <p>
 */

public interface CuentasRepository {

  void guardarCuenta(Cuenta cuenta);

  Cuenta obtenerCuenta(String codigo) throws CuentaNoEncontradaException;

  void actualizarSaldo(String codigo, BigDecimal saldo);

}
