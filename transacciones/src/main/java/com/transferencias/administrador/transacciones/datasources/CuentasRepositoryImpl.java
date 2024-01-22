/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.transferencias.administrador.transacciones.datasources;

import com.transferencias.administrador.transacciones.business.repositories.CuentasRepository;
import com.transferencias.administrador.transacciones.datasources.postgres.cuentas.CuentasProjection;
import com.transferencias.administrador.transacciones.datasources.postgres.cuentas.CuentasRepositoryFacade;
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

  private CuentasRepositoryFacade cuentasRepositoryFacade;

  public CuentasRepositoryImpl(CuentasRepositoryFacade cuentasRepositoryFacade) {
    this.cuentasRepositoryFacade = cuentasRepositoryFacade;
  }

  @Override
  public void registrarCuenta(String codigo, String moneda, BigDecimal saldo, String estado) {

    CuentasProjection cuentasProjection = new CuentasProjection();
    cuentasProjection.setCodigo(codigo);
    cuentasProjection.setMoneda(moneda);
    cuentasProjection.setSaldo(saldo);
    cuentasProjection.setEstado(estado);

    cuentasRepositoryFacade.save(cuentasProjection);

  }

}
