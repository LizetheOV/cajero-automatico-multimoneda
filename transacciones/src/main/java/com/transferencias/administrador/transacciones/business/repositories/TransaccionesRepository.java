
package com.transferencias.administrador.transacciones.business.repositories;

import com.transferencias.administrador.transacciones.business.models.Cuenta;
import com.transferencias.administrador.transacciones.business.models.Transaccion;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 06:51
 * <p>
 */

public interface TransaccionesRepository {

  void registrarTransaccion(Cuenta cuenta, Transaccion transaccion);

}
