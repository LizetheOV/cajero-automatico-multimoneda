/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.administrador.tasas.business.interactors;

import com.administrador.tasas.business.exceptions.CotizacionException;
import com.administrador.tasas.business.models.Tasa;
import com.administrador.tasas.business.repositories.TasasStorageRepository;
import com.administrador.tasas.business.repositories.TipoCambioRepository;
import com.administrador.tasas.business.utils.DateUtils;
import com.administrador.tasas.transport.dto.CotizacionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.business.interactors
 * <p>
 * User: LOvandoV
 * Date: 19/1/2024
 * Time: 19:26
 * <p>
 */


@Slf4j
@Service
public class TasasInteractor {

  private TipoCambioRepository tipoCambioRepository;
  private TasasStorageRepository storageRepository;

  public TasasInteractor(TipoCambioRepository tipoCambioRepository, TasasStorageRepository storageRepository) {
    this.tipoCambioRepository = tipoCambioRepository;
    this.storageRepository = storageRepository;
  }

  public CotizacionDTO getTipoCambio(String monedaOrigen, String monedaDestino) throws CotizacionException {

    if (monedaDestino.equals(monedaOrigen)) {

      throw new CotizacionException(
          String.format(
              "La moneda origen %s no puede ser la misma que la moneda destino %s",
              monedaOrigen,
              monedaDestino
          )
      );

    }

    Tasa tasa = storageRepository.obtenerTasa(monedaOrigen, monedaDestino);

    if (Objects.isNull(tasa)) {

      log.info("No se encontraron tasas almacenadas para el dia de hoy");
      tasa = tipoCambioRepository.getTipoCambio(monedaOrigen, monedaDestino);
      storageRepository.guardarTasa(tasa, monedaOrigen, monedaDestino);

    }

    CotizacionDTO response = new CotizacionDTO();
    response.setOrigen(monedaOrigen);
    response.setDestino(monedaDestino);
    response.setCotizacion(tasa.getCotizacion());
    response.setFechaHora(DateUtils.getFormattedDate(tasa.getTimestamp()));

    return response;

  }

}
