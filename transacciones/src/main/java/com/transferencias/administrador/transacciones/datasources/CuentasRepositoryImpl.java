
package com.transferencias.administrador.transacciones.datasources;

import com.transferencias.administrador.transacciones.business.repositories.CuentasRepository;
import com.transferencias.administrador.transacciones.datasources.postgres.cuentas.CuentaProjection;
import com.transferencias.administrador.transacciones.datasources.postgres.cuentas.CuentaRepositoryFacade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

  public CuentasRepositoryImpl(CuentaRepositoryFacade cuentaRepositoryFacade) {
    this.cuentaRepositoryFacade = cuentaRepositoryFacade;
  }

  @Override
  public void registrarCuenta(String codigo, String moneda, BigDecimal saldo, String estado) {

    CuentaProjection cuentaProjection = new CuentaProjection();
    cuentaProjection.setCodigo(codigo);
    cuentaProjection.setMoneda(moneda);
    cuentaProjection.setSaldo(saldo);
    cuentaProjection.setEstado(estado);

    cuentaRepositoryFacade.save(cuentaProjection);

  }

}
