

package com.administrador.tasas.transport.rest;

import com.administrador.tasas.business.exceptions.CotizacionException;
import com.administrador.tasas.business.interactors.TasasInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Project: cajero-automatico-multimoneda
 * Package: com.administrador.tasas.transport.rest
 * <p>
 * User: LOvandoV
 * Date: 12/1/2024
 * Time: 19:46
 * <p>
 */

@Service
public class TasasApiRest implements TasasApiDelegate {

  @Autowired
  TasasInteractor tasasInteractor;

  @Override
  public ResponseEntity obtenerTasa(String from, String to) {

    try {
      return ResponseEntity.ok(tasasInteractor.getTipoCambio(from, to));
    } catch (CotizacionException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Map.of("mensaje", e.getMessage()));
    }

  }

}
