
package com.transferencias.administrador.transacciones.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.config
 * <p>
 * User: LOvandoV
 * Date: 12/1/2024
 * Time: 19:15
 * <p>
 */

@Configuration
@Data
public class ConfigurationProperties {

  @Value("${connections.gateway: Error al leer el valor connections.gateway del archivo de application.yml }")
  private String gatewayURL;

}
