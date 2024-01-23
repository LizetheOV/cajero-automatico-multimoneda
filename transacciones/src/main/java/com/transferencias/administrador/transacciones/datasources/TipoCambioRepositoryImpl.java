
package com.transferencias.administrador.transacciones.datasources;

import com.transferencias.administrador.transacciones.business.repositories.TipoCambioRepository;
import com.transferencias.administrador.transacciones.config.ConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.datasource
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 18:07
 * <p>
 */

@Component
@AllArgsConstructor
@Log4j2
public class TipoCambioRepositoryImpl implements TipoCambioRepository {

  private WebClient webClient;
  private ConfigurationProperties configurationProperties;

  @Override
  public BigDecimal getTipoCambio(String monedaOrigen, String monedaDestino) {

    String url = configurationProperties.getGatewayURL() + "/tasas/" + monedaOrigen + "/" + monedaDestino;

    Map<String, Object> tipoCambio = webClient.get()
        .uri(url)
        .retrieve()
        .bodyToMono(Map.class)
        .block();

    log.info(url, tipoCambio);


    if (Objects.nonNull(tipoCambio)) {

      return new BigDecimal(tipoCambio.get("cotizacion").toString());

    }

    return null;

  }

}
