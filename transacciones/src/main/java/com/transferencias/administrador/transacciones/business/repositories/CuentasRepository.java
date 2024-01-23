
package com.transferencias.administrador.transacciones.business.repositories;

import com.transferencias.administrador.transacciones.business.exceptions.CuentaNoEncontradaException;
import com.transferencias.administrador.transacciones.business.models.Cuenta;

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

  void registrarCuenta(Cuenta cuenta);

  Cuenta obtenerCuenta(String codigo) throws CuentaNoEncontradaException;

  void actualizarSaldo(Cuenta cuenta);

}
