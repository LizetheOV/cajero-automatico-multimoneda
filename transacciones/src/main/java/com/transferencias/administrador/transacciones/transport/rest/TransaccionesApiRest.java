package com.transferencias.administrador.transacciones.transport.rest;

import com.transferencias.administrador.transacciones.business.exceptions.CuentaConSaldoInsuficienteException;
import com.transferencias.administrador.transacciones.business.exceptions.CuentaNoEncontradaException;
import com.transferencias.administrador.transacciones.business.interactors.TransaccionesInteractor;
import com.transferencias.administrador.transacciones.transport.dto.TipoTransaccion;
import com.transferencias.administrador.transacciones.transport.dto.TransaccionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.transferencias.administrador.transacciones.transport.rest
 * <p>
 * User: LOvandoV
 * Date: 23/1/2024
 * Time: 07:16
 * <p>
 */

@Service
public class TransaccionesApiRest implements TransaccionesApiDelegate {

  @Autowired
  TransaccionesInteractor transaccionesInteractor;

  public ResponseEntity registrarDeposito(TransaccionRequestDTO transaccionRequestDTO) {

    try {
      return ResponseEntity.ok(
          transaccionesInteractor.registrarTransaccion(
              transaccionRequestDTO,
              TipoTransaccion.DEPOSITO
          )
      );
    } catch (CuentaNoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("mensaje", e.getMessage()));
    } catch (CuentaConSaldoInsuficienteException e) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
          .body(Map.of("mensaje", e.getMessage()));
    }

  }

  public ResponseEntity registrarRetiro(TransaccionRequestDTO transaccionRequestDTO) {

    try {
      return ResponseEntity.ok(
          transaccionesInteractor.registrarTransaccion(
              transaccionRequestDTO,
              TipoTransaccion.RETIRO
          )
      );
    } catch (CuentaNoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("mensaje", e.getMessage()));
    } catch (CuentaConSaldoInsuficienteException e) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
          .body(Map.of("mensaje", e.getMessage()));
    }

  }

}
