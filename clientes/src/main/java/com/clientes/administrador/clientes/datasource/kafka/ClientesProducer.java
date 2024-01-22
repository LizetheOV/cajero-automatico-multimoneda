

package com.clientes.administrador.clientes.datasource.kafka;

import com.clientes.administrador.clientes.business.models.Cliente;
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
public class ClientesProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {

    kafkaTemplate.send(topic, message);

  }

}
