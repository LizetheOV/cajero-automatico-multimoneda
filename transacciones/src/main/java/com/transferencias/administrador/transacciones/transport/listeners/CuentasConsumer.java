
package com.transferencias.administrador.transacciones.transport.listeners;

import com.transferencias.administrador.transacciones.business.interactors.CuentasInteractor;
import com.transferencias.administrador.transacciones.business.utils.ObjectParseUtils;
import com.transferencias.administrador.transacciones.transport.dto.CuentaDTO;
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
public class CuentasConsumer {

  @Autowired
  CuentasInteractor cuentasInteractor;

  @KafkaListener(topics = "cuenta-created", groupId = "cuenta-group")
  public void listen(String mensaje) {

    CuentaDTO cuentaDTO = ObjectParseUtils.parseObjectFromString(mensaje, CuentaDTO.class);
    cuentasInteractor.registrarCuenta(cuentaDTO);

  }


}
