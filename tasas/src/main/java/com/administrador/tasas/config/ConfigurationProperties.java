/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.administrador.tasas.config;

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

  @Value("${connections.currencies.url: Error al leer el valor connections.currencies.url del archivo de application.yml }")
  private String currencyURL;

  @Value("${connections.currencies.key: Error al leer el valor connections.currencies.key del archivo de application.yml }")
  private String currencyKEY;

}
