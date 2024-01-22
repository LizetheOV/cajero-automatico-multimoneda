
package com.productos.administrador.cuentas.transport.listeners;

import com.productos.administrador.cuentas.business.interactors.ClientesInteractor;
import com.productos.administrador.cuentas.business.utils.ObjectParseUtils;
import com.productos.administrador.cuentas.transport.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.transport.listeners
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 12:15
 * <p>
 */

@Component
public class ClientesConsumer {

  @Autowired
  ClientesInteractor clientesInteractor;

  @KafkaListener(topics = "client-created", groupId = "cliente-group")
  public void listen(String mensaje) {

    ClienteDTO clienteDTO = ObjectParseUtils.parseObjectFromString(mensaje, ClienteDTO.class);
    clientesInteractor.registrarCliente(clienteDTO);

  }


}
