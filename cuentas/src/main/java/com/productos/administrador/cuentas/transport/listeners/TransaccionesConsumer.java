
package com.productos.administrador.cuentas.transport.listeners;

import com.productos.administrador.cuentas.business.interactors.ClientesInteractor;
import com.productos.administrador.cuentas.business.interactors.CuentasInteractor;
import com.productos.administrador.cuentas.business.utils.ObjectParseUtils;
import com.productos.administrador.cuentas.transport.dto.ClienteDTO;
import com.productos.administrador.cuentas.transport.dto.SaldoCuentaDTO;
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
public class TransaccionesConsumer {

  @Autowired
  CuentasInteractor cuentasInteractor;

  @KafkaListener(topics = "saldo-updated", groupId = "cliente-group")
  public void listen(String mensaje) {

    SaldoCuentaDTO saldoCuentaDTO = ObjectParseUtils.parseObjectFromString(mensaje, SaldoCuentaDTO.class);
    cuentasInteractor.actualizarSaldo(saldoCuentaDTO);

  }


}
