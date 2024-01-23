

package com.transferencias.administrador.transacciones.datasources.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.clientes.administrador.clientes.datasource.kafka
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 11:58
 * <p>
 */

@Component
public class KafkaProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {

    kafkaTemplate.send(topic, message);

  }

}
