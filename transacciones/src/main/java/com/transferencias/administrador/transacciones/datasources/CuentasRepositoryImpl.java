
package com.transferencias.administrador.transacciones.datasources;

import com.transferencias.administrador.transacciones.business.exceptions.CuentaNoEncontradaException;
import com.transferencias.administrador.transacciones.business.models.Cuenta;
import com.transferencias.administrador.transacciones.business.repositories.CuentasRepository;
import com.transferencias.administrador.transacciones.datasources.kafka.KafkaProducer;
import com.transferencias.administrador.transacciones.datasources.postgres.cuentas.CuentaProjection;
import com.transferencias.administrador.transacciones.datasources.postgres.cuentas.CuentaRepositoryFacade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.datasources.postgres
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 18:57
 * <p>
 */

@Component
public class CuentasRepositoryImpl implements CuentasRepository {

  private CuentaRepositoryFacade cuentaRepositoryFacade;
  private KafkaProducer kafkaProducer;

  public CuentasRepositoryImpl(CuentaRepositoryFacade cuentaRepositoryFacade, KafkaProducer kafkaProducer) {
    this.cuentaRepositoryFacade = cuentaRepositoryFacade;
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public void registrarCuenta(Cuenta cuenta) {

    CuentaProjection cuentaProjection = new CuentaProjection();
    cuentaProjection.setCodigo(cuenta.getCodigo());
    cuentaProjection.setMoneda(cuenta.getMoneda());
    cuentaProjection.setSaldo(cuenta.getSaldo());
    cuentaProjection.setEstado(cuenta.getEstado());

    cuentaRepositoryFacade.save(cuentaProjection);

  }

  @Override
  public Cuenta obtenerCuenta(String codigo) throws CuentaNoEncontradaException {

    CuentaProjection cuentaProjection = cuentaRepositoryFacade.findByCodigo(codigo);

    if (Objects.isNull(cuentaProjection)) {

      throw new CuentaNoEncontradaException(
          String.format("No se encontro una cuenta con el codigo: %s", codigo)
      );

    }

    return new Cuenta(
        codigo,
        cuentaProjection.getSaldo(),
        cuentaProjection.getMoneda(),
        cuentaProjection.getEstado()
    );
  }

  @Override
  public void actualizarSaldo(Cuenta cuenta) {

    CuentaProjection cuentaProjection = cuentaRepositoryFacade.findByCodigo(cuenta.getCodigo());
    cuentaProjection.setSaldo(cuenta.getSaldo());
    cuentaRepositoryFacade.save(cuentaProjection);
    kafkaProducer.sendMessage(
        "saldo-updated",
        cuenta.toString()
    );

  }

}
