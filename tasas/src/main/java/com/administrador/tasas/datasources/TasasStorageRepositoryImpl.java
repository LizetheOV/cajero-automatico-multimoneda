

package com.administrador.tasas.datasources;

import com.administrador.tasas.business.models.Tasa;
import com.administrador.tasas.business.repositories.TasasStorageRepository;
import com.administrador.tasas.business.utils.DateUtils;
import com.administrador.tasas.datasources.mongodb.TasasProjection;
import com.administrador.tasas.datasources.mongodb.TasasStorageRepositoryFacade;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.datasources.mongodb
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 07:20
 * <p>
 */

@Component
public class TasasStorageRepositoryImpl implements TasasStorageRepository {

  private TasasStorageRepositoryFacade tasasStorage;

  public TasasStorageRepositoryImpl(TasasStorageRepositoryFacade tasasStorage) {
    this.tasasStorage = tasasStorage;
  }

  @Override
  public Tasa obtenerTasa(String monedaOrigen, String monedaDestino) {

    TasasProjection tasasProjection = tasasStorage.findByOrigenAndDestinoAndTimestampIsBetween(
        monedaOrigen,
        monedaDestino,
        DateUtils.startOfDay(),
        DateUtils.endOfDay()
    );

    if (Objects.nonNull(tasasProjection)) {

      return new Tasa(tasasProjection.getCotizacion(), tasasProjection.getTimestamp());

    }

    return null;
  }

  @Override
  public void guardarTasa(Tasa tasa, String monedaOrigen, String monedaDestino) {

    TasasProjection tasasProjection = new TasasProjection(
        UUID.randomUUID().toString(),
        monedaOrigen,
        monedaDestino,
        tasa.getCotizacion(),
        tasa.getTimestamp(),
        new Date()
    );

    tasasStorage.save(tasasProjection);

  }

}
