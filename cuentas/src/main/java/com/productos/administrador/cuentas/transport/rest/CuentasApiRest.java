/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.productos.administrador.cuentas.transport.rest;

import com.productos.administrador.cuentas.business.exceptions.ClienteNoEncontradoException;
import com.productos.administrador.cuentas.business.exceptions.CuentaNoEncontradaException;
import com.productos.administrador.cuentas.business.interactors.CuentasInteractor;
import com.productos.administrador.cuentas.transport.dto.CuentaDTO;
import com.productos.administrador.cuentas.transport.dto.SaldoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.productos.administrador.cuentas.transport.rest
 * <p>
 * User: LOvandoV
 * Date: 22/1/2024
 * Time: 17:03
 * <p>
 */

@Service
public class CuentasApiRest implements CuentasApiDelegate {

  @Autowired
  CuentasInteractor cuentasInteractor;

  public ResponseEntity crearCuenta(CuentaDTO cuentaDTO) {

    try {
      return ResponseEntity.ok(cuentasInteractor.guardarCuenta(cuentaDTO));
    } catch (ClienteNoEncontradoException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("mensaje", e.getMessage()));
    }

  }

  public ResponseEntity obtenerCuenta(UUID id) {

    try {
      return ResponseEntity.ok(cuentasInteractor.obtenerCuenta(id.toString()));
    } catch (CuentaNoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("mensaje", e.getMessage()));
    }

  }

  public ResponseEntity obtenerSaldo(UUID id, String moneda) {

    try {
      return ResponseEntity.ok(cuentasInteractor.obtenerSaldo(id.toString(), moneda));
    } catch (CuentaNoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("mensaje", e.getMessage()));
    }

  }

}
