/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.administrador.tasas.datasources;

import com.administrador.tasas.business.exceptions.CotizacionException;
import com.administrador.tasas.business.models.Tasa;
import com.administrador.tasas.business.repositories.TipoCambioRepository;
import com.administrador.tasas.config.ConfigurationProperties;
import com.administrador.tasas.datasources.currencylayer.TipoCambio;
import com.administrador.tasas.transport.dto.CotizacionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.datasources.currencylayer
 * <p>
 * User: LOvandoV
 * Date: 12/1/2024
 * Time: 19:34
 * <p>
 */


@Component
@AllArgsConstructor
@Log4j2
public class TipoCambioImpl implements TipoCambioRepository {

  private WebClient webClient;
  private ConfigurationProperties configurationProperties;

  @Override
  public Tasa getTipoCambio(String monedaOrigen, String monedaDestino) throws CotizacionException {

    String url = configurationProperties.getCurrencyURL() + "?access_key=" + configurationProperties.getCurrencyKEY() +
        "&currencies=" + monedaDestino + "&source=" + monedaOrigen + "&format=1";

    TipoCambio cotizacion = webClient.get()
        .uri(url)
        .retrieve()
        .bodyToMono(TipoCambio.class)
        .block();

    log.info(url, cotizacion);


    if (Objects.nonNull(cotizacion) && Objects.nonNull(cotizacion.getQuotes())) {

      Tasa tasa = new Tasa();
      tasa.setCotizacion(cotizacion.getQuotes().get(monedaOrigen+monedaDestino));
      tasa.setTimestamp(cotizacion.getTimestamp());

      return tasa;

    }

    throw new CotizacionException("El servicio de no retorno cotizaciónes");

  }

}
