
package com.productos.administrador.cuentas.datasource;

import com.productos.administrador.cuentas.business.exceptions.CuentaNoEncontradaException;
import com.productos.administrador.cuentas.business.models.Cuenta;
import com.productos.administrador.cuentas.business.repositories.CuentasRepository;
import com.productos.administrador.cuentas.datasource.kafka.CuentasProducer;
import com.productos.administrador.cuentas.datasource.postgres.cuentas.CuentasProjection;
import com.productos.administrador.cuentas.datasource.postgres.cuentas.CuentasRepositoryFacade;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.datasource
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:13
 * <p>
 */

@Component
public class CuentasRepositoryImpl implements CuentasRepository {

  private CuentasRepositoryFacade cuentasRepositoryFacade;
  private CuentasProducer cuentasProducer;

  public CuentasRepositoryImpl(CuentasRepositoryFacade cuentasRepositoryFacade, CuentasProducer cuentasProducer) {
    this.cuentasRepositoryFacade = cuentasRepositoryFacade;
    this.cuentasProducer = cuentasProducer;
  }

  @Override
  public void guardarCuenta(Cuenta cuenta) {

    CuentasProjection cuentasProjection = new CuentasProjection();
    Date fechaHoraActual = new Date(new java.util.Date().getTime());

    cuentasProjection.setCodigo(cuenta.getCodigo());
    cuentasProjection.setClienteID(cuenta.getClienteID());
    cuentasProjection.setEstado(cuenta.getEstado());
    cuentasProjection.setSaldo(cuenta.getSaldo());
    cuentasProjection.setMoneda(cuenta.getMoneda());
    cuentasProjection.setFechaHoraRegistro(fechaHoraActual);
    cuentasProjection.setFechaHoraActualizacion(fechaHoraActual);

    cuentasRepositoryFacade.save(cuentasProjection);
    cuentasProducer.sendMessage(
        "cuenta-created",
        cuenta.toString()
    );

  }

  @Override
  public Cuenta obtenerCuenta(String codigo) throws CuentaNoEncontradaException {

    CuentasProjection cuentasProjection = cuentasRepositoryFacade.findByCodigo(codigo);

    if (Objects.isNull(cuentasProjection)) {

      throw new CuentaNoEncontradaException(
          String.format("No se encontro una cuenta con el codigo: %s", codigo)
      );

    }

    return new Cuenta(
        codigo,
        cuentasProjection.getClienteID(),
        cuentasProjection.getSaldo(),
        cuentasProjection.getMoneda(),
        cuentasProjection.getEstado()
    );

  }

}
