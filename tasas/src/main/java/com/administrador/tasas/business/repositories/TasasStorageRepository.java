

package com.administrador.tasas.business.repositories;

import com.administrador.tasas.business.models.Tasa;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.business.repositories
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 07:13
 * <p>
 */

public interface TasasStorageRepository {

  Tasa obtenerTasa(String monedaOrigen, String monedaDestino);
  void guardarTasa(Tasa tasa, String monedaOrigen, String monedaDestino);

}
