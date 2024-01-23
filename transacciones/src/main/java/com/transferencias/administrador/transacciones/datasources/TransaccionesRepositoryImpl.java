
package com.transferencias.administrador.transacciones.datasources;

import com.transferencias.administrador.transacciones.business.models.Cuenta;
import com.transferencias.administrador.transacciones.business.models.Transaccion;
import com.transferencias.administrador.transacciones.business.repositories.TransaccionesRepository;
import com.transferencias.administrador.transacciones.datasources.postgres.transacciones.TransaccionProjection;
import com.transferencias.administrador.transacciones.datasources.postgres.transacciones.TransaccionRepositoryFacade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.datasources
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 07:14
 * <p>
 */

@Component
public class TransaccionesRepositoryImpl implements TransaccionesRepository {

  private TransaccionRepositoryFacade transaccionRepositoryFacade;

  public TransaccionesRepositoryImpl(TransaccionRepositoryFacade transaccionRepositoryFacade) {
    this.transaccionRepositoryFacade = transaccionRepositoryFacade;
  }


  @Override
  public void registrarTransaccion(Cuenta cuenta, Transaccion transaccion) {

    TransaccionProjection projection = new TransaccionProjection();

    projection.setCodigo(transaccion.getCodigo());
    projection.setMontoTransaccion(transaccion.getMontoTransaccion());
    projection.setMontoRecibido(transaccion.getMontoRecibido());
    projection.setMonedaRecibida(transaccion.getMoneda());
    projection.setCotizacion(transaccion.getCotizacion());
    projection.setSaldoInicial(transaccion.getSaldoInicial());
    projection.setTipo(transaccion.getTipo());
    projection.setCuentaID(cuenta.getCodigo());
    projection.setSaldoFinal(cuenta.getSaldo());
    projection.setFechaHoraRegistro(new Date(new java.util.Date().getTime()));

    transaccionRepositoryFacade.save(projection);

  }
}
