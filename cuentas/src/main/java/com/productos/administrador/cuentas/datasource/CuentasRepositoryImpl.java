
package com.productos.administrador.cuentas.datasource;

import com.productos.administrador.cuentas.business.exceptions.CuentaNoEncontradaException;
import com.productos.administrador.cuentas.business.models.Cuenta;
import com.productos.administrador.cuentas.business.repositories.CuentasRepository;
import com.productos.administrador.cuentas.datasource.kafka.CuentasProducer;
import com.productos.administrador.cuentas.datasource.postgres.cuentas.CuentaProjection;
import com.productos.administrador.cuentas.datasource.postgres.cuentas.CuentaRepositoryFacade;
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

  private CuentaRepositoryFacade cuentaRepositoryFacade;
  private CuentasProducer cuentasProducer;

  public CuentasRepositoryImpl(CuentaRepositoryFacade cuentaRepositoryFacade, CuentasProducer cuentasProducer) {
    this.cuentaRepositoryFacade = cuentaRepositoryFacade;
    this.cuentasProducer = cuentasProducer;
  }

  @Override
  public void guardarCuenta(Cuenta cuenta) {

    CuentaProjection cuentaProjection = new CuentaProjection();
    Date fechaHoraActual = new Date(new java.util.Date().getTime());

    cuentaProjection.setCodigo(cuenta.getCodigo());
    cuentaProjection.setClienteID(cuenta.getClienteID());
    cuentaProjection.setEstado(cuenta.getEstado());
    cuentaProjection.setSaldo(cuenta.getSaldo());
    cuentaProjection.setMoneda(cuenta.getMoneda());
    cuentaProjection.setFechaHoraRegistro(fechaHoraActual);
    cuentaProjection.setFechaHoraActualizacion(fechaHoraActual);

    cuentaRepositoryFacade.save(cuentaProjection);
    cuentasProducer.sendMessage(
        "cuenta-created",
        cuenta.toString()
    );

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
        cuentaProjection.getClienteID(),
        cuentaProjection.getSaldo(),
        cuentaProjection.getMoneda(),
        cuentaProjection.getEstado()
    );

  }

}
