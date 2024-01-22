

package com.administrador.tasas.business.repositories;

import com.administrador.tasas.business.exceptions.CotizacionException;
import com.administrador.tasas.business.models.Tasa;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 12/1/2024
 * Time: 19:32
 * <p>
 */

public interface TipoCambioRepository {
  Tasa getTipoCambio(String monedaOrigen, String monedaDestino) throws CotizacionException;

}
