
package com.productos.administrador.cuentas.business.repositories;

import java.math.BigDecimal;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:57
 * <p>
 */

public interface TipoCambioRepository {

  BigDecimal getTipoCambio(String monedaOrigen, String monedaDestino);

}
