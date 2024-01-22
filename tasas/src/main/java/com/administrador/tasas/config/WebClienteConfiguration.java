/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.administrador.tasas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.config
 * <p>
 * User: LOvandoV
 * Date: 12/1/2024
 * Time: 19:29
 * <p>
 */

@Configuration
public class WebClienteConfiguration {


  @Bean
  public WebClient webClient() {

    return WebClient.builder().build();

  }

}
