

package com.productos.administrador.cuentas.business.repositories;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.utils.repositories
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 16:51
 * <p>
 */

public interface ClientesRepository {

  void registrarCliente(String codigo, String estado);
  boolean existeCliente(String codigo);

}
